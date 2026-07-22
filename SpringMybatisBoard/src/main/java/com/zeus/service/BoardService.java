package com.zeus.service;

import java.util.List;

import com.zeus.dto.BoardDTO;

public interface BoardService {
	public boolean insert(BoardDTO boardDto) throws Exception; 
	public BoardDTO select(BoardDTO boardDto) throws Exception; 
	public boolean update(BoardDTO boardDto) throws Exception; 
	public boolean delete(BoardDTO boardDto) throws Exception; 
	public List<BoardDTO> list() throws Exception;
	
	public List<BoardDTO> search(BoardDTO boardDto) throws Exception; 
}