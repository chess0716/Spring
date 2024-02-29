package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.BoardDTO;
import com.example.demo.service.BoardService;

import java.util.HashMap;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/insert")
    public String insert() {
        return "insert";
    }

    @PostMapping("/insert")
    public String insert(BoardDTO board) {
        boardService.insert(board);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        HashMap<String, Object> hm = new HashMap<>();
        List<BoardDTO> boards = boardService.list(hm);
        int count = boardService.getCount(hm);
        model.addAttribute("boards", boards);
        model.addAttribute("count", count);
        return "list";
    }

    @GetMapping("/view/{num}")
    public String view(@PathVariable int num, Model model) {
        BoardDTO board = boardService.view(num);
        model.addAttribute("board", board);
        return "view";
    }

    @GetMapping("/update/{num}")
    public String updateForm(@PathVariable int num, Model model) {
        BoardDTO board = boardService.view(num);
        model.addAttribute("board", board);
        return "update";
    }

    @PostMapping("/update/{num}")
    public String update(@PathVariable int num, BoardDTO board) {
        board.setNum(num);
        boardService.update(board);
        return "redirect:/list";
    }

    @DeleteMapping("/delete/{num}")
    public ResponseEntity<String> delete(@PathVariable int num) {
        try {
            boardService.delete(num);
            return ResponseEntity.ok().body(num + "번 삭제 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 실패: " + e.getMessage());
        }
    }
}
