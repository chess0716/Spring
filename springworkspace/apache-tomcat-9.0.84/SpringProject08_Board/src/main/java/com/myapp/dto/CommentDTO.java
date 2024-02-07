package com.myapp.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Getter;
import lombok.Setter;
@Getter  @Setter
public class CommentDTO {
	private int cnum;
	private String userid;
	private String content;
	@JsonFormat(shape = Shape.STRING, 
			  pattern ="yyyy-MM-dd", timezone ="Asia/Seoul"  )
	private Date regdate;
	private int bnum; //board 테이블과 연관 외래키
	//private BoardDTO board;

}




