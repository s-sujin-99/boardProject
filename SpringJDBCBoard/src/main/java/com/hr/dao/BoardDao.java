package com.hr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hr.domain.Board;

@Repository
public class BoardDao {
	@Autowired 
	// jdbc teplate 
	private JdbcTemplate jdbcTemplate;
	
	public boolean insert(Board board) throws Exception {
		String query = "INSERT INTO board(board_no, title, content, writer)" 
					 + "VALUES(board_seq.NEXTVAL, ?, ?, ?)"; 
		int count = jdbcTemplate.update(query, board.getTitle(), board.getContent(), 
				board.getWriter());
		return (count <= 0) ? false : true;
	}

	public Board select(Board board) throws Exception {
		// TODO Auto-generated method stub
		String query = "select * from board where board_no = ?";
		List<Board> list = jdbcTemplate.query(query, new RowMapper<Board>() {

			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Board board = new Board();
				
				board.setBoardNo(rs.getInt("board_no"));
				board.setContent(rs.getString("content"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setRegDate(rs.getDate("reg_date"));
				
				return board;
			}
			
		}, board.getBoardNo());
		
		return (list.isEmpty() == true) ? null : list.get(0);
	}

	public boolean update(Board board) throws Exception {
		String query = "update board set board.title = ?, board.content = ? where board_no = ?";
		
		int count = jdbcTemplate.update(query, board.getTitle(), board.getContent(), board.getBoardNo());
		return (count <= 0) ? false : true;
	}

	public boolean delete(Board board) throws Exception {
		String query = "delete from board where board_no = ?";
		
		int count = jdbcTemplate.update(query, board.getBoardNo());
		return (count <= 0) ? false : true;
		
	}

	public List<Board> allSelect() throws Exception {
		
		String query =  "select * from board where board_no > 0 order by reg_date desc";
		List<Board> list = jdbcTemplate.query(query, new RowMapper<Board>() {

			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Board board = new Board();
				
				board.setBoardNo(rs.getInt("board_no"));
				board.setContent(rs.getString("content"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setRegDate(rs.getDate("reg_date"));
				
				return board;
			}
			
		});
		
		return list;
	}
}
