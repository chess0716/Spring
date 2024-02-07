package com.myboard.app08;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.myapp.dto.FileBoardDTO;
import com.myapp.model.BoardService;

@Controller
public class FileController {
	@Autowired
	private BoardService boardService;
	
	//파일 추가폼
	@GetMapping("fileInsert")
	public String fileInsert() {
		return "fileBoardInsert";
	}
	//파일보드 추가
	@PostMapping("fileInsert")
	public String fileInsert(FileBoardDTO fboard) {
		String uploadFolder="D:\\jung\\springWork\\SpringProject08_Board\\src\\main\\webapp\\resources\\imgs";
	    MultipartFile f = fboard.getUpload();
			//파일 이름 중복 피하기 위해 이름 수정
			UUID  uuid = UUID.randomUUID();
			String uploadFileName = uuid.toString()+"_"+f.getOriginalFilename();
			File saveFile = new File(uploadFolder, uploadFileName);
			try {
				f.transferTo(saveFile); // 파일업로드
				fboard.setFileimage(uploadFileName); //테이블에 저장될 파일 이름
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			boardService .fileInsert(fboard);
		return "redirect:/fileList";
	}
	@GetMapping("fileList")
	public String fileList(Model model) {
		List<FileBoardDTO> arr  =boardService.fileList();
		model.addAttribute("farr",arr );
		return "fileList";
	}
	
	
	///////////////////
	@GetMapping("uploadFile")
	public void uploadFile() {}
	//파일업로드
	@PostMapping("fileUpload")
	public String fileUpload(MultipartFile[] uploads, Model model) {
		String uploadFolder="D:\\jung\\springWork\\SpringProject08_Board\\src\\main\\webapp\\resources\\imgs";
		ArrayList<String> arr = new ArrayList<String>();
		for(MultipartFile multipartFile  : uploads) {
			//파일 이름 중복 피하기 위해 이름 수정
			UUID  uuid = UUID.randomUUID();
			String uploadFileName = uuid.toString()+"_"+multipartFile.getOriginalFilename();
			File saveFile = new File(uploadFolder, uploadFileName);
			try {
				multipartFile.transferTo(saveFile);
				arr.add(multipartFile.getOriginalFilename());
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
	   model.addAttribute("fileArr", arr);
		return "fileResult";
	}
	

}
