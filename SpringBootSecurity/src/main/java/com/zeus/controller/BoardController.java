package com.zeus.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Controller
public class BoardController {
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')") 
	@RequestMapping("/board/list")
	public String list() {
		log.info("list : 모두가 접근 가능");
		return "board/list";
	}
	
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	@RequestMapping("/board/insertForm")
	public String insertForm() {
		log.info("insertForm : 로그인한 회원만 접근 가능");
		return "board/insertForm";
	}
}
