package com.example.demo.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data
public class FileBoardDTO {
	//번호, 제목, 저자, 내용, 이미지
		private int num;
		private String title;
		private String writer;
		private String content;
		private MultipartFile  upload;   //
		private String fileimage;
		

}
