package com.zeus.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = {"regDate","COUNT"})
@Builder

public class Board {
	//멤변 (게시판)
    @NonNull
    private Integer boardNo; 
    private String title;
    private String content;
    private String writer; 
    private LocalDateTime regDate; 
    private int COUNT;
    //생성자
}