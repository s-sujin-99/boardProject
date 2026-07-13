package com.hr.service;

import java.util.List;

import com.hr.dto.BoardDto;

public interface BoardService {
	public boolean insert(BoardDto boardDto) throws Exception; 
	public BoardDto select(BoardDto boardDto) throws Exception; 
	public boolean update(BoardDto boardDto) throws Exception; 
	public boolean delete(BoardDto boardDto) throws Exception; 
	public List<BoardDto> list() throws Exception;
}
