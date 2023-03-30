package kr.board.entity;

import lombok.Data;

// Lombok API : setter, getter, toString 등 자동생성 라이브러리
@Data // Lombok api 
public class Board {
	private int idx; // 번호
	private String title; // 제목
	private String content; // 내용
	private String writer; // 작성자
	private String indate; // 작성일
	private int count; // 조회수
	
	// setter, getter
	
}
