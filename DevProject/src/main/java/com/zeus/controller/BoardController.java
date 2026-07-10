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

import com.zeus.dto.BoardDTO;
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
@RequestMapping(value = "/board")
public class BoardController {
	@RequestMapping(value="/ajaxhome", method = RequestMethod.GET)
    public String ajaxhome() {
        return "board/ajaxhome";
    }

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public String home() {
        return "board/home";
    }

    @RequestMapping(value="/insert", method = RequestMethod.POST)
    public String insert() {
        return "board/insert";
    }

    @RequestMapping(value = "/select/{boardNo}", method = RequestMethod.GET)
    public String select(@PathVariable("boardNo") int boardNo, Model model) {
        model.addAttribute("boardNo", 100);
        return "board/select";
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public String select(Model model) {
        model.addAttribute("boardNo", "해당된 번호는 없음");
        return "board/select";
    }

    @GetMapping(value = "/select", params = "register")
    public String select_params(Model model) {
        model.addAttribute("boardNo", "register 값 없음");
        return "board/select";
    }

    @RequestMapping(value="/delete", method = RequestMethod.GET)
    public String delete(Model model) {
        return "board/delete";
    }

    @RequestMapping(value="/update", method = RequestMethod.GET)
    public String update1(Model model) {
        return "board/update";
    }
    
    @RequestMapping(value="/update{boardNo}")
    public ResponseEntity<String> update2(@PathVariable("boardNo") int boardNo, @RequestBody BoardDTO boardDTO) {
        log.info("boardDTO ="+boardDTO);
        ResponseEntity<String> message = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        return message;
    }

    @RequestMapping(value="/update{boardNo}", headers = "X-HTTP-Method-Override = PUT")
    public ResponseEntity<String> update3(@PathVariable("boardNo") int boardNo, @RequestBody BoardDTO boardDTO) {
        log.info("headers boardDTO ="+boardDTO);
        ResponseEntity<String> message = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        return message;
    }
}

