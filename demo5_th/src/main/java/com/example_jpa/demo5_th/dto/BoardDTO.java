package com.example_jpa.demo5_th.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter  @Setter
public class BoardDTO {
	private int num;
	private String title;
	private String writer;
	private String content;
	private Date regdate;
	private int hitcount;
	private int replyCnt;
}
