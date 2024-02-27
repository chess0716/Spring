package com.ccp5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ccp5.dto.Board;
import com.ccp5.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {

    @Autowired
    private BoardService boardService;
    
    @GetMapping({"/", "/index"})
    public String index(Model model) {
        List<Board> boards = boardService.list();
        model.addAttribute("boards", boards);
        return "index";
    }

  //상세보기
  	@GetMapping("view/{num}")
  	public String view(@PathVariable long num, Model model) {
  		model.addAttribute("board", boardService.view(num));
  		return "view";
  	}
  	
    
    @GetMapping("/insert")
    public String inerst() {
    	return "insert";
    }
}