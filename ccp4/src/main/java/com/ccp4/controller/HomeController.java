package com.ccp4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ccp4.dto.BoardDTO;
import com.ccp4.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    private BoardService boardService;
    
    @GetMapping({"/", "/index"})
    public String index(Model model) {
        List<BoardDTO> boards = boardService.getAllBoards();
        model.addAttribute("boards", boards);
        return "index";
    }

    @GetMapping("/view/{num}")
    public String view(@PathVariable("num") int num, Model model) {
     
        BoardDTO board = boardService.getBoardByNum(num);
        model.addAttribute("board", board);
        return "view";
    }
    
    @GetMapping("/insert")
    public String inerst() {
    	return "insert";
    }
}