package com.zeus.controller;

import org.apache.commons.logging.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zeus.dto.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	// http://192.168.0.9/8080/home 요청을 하면 여기서 받는다.
	// 1) get 방식 2) /home 요청한다. 3) 해당된 함수에서 처리하고 4)return "home"화면을 보여줘야 한다.
	@RequestMapping(value="/home")
	public String home() {
		Date data = new Date();
		log.info(String.format("접속시간:%s ", data.toString()));
		return "home";

	}
	@RequestMapping(value = "/")
	public String mainpage() {
		//비즈니스 로직을 처리한다. (데이타베이스 부르고 => 연산하고 => 결과값 home.jsp 보내준다)
		return "webpage";
	}
	
	@GetMapping(value="/home1")
	public String home1(Model model) {
		//비즈니스 로직을 처리한다. (데이타베이스 부르고 => 연산하고 => 결과값 home.jsp 보내준다)
		log.info("사용자가 home1 선택하였습니다.");
		model.addAttribute("severTime","서버에서 만든 오후 14시 59분");
		return "home1";

	}
	@GetMapping(value="/home2")
	public String home2(Model model) {
		//비즈니스 로직을 처리한다. (데이타베이스 부르고 => 연산하고 => 결과값 home.jsp 보내준다)
		log.info("사용자가 home2 선택하였습니다.");
		Member member = new Member();
		member.setEmail("kdj@nate.com");
		member.setPassword("123456");
		member.setUserId("userid");
		member.setUserName("zeus");
		LocalDate Id = LocalDate.of(2026, 6, 25);
		member.setDateOfBirth(Id);
		
		model.addAttribute("severTime","서버에서 만든 오후 14시 59분");
		model.addAttribute(member);
		return "home2";

	}
	@GetMapping(value="/home3")
	public String home3(Model model) {
		//비즈니스 로직을 처리한다. (데이타베이스 부르고 => 연산하고 => 결과값 home.jsp 보내준다)
		log.info("사용자가 home3 선택하였습니다.");
		
		model.addAttribute("severTime","서버에서 만든 오후 14시 59분");
	
		return "home3";

	}
	@GetMapping(value="/home4")
	public String home4(Model model) {
		//비즈니스 로직을 처리한다. (데이타베이스 부르고 => 연산하고 => 결과값 home.jsp 보내준다)
		log.info("사용자가 home4 선택하였습니다.");
		String[] hobbyArray = new String[] {"자바스크립트","자바공부하기","스프링","파이썬공부"};
		
		List<String> list = new ArrayList<String>();
		list.add("자바스크립트");
		list.add("자바공부하기");
		list.add("스프링");
		list.add("파이썬공부");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("data1", "자바스크립트");
		map.put("data2", "자바공부하기");
		map.put("data3", "스프링");
		map.put("data4", "파이썬공부");
		
		String hobbyString = "java,python,spring";
		
		model.addAttribute("hobbyString", hobbyString);
		model.addAttribute("map",map);
		model.addAttribute("hobbyArray",hobbyArray);
		model.addAttribute("list",list);
		model.addAttribute("severTime","서버에서 만든 오후 14시 59분");
		return "home4";

	}
	@GetMapping(value="/home1101")
	public void home1101(Model model) {
		//비즈니스 로직을 처리한다. (데이타베이스 부르고 => 연산하고 => 결과값 home.jsp 보내준다)
		log.info("사용자가 home1101 선택하였습니다.");
		model.addAttribute("severTime","서버에서 만든 오후 14시 59분");
		return;

	}

}
