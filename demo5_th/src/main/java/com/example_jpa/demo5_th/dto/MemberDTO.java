package com.example_jpa.demo5_th.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberDTO {
	private String id;
	private String password;
	private String name;
	private String addr;
	private Date regdate;
}
