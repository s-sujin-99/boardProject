package com.zeus.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.zeus.common.security.CustomAccessDeniedHandler;
import com.zeus.common.security.CustomLoginSuccessHandler;
import com.zeus.common.security.CustomUserDetailsService;

import jakarta.servlet.DispatcherType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity() // Spring security 자동 설정 비활성화
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {
	@Autowired
	DataSource dataSource;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		log.info("security config ...");
		// 1. csrf 비활성화
		http.csrf((csrf) -> csrf.disable());

		// 2-1. [추가] URL별 접근 권한 설정
		http.authorizeHttpRequests(auth -> auth.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
				.requestMatchers("/error/accessError", "/login", "/logout", "/css/**", "/js/**", "/error",
						"/.well-known/**", "/WEB-INF/**")
				.permitAll().requestMatchers("/board/list").permitAll() // 게시판 목록: 누구나
//				.requestMatchers("/board/insertForm").hasRole("MEMBER") // 게시판 등록: 회원만
//				.requestMatchers("/notice/list").permitAll() // 공지사항 목록: 누구나
//				.requestMatchers("/notice/insertForm").hasRole("ADMIN") // 공지사항 등록: 관리자만
				.anyRequest().authenticated() // 그 외 모든 요청은 인증 필요
		);

		// 2-2. 접근 거부 처리자에 대한 페이지 이동 URI를 지정
		// 페이지 포워딩:서버내부에서 요청을 사용자가 지정한 /accessError경로로 포워딩(Forwarding)처리한다.
		// (브라우저 주소창은 바뀌지 않고 서버 내부에서 해당 페이지를 보여준다.
//				http.exceptionHandling(exception -> exception.accessDeniedPage("/accessError"));
		http.exceptionHandling(exception -> exception.accessDeniedHandler(createAccessDeniedHandler()));

		// 3. 기본 폼 로그인 활성화
		// 개발자가 정의한 로그인 페이지의 URI를 지정한다.
		http.formLogin(form -> form.loginPage("/login/insertForm") // 커스텀 로그인 페이지가 있다면 지정
				.loginProcessingUrl("/login") // 로그인 폼 Action URL post(Security가 낚아챔) id(username), pwd(password)
				.defaultSuccessUrl("/", true) // 로그인 성공 시 이동할 기본 URL
				.successHandler(createAuthenticationSuccessHandler()).permitAll());
		// 4. 로그아웃 처리
		// 로그아웃 처리를 위한 URI를 지정하고, 로그아웃한 후에 세션을 무효화 한다.
		http.logout(logout -> logout.logoutUrl("/logout") // 로그아웃을 처리할 URL (기본값: /logout)
				.logoutSuccessUrl("/login/insertForm") // 로그아웃 성공 시 이동할 페이지
				.invalidateHttpSession(true) // HTTP 세션 무효화 (기본값: true)
				.deleteCookies("JSESSIONID", "remember-me") // 로그아웃 시 관련 쿠키 삭제
				.permitAll() // 로그아웃 요청은 누구나 접근 가능해야 함
		);

		// 5. 쿠키의 유효 시간을 지정한다(24시간).
		http.rememberMe(remember -> remember.key("zeus")// 인증토큰생성시 사용할키 (보안상 중요)
				.tokenRepository(createJDBCRepository())// DB를 이용한 토큰 저장소 설정
				.tokenValiditySeconds(60 * 60 * 24) // 토큰유효기간(24시간)
		);

		return http.build();
	}

	@Bean
	public PersistentTokenRepository createJDBCRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}

	@Bean
	public AccessDeniedHandler createAccessDeniedHandler() {
		// TODO Auto-generated method stub
		return new CustomAccessDeniedHandler();
	}

	// CustomLoginSuccessHandler를 빈으로 등록한다.
	@Bean
	public AuthenticationSuccessHandler createAuthenticationSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}

//	@Autowired
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// 지정된 아이디와 패스워드로 로그인이 가능하도록 설정한다.
//		auth.inMemoryAuthentication().withUser("member").password("{noop}1234").roles("MEMBER");
//		auth.inMemoryAuthentication().withUser("manager").password("{noop}1234").roles("MANAGER");
//		auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN", "MEMBER");
	// CustomUserDetailsService 빈을 인증 제공자에 지정한다.
//		auth.userDetailsService(createUserDetailsService()) 
//		.passwordEncoder(createPasswordEncoder());
//		
//	}

	// 스프링 시큐리티의 UserDetailsService를 구현한 클래스를 빈으로 등록한다.
	@Bean
	public UserDetailsService createUserDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public PasswordEncoder createPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
