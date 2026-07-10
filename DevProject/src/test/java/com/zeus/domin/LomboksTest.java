package com.zeus.domin;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LomboksTest {
	@Test
	public void testNoArgsConstructor() {
//		Board board = new Board();
		Board board = Board.builder()
				.boardNo(1)
				.title("테스트1")
				.content("테스트 내용1")
				.writer("홍길동")
				.regDate(new Date())
				.build();
		System.out.println(board);
	}

	@Test
	public void testRequiredArgsConstructor() {
		Board board = new Board(1,"테스트 제목");

		System.out.println(board);
	}

	@Test
	public void testGetter() {
//		Board board = new Board();

//		System.out.println(board.getTitle()); // 결과 : Null
	}

	@Test
	public void testSetter() {
//		Board board = new Board();
//		board.setTitle("게시판 제목");

//		System.out.println(board.getTitle()); // 결과 : 게시판 제목
//		System.out.println(board.toString()); // 결과 : 게시판 전체 내용
	}
	@Test
	public void testAllArgsConstructor() {
		Board board = new Board(1, "테스트 제목1", "테스트 내용1", "홍길동", new Date());
		System.out.println(board);
	}
	
	@Test
	public void testToStringExclude() {
		Member member = new Member();
		System.out.println(member);
	}
}
