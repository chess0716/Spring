package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.MemberDTO;
import com.example.demo.service.BoardService;
import com.example.demo.service.CommentService;

import jakarta.servlet.http.HttpSession;



@RequestMapping("/reply/*")
//@Controller
@RestController    //@Controller +@ResponseBody
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private BoardService boardService;
  //추가
	@PostMapping("commentInsert")
	//@ResponseBody
	public String insert(@RequestBody CommentDTO comment,
			 HttpSession session) {
		MemberDTO m =(MemberDTO)session.getAttribute("sMember");
		comment.setUserid(m.getId());
		commentService.insert(comment);
		return "success";
	}
	//전체보기
	@GetMapping("commentList/{num}")
	public HashMap<String, Object> list(@PathVariable int num) {
		List<CommentDTO> clist = commentService.getList(num);
		int replyCnt  = boardService.view(num).getReplyCnt();
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("clist", clist);
		hm.put("replyCnt", replyCnt);
		return hm;
	}
	

	//삭제
	@DeleteMapping("delete/{cnum}")
	//@ResponseBody
	public int delete(@PathVariable int cnum) {
		commentService.delete(cnum);
		return cnum;
	}
	

}




