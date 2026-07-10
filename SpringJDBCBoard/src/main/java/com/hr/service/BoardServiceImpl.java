package com.hr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hr.dao.BoardDao;
import com.hr.domain.Board;
import com.hr.dto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardDao boardDao;
	
	@Override
	@Transactional(readOnly = true)
	public boolean insert(BoardDto boardDto) throws Exception {
		if(boardDto == null || boardDto.getTitle() == null) {
			return false;			
		}
		Board board = new Board();
		board.setTitle(boardDto.getTitle());
		board.setWriter(boardDto.getWriter());
		board.setContent(boardDto.getContent());
		
		return boardDao.insert(board);
		
	}

	@Override
	@Transactional(readOnly = true)
	public BoardDto select(BoardDto boardDto) throws Exception {
		if(boardDto.getBoardNo() <= 0) {
			return null;
		};
		Board board = new Board();
		board.setBoardNo(boardDto.getBoardNo());
		Board board1 = boardDao.select(board);
		
		boardDto.setBoardNo(board1.getBoardNo());
		boardDto.setContent(board1.getContent());
		boardDto.setRegDate(board1.getRegDate());
		boardDto.setTitle(board1.getTitle());
		boardDto.setWriter(board1.getWriter());
		
		return boardDto;
	}

	@Override
	public boolean update(BoardDto boardDto) throws Exception {
		if(boardDto == null || boardDto.getTitle() == null) {
			return false;			
		}
		
		Board board = new Board();
		board.setBoardNo(boardDto.getBoardNo());
		board.setTitle(boardDto.getTitle());
		board.setWriter(boardDto.getWriter());
		board.setContent(boardDto.getContent());
		
		return boardDao.update(board);
	}

	@Override
	public boolean delete(BoardDto boardDto) throws Exception {
		if(boardDto.getBoardNo() <= 0) {
			return false;
		};
		Board board = new Board();
		board.setBoardNo(boardDto.getBoardNo());
		
		return boardDao.delete(board);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BoardDto> allSelect() throws Exception {
		List<Board> list = boardDao.allSelect();
		if(list.size() <= 0) {
			return null;
		}
		
		List<BoardDto> list2 = new ArrayList<>();
		for (Board board : list) {
			BoardDto boardDto = new BoardDto();
			boardDto.setBoardNo(board.getBoardNo());
			boardDto.setContent(board.getContent());
			boardDto.setRegDate(board.getRegDate());
			boardDto.setTitle(board.getTitle());
			boardDto.setWriter(board.getWriter());
			list2.add(boardDto);
		}
		
		return list2;
	}

	
	
}
