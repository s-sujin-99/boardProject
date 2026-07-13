 package com.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hr.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	List<Board> findByTitleContaining(String keyword);
	
	List<Board> findByContentContaining(String keyword);
	
	List<Board> findByWriterContaining(String keyword);
}
