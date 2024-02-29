package com.ccp5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ccp5.dto.BoardDTO;
import com.ccp5.dto.IngrBoard;
import com.ccp5.service.BoardService;
import com.ccp5.service.IngrListService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private IngrListService ilService;
    
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
    
    // 레시피 등록
    @GetMapping("/insert")
    public String inerst(Model model) {
    	model.addAttribute("categories", ilService.category());
    	return "insert";
    }
    
    // 레시피 수정폼
    @GetMapping("/update/{num}")
    public String update(@PathVariable int num, Model model) {
        BoardDTO board = boardService.getBoardByNum(num);
        List<IngrBoard> ingrBoards = ilService.findByTitle(board.getTitle());
        model.addAttribute("categories", ilService.category());
        model.addAttribute("iboard", ingrBoards);
        model.addAttribute("board", board);
        return "update";
    }
    
}