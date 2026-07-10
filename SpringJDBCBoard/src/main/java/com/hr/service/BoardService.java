package com.hr.service;

import java.util.List;

import com.hr.dto.BoardDto;

public interface BoardService {
	// insert
	public boolean insert(BoardDto boardDto) throws Exception; 
	// select
	public BoardDto select(BoardDto boardDto) throws Exception; 
	// update
	public boolean update(BoardDto boardDto) throws Exception; 
	// delete
	public boolean delete(BoardDto boardDto) throws Exception; 
	// all select
	public List<BoardDto> allSelect() throws Exception; 
}
