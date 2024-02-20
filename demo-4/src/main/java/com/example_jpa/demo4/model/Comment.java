package com.example_jpa.demo4.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity (name="tbl_comment4")
@Getter @Setter
public class Comment {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cnum;
	private String content;
	@CreationTimestamp
	@Temporal (TemporalType.TIMESTAMP)
	@Column(name="regdate")
	@JsonFormat(pattern = "yyyy-mm-dd",timezone = "Asia/Seoul")
	private Date regdate;
	
	//User
	@ManyToOne
	@JoinColumn (name="user_id")
	private User user;
	
	//Board
	@ManyToOne
	@JoinColumn (name ="bnum")
	private Board board;
	
	
	
	
	
	
	
}
