package com.zeus.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zeus.dto.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
	@GetMapping(value = "/member/model01")
	public String model01(Model model) {
		
		Member member = new Member();
		member.setUserId("123456");
		member.setEmail("aaa@ccc.com");
		member.setPassword("password");
		
		model.addAttribute("member", member);
		
		return "member/model01";
	}
	
	@GetMapping(value = "/member/model02")
	public String model02(Model model) {
		
		ArrayList<Member> members = new ArrayList<Member>();
		for (int i = 0; i < 3; i++) {
			Member member = new Member();
			member.setUserId("홍길동" +i);
			member.setEmail("aaa@ccc.com"+ i);
			member.setPassword("password" +i);
			members.add(member);
		}
		
		model.addAttribute("members",members);
		
		return "member/model01";
	}
	
	@GetMapping(value = "/member/model03")
	public String model03(@ModelAttribute Member member) {
		log.info("/member/model03" + member);
		
		return "member/model01";
	}
	
	@GetMapping(value = "/member/model04")
	public String model04(Member member, RedirectAttributes attributes) {
		log.info("/member/model04" + member);
		attributes.addFlashAttribute("member", member);
		return "redirect:/member/result";
	}
	
	@GetMapping(value = "/member/result")
	public String result() {
		log.info("result");
		
		return "member/result";
	}
	
	
}
