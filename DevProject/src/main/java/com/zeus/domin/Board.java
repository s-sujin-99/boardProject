package com.zeus.domin;

import java.io.Serializable;
//import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
//@EqualsAndHashCode(of= {"boardNo","title"})
public class Board implements Serializable, Comparable<Board>{
	@NonNull
	private int boardNo;
	@NonNull
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	@Override
	public int compareTo(Board o) {
		// TODO Auto-generated method stub
		return this.boardNo - o.boardNo;
	}
	
}

//public class Board implements Serializable , Comparable<Board>{
//	private static final long serialVersionUID = 1L;
//
//	private int boardNo;
//	private String title;
//	private String content;
//	private String writer;
//	private Date regDate;
//
//	public Board() {
//		this(0, null);
//	}
//
//	public Board(int boardNo, String title) {
//		super();
//		this.boardNo = boardNo;
//		this.title = title;
//	}
//
//	public int getBoardNo() {
//		return boardNo;
//	}
//
//	public void setBoardNo(int boardNo) {
//		this.boardNo = boardNo;
//	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getContent() {
//		return content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
//
//	public String getWriter() {
//		return writer;
//	}
//
//	public void setWriter(String writer) {
//		this.writer = writer;
//	}
//
//	public Date getRegDate() {
//		return regDate;
//	}
//
//	public void setRegDate(Date regDate) {
//		this.regDate = regDate;
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(boardNo);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if(obj instanceof Board) {
//			Board board = (Board) obj;
//			if(this.boardNo == board.boardNo) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//	@Override
//	public boolean compareTo(Board o) {
//		return this.boardNo - o.boardNo
//	}
//
//	@Override
//	public String toString() {
//		return "Board [boardNo=" + boardNo + ", title=" + title + ", content=" + content + ", writer=" + writer
//				+ ", regDate=" + regDate + "]";
//	}
//	
//	
//
//}
