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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "board")
@Getter
@Setter
public class BoardDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;
    private String title;
    private String writer;
    private String content;
    @CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="regdate")
	@JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private LocalDateTime regdate;
    private int hitcount;
    @Column(name = "reply_cnt")
    private int replyCnt;
    @Column(name = "totalprice")
    private int totalprice;
    
    @Override
    public String toString() {
        return "BoardDTO{" +
                "num=" + num +
                ", title='" + title + '\'' +
                ", writer='" + writer + '\'' +
                ", content='" + content + '\'' +
                ", regdate=" + regdate +
                ", hitcount=" + hitcount +
                ", replyCnt=" + replyCnt +
                ", totalprice=" + totalprice +
                '}';
    }

}