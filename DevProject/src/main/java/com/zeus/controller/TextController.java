package com.zeus.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.zeus.dto.Address;
import com.zeus.dto.BoardDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TextController {

    // 2번방
    @GetMapping(value = "/Text/posthome")
    public void posthome() {
 
    }
    
    @GetMapping(value = "/Text/ajaxhome2")
    public void posthome2() {
    }

    @GetMapping(value = "/Text/ajaxhome3")
    public void posthome3() {
    }

    @GetMapping(value = "/Text/ajaxhome4")
    public void posthome4() {
    }
//    @RequestMapping(value = "/Text/gohome1", method = RequestMethod.POST)
//    public String gohome(Model model, @ModelAttribute  BoardDTO bd) {
//        log.info("/Text/gohome1 userId =" + bd.toString());
//        if (bd.getHobby() != null) {
//        	log.info("bd.getHobby().size() =" + bd.getHobby().size());
//			
//		}
//        
//        return "/Text/gohome1";
//    }
    
    @RequestMapping(value = "/Text/gohome1", method = RequestMethod.POST)
    public String gohome(Model model, BoardDTO bd) {
    	ArrayList<MultipartFile> list = bd.getPicture();
       
    	for (MultipartFile p : list) {
        	log.info("/Text/gohome1 =" + p.getOriginalFilename());
        	log.info("/Text/gohome1 =" + p.getSize());
        	log.info("/Text/gohome1 =" + p.getContentType());
		}
    	
//    	log.info("/Text/gohome1 =" + p.getOriginalFilename());
//    	log.info("/Text/gohome1 =" + p.getSize());
//    	log.info("/Text/gohome1 =" + p.getContentType());
        
        return "/Text/gohome1";
    }
    
    @RequestMapping(value = "/Text/gohome1/{boardNo}", method = RequestMethod.POST)
    public ResponseEntity<String> gohome2(Model model, @PathVariable("boardNo") String boardNo ,@RequestBody BoardDTO bd) {
    	log.info("/Text/gohome1 boardNo =" + boardNo);
    	log.info("/Text/gohome1 =" + bd.toString());
    	
    	ResponseEntity<String> entity = new ResponseEntity<>("SUCCESS",HttpStatus.OK);
    	
        return entity;
    }

    @RequestMapping(value = "/Text/gohome3", method = RequestMethod.POST)
    public ResponseEntity<String> gohome3(Model model, @RequestBody ArrayList<BoardDTO> bd) {
    	for (BoardDTO boardDTO : bd) {
    		log.info("/Text/gohome1 boardNo =" + boardDTO.getBoardNo());
    		log.info("/Text/gohome1 =" + boardDTO.getTitle());			
		}
    	
    	ResponseEntity<String> entity = new ResponseEntity<>("SUCCESS",HttpStatus.OK);
    	
        return entity;
    }
    
    @PostMapping(value = "/Text/gohome4", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> gohome4(MultipartFile file) {
    	
        log.info("/Text/gohome1 =" + file.getOriginalFilename());
        log.info("/Text/gohome1 =" + file.getSize());
        log.info("/Text/gohome1 =" + file.getContentType());
    	
        ResponseEntity<String> entity = new ResponseEntity<>("SUCCESS",HttpStatus.OK);
    	
        return entity;
    }
    
    @RequestMapping(value = "/Text/gohome1", method = RequestMethod.GET)
    public ArrayList<Address> gohomeGet(Model model, @ModelAttribute  BoardDTO bdo) {
    	ArrayList<Address> addressList = bdo.getAddress();
        log.info("/Text/gohome1 userId =" + addressList.toString());
        
        return addressList;
    }
}
