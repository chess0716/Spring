package com.ccp4.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestBoard {
	private int num;
	private String title;
	private String writer;
	private String Content;
	private Date regdate;
}
