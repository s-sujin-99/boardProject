package com.hr.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BoardDto {
	private int boardNo; 
	private String title; 
	private String content; 
	private String writer; 
	private Date regDate; 
}
