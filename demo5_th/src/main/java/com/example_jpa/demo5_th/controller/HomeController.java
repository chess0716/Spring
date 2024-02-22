package com.example_jpa.demo5_th.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example_jpa.demo5_th.dto.BoardDTO;
import com.example_jpa.demo5_th.dto.PageVO;
import com.example_jpa.demo5_th.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	private final BoardService boardService;
	//전체보기==>페이징
	@GetMapping("list")
	public String list(Model model,String pageNum) {
		 int currentPage = pageNum == null ? 1 : Integer.parseInt(pageNum);
		 int pageSize =3;
		 int pageStart = (currentPage-1) *pageSize;
		 int count = boardService.getCount();
		 PageVO page = new PageVO(count, pageSize, currentPage);
		 model.addAttribute("boards", boardService.pagelist(pageStart, pageSize)) ;
		 model.addAttribute("p", page);
		 model.addAttribute("count", count);
		 return "board/list";
	 }
	
	 //전체보기
//	 @GetMapping("list")
//	 public String list(Model model) {
//		 model.addAttribute("boards", boardService.list()) ;
//		 model.addAttribute("count", boardService.getCount());
//		 return "board/list";
//	 }
	
 @GetMapping("/")
 public String home() {
	 return "home";
 }
 //추가폼
 @GetMapping("insert")
 public String insert() {
	 return "board/insert";
 }
 //추가
 @PostMapping("insert")
 public String insert(BoardDTO board) {
	 boardService.insert(board);
	 return "redirect:list";
 }

	 //상세보기
	 @GetMapping("view/{num}")
	 public String view(@PathVariable int num, Model model) {
		 model.addAttribute("board", boardService.view(num));
		 return "board/view";
	 }
	 
	 
	 
}
