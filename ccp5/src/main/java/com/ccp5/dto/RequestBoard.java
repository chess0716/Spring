package com.ccp5.dto;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "request_board")
@Getter @Setter
public class RequestBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;

    private String title;

    private String writer;

    @Column(name = "content")
    private String Content;

    @Column(name = "regdate")
    private Date regdate;
}
