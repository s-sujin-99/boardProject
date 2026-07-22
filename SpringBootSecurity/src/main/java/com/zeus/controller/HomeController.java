package com.zeus.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Controller
@MapperScan(basePackages = "com.zeus.mapper")
public class HomeController {
	@GetMapping("/")
	public String home(Locale locale, Model model) {
		log.info(locale + "의 방문을 환영합니다.");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("formattedDate", formattedDate);
		return "home";
	}
	
	@GetMapping("/accessError")
	public String accessError() {
		log.info("접근 권한 위배");
		return "error/accessError";
	}
	
}
