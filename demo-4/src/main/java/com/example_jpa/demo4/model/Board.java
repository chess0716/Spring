package com.example_jpa.demo4.model;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
@Entity (name="tbl_board4")
@Getter @Setter
public class Board {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long num;
	private String title;
	private String content;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="regdate")
	private Date regdate;
	private Integer hitcount;
	private Integer replyCnt;
	@OneToMany(mappedBy = "board",cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("board")
	private List<Comment> comments;
	
	@JoinColumn (name="user_id")
	@ManyToOne
	private User user;
	
	@PrePersist
	public void prePersist() {
	    this.hitcount = (this.hitcount == null) ? 0 : this.hitcount;
	    this.replyCnt = (this.replyCnt == null) ? 0 : this.replyCnt;
	}

	
	
	
	
	
}
