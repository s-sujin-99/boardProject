package com.zeus;

import org.mybatis.spring.annotation.MapperScan; // 필수 임포트
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zeus.mapper") // 이 패키지 아래의 인터페이스를 전부 매퍼로 인식함
public class SpringMybatisBoardApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringMybatisBoardApplication.class, args);
    }
}