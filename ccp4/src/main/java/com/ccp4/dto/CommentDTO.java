package com.ccp4.dto;


import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CommentDTO {
	
    private int cnum;
    private String writer;
    private String content;
    private LocalDateTime regdate;
    private int bnum;

}
