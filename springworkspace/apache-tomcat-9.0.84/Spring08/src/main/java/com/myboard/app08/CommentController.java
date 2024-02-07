package com.myboard.app08;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.dto.CommentDTO;
import com.myapp.model.CommentService;

@RequestMapping("/reply/*")
//@Controller
@RestController    //@Controller +@ResponseBody
public class CommentController {
	@Autowired
	private CommentService commentService;
  //추가
	@PostMapping("commentInsert")
	//@ResponseBody
	public String insert(@RequestBody CommentDTO comment) {
		comment.setUserid("aa");
		commentService.insert(comment);
		return "success";
	}
	//전체보기
	@GetMapping("commentList/{num}")
	//@ResponseBody
	public List<CommentDTO> list(@PathVariable int num) {
		List<CommentDTO> clist = commentService.getList(num);
		return clist;
	}
	//삭제
	@DeleteMapping("delete/{cnum}")
	//@ResponseBody
	public int delete(@PathVariable int cnum) {
		commentService.delete(cnum);
		return cnum;
	}
	

}



