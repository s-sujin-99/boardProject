package com.hr.controller;

import com.hr.service.BoardServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hr.dto.BoardDto;
import com.hr.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {
	private final BoardServiceImpl boardServiceImpl;
	@Autowired
	private BoardService boardService;

	BoardController(BoardServiceImpl boardServiceImpl) {
		this.boardServiceImpl = boardServiceImpl;
	}

	// 게시판 작성 폼
	@GetMapping(value = "/board/insertForm")
	public String boardInsertForm(BoardDto boardDto, Model model) {
		model.addAttribute("boardDto", boardDto);
		return "board/insertForm";
	}

	// 게시판 입력 내용 저장 요청
	@PostMapping(value = "/board/insert")
	public String boardInsert(BoardDto boardDto, Model model) throws Exception {
		log.info("board/insert" + boardDto.toString());
		try {
			boolean result = boardService.insert(boardDto);

			if (result) {
				model.addAttribute("boardDto", boardDto);
				return "board/success";
			} else {
				model.addAttribute("mode", "insert"); 
				return "board/fail";
			}
		} catch (Exception e) {
			model.addAttribute("mode", "insert"); 
			return "board/fail";
		}
	}

	// 게시판 리스트 요청
	@GetMapping(value = "/board/list")
	public String boardList(Model model) throws Exception {
		List<BoardDto> allSelect = boardServiceImpl.allSelect();
		if (allSelect == null || allSelect.size() <= 0) {
			
			model.addAttribute("mode", "select"); 
			return "board/fail";
		}
		model.addAttribute("list", allSelect);
		return "board/list";
	}

	// 게시판 글 요청
	@GetMapping(value = "/board/select")
	public String boardSelect(BoardDto boardDto, Model model) throws Exception {
		if (boardDto.getBoardNo() == 0) {
			model.addAttribute("mode", "select"); 
			return "board/fail";
		}
		
		boardDto = boardServiceImpl.select(boardDto);
		if (boardDto == null) {
			model.addAttribute("mode", "select"); 
			return "board/fail";
		}

		model.addAttribute("boardDto", boardDto);
		return "board/select";
	}

	// 게시판 글 삭제 요청
	@GetMapping(value = "/board/delete")
	public String boardDelete(BoardDto boardDto, Model model) throws Exception {
		if (boardDto.getBoardNo() <= 0) {
			model.addAttribute("mode", "delete"); 
			return "board/fail";
		}
		
		boolean result = boardServiceImpl.delete(boardDto);
		if (result == false) {
			model.addAttribute("mode", "delete"); 
			return "board/fail";
		}

		model.addAttribute("boardDto", boardDto);
		return "redirect:/board/list";
	}

	// 게시판 글 수정 폼 요청
	@GetMapping(value = "/board/updateForm")
	public String boardUpdateForm(BoardDto boardDto, Model model) throws Exception {
		if (boardDto.getBoardNo() <= 0) {
			model.addAttribute("mode", "select"); 
			return "board/fail";
		}
		
		boardDto = boardServiceImpl.select(boardDto);
		if (boardDto == null) {
			model.addAttribute("mode", "select");
			return "board/fail";
		}
		
		model.addAttribute("boardDto", boardDto);
		return "board/updateForm";
	}
	
	// 게시판 수정 요청
	@PostMapping(value = "/board/update")
	public String boardUpdate(BoardDto boardDto, Model model) throws Exception {
		if (boardDto.getBoardNo() <= 0) {
			model.addAttribute("mode", "update"); 
			return "board/fail";
		}
		
		boolean result = boardServiceImpl.update(boardDto);
		if (result == false) {
			model.addAttribute("mode", "update"); 
			return "board/fail";
		}
		
		return "redirect:/board/select?boardNo=" + boardDto.getBoardNo();
	}
}