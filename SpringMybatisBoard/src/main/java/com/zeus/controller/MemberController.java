package com.zeus.controller;

import java.util.List;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zeus.domain.Member;
import com.zeus.dto.BoardDTO;
import com.zeus.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("user")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@GetMapping(value = "/insertForm")
	public String memberInsertForm(Member member, Model model) {
		model.addAttribute("member", member);
		return "user/insertForm";
	}

//	@GetMapping(value = "/insert")
//	@ResponseBody
//	public String boardInsert(Model model) throws Exception {
//		Member member = new Member();
//		member.setUserId("id");
//		member.setUserName("zeus");
//		member.setUserPw("pwd");
//		log.info("user/insert" + member.toString());
//		boolean result = memberService.insert(member);
//		if (result == true) {
//			return "성공";
//		}
//		return "실패";
//	}

	@PostMapping(value = "/insert")
	public String boardInsert(Member member, Model model) throws Exception {
		log.info("user/insert" + member.toString());
		boolean result = memberService.insert(member);
		if (result == true) {
			return "user/success";
		}
		return "user/fail";
	}

	// 사용자 리스트 화면 요청
	@GetMapping(value = "/list")
	public String userList(Member member, Model model) throws Exception {
		List<Member> list = memberService.list();
		model.addAttribute(list);
		return "user/list";
	}

	// 사용자 정보 수정폼 화면 요청
	@GetMapping(value = "/updateForm")
	public String userUpdateForm(Member member, Model model) throws Exception {
		if (member.getUserNo() <= 0) {
			return "user/fail";
		}
		member = memberService.select(member);

		model.addAttribute("member", member);
		return "user/updateForm";
	}

	// 사용자 수정 요청
	@PostMapping(value = "/update")
	public String userUpdate(Member member, Model model) throws Exception {
		boolean result = memberService.update(member);
		if (result == true) {
			return "user/success";
		}
		return "user/fail";
	}

	// 사용자 삭제 요청
	@GetMapping(value = "/deleteMember")
	public String userDelete(Member member, Model model) throws Exception {
		boolean result = memberService.delete(member);
		if (result == true) {
			return "user/success";
		}
		return "user/fail";
	}

	// 사용자 삭제 요청
	@GetMapping(value = "/deleteAuth")
	public String authDelete(Member member, Model model) throws Exception {
		boolean result = memberService.deleteAuth(member);
		if (result == true) {
			return "user/success";
		}
		return "user/fail";
	}

	// 사용자 정보 요청
	@GetMapping(value = "/select")
	public String userSelect(Member member, Model model) throws Exception {
		if (member.getUserNo() <= 0) {
			return "user/fail";
		}
		member = memberService.select(member);
		if (member == null) {
			return "user/fail";
		}
		model.addAttribute(member);
		return "user/select";
	}

}