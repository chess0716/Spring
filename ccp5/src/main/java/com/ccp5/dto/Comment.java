package com.ccp5.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;



@Entity (name="tbl_comment4")
@Data
public class Comment {

		
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long cnum;
	    
	    @ManyToOne
	    @JoinColumn(name="username")
	    private User writer;
	    
	    private String content;
	    
	    @CreationTimestamp
	    @Column(name="regdate")
	    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	    private LocalDateTime regdate;
	    
	    @ManyToOne
	    @JoinColumn(name ="bnum")
	    private BoardDTO board;
		
	    
}
