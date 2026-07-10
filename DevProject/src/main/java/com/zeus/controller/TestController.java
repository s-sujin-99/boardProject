package com.zeus.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.zeus.dto.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController {
	@GetMapping(value = "/spring/form11")
	public String method1(Model model) {
		return "spring/form1";
	}
	
	@GetMapping(value = "/spring/form22")
	public void method2(Model model) {
		log.info("spring/form1");
	}
	
	@GetMapping(value = "/spring/form33")
	public Member method3(Model model) {
		log.info("spring/form1");
		Member member = new Member();
		return member;
	}
	
	@GetMapping(value = "/spring/form44")
	public ResponseEntity<Member> method4(Model model) {
		Member member = new Member();
		ResponseEntity<Member> entity = new ResponseEntity<>(member, HttpStatus.OK);
		return entity;
	}
}
