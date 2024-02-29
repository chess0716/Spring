package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.FileBoardDTO;
import com.example.demo.service.BoardService;

@Controller
public class FileController {
	@Autowired
	private BoardService boardService;
	
	//파일추가폼
	@GetMapping("fileInsert")
	public String fileInsert() {
		return "fileBoardInsert";
	}
	//파일추가
	@PostMapping("fileInsert")
	public String fileInsert(FileBoardDTO fboard) {
		boardService.fileInsert(fboard);
		return "redirect:/fileList";
	}
	@GetMapping("fileList")
	public String fileList(Model model) {
		model.addAttribute("farr", boardService.fileList());
		return "fileList";
	}

}

