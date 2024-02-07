package com.myspring.app09;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myspring.dto.BoardDTO;
import com.myspring.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/board/*")
@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("insert")
    public void insert() {
        
    }

    @PostMapping("insert")
    public String insert(BoardDTO board) {
        boardService.insert(board);
        return "redirect:/list"; 
    }
    
    @GetMapping("list")
    public String list(Model model) {
    	List<BoardDTO> boardList = boardService.list(); 
        int count = boardService.count(); 
        model.addAttribute("boardList", boardList);
        model.addAttribute("count", count);
        return "board/list";
       
    }
    @GetMapping("view/{num}")
	public String view(@PathVariable int num, Model model) {
		model.addAttribute("board", boardService.view(num));
		return "board/view";
	}
    @GetMapping("update/{num}")
	 public String update(@PathVariable int num, Model model) {
		 model.addAttribute("board", boardService.list());
			return "update";
	 }
	 //�닔�젙
	 @PutMapping("update")
	 @ResponseBody
	 public int update(@RequestBody BoardDTO board) {
		 boardService.update(board);
		 return board.getNum();
	 }
	 
	 
	 
	
	@DeleteMapping("delete/{num}")
	@ResponseBody
	public int delete(@PathVariable int num) {
		boardService.delete(num);
		return num;
	}


	    @GetMapping("join")
	    public String showJoinForm() {
	        // join.jsp와 같은 회원가입 폼을 보여주는 뷰를 반환합니다.
	        return "join";
	    }
	    @GetMapping("updateMember")
	    public String updateMember(Model model) {
	       
	        return "updateMember"; 
	    }
}

