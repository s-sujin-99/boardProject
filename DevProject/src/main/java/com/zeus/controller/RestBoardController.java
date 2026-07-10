package com.zeus.controller;

import org.apache.commons.logging.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zeus.dto.BoardDTO;
import com.zeus.dto.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
//뷰화면에서 사용자 요청을 받는 클래스
@RestController
@Slf4j
//데이터만 전달받고 전달하는 리엑트 전문 컨트롤로
@RequestMapping(value = "/board")
public class RestBoardController {
	
    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public @ResponseBody String hello() {
        return "안녕하세요";
    }
    @RequestMapping(value="/list", method = RequestMethod.GET)
    public @ResponseBody BoardDTO list() {
    	BoardDTO bDTO = new BoardDTO();
    	bDTO.setBoardNo(10);
    	bDTO.setContent("dkfs");
    	bDTO.setTitle("title");
    	bDTO.setWriter("kdj");
        return bDTO;
    }
    @RequestMapping(value="/listArray", method = RequestMethod.GET)
    public @ResponseBody List<BoardDTO> listArray() {
    	List<BoardDTO> list = new ArrayList<>();
    	BoardDTO bDTO = new BoardDTO();
    	bDTO.setBoardNo(1);
    	bDTO.setContent("dkfs" + 1);
    	bDTO.setTitle("title" + 1);
    	bDTO.setWriter("kdj" + 1);
        list.add(bDTO);
        
        return list;
    }

    
}

