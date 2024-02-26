package com.ccp4.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class BoardDTO {
    private int num;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime regdate;
    private int hitcount;
    private int replyCnt;
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