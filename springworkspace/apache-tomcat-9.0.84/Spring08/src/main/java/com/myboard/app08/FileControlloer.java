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
public class FileControlloer {
	@Autowired
	private BoardService boardService;
	//파일 추가폼
	@GetMapping("fileInsert")
	public String fileInsert() {
		return "fileBoardInsert";
	}
	
	
	 // 파일 추가
    @PostMapping("fileInsert")
    public String fileInsert(FileBoardDTO fboard) {
        MultipartFile f = fboard.getUpload();

        // 파일이 업로드되지 않았을 경우 예외 처리
        if (f.isEmpty()) {
            // 파일이 선택되지 않았음을 사용자에게 알릴 수 있도록 처리
            return "redirect:fileInsert?error=emptyfile";
        }

        String uploadFolder = "C:/Users/it/Desktop/sts/sts-3.9.18.RELEASE/dropins/springworkspace/apache-tomcat-9.0.84/Spring08/src/main/webapp/resources/imgs";

        try {
            // 파일 이름 중복 피하기 위해 UUID 생성
            UUID uuid = UUID.randomUUID();
            String uploadFileName = uuid.toString() + "_" + f.getOriginalFilename();
            File saveFile = new File(uploadFolder + "/" + uploadFileName); // 저장 경로 지정

            // 파일을 서버에 저장
            f.transferTo(saveFile);
            fboard.setFileimage(uploadFileName);

            // 파일 정보를 DB에 저장
            boardService.fileInsert(fboard);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            // 파일 저장 중 예외 발생시 예외 처리
            return "redirect:fileInsert";
        }

        return "redirect:fileList";
    }

    // 파일 목록 표시
    @GetMapping("fileList")
    public String fileList(Model model) {
        List<FileBoardDTO> fileList = boardService.fileList();
        model.addAttribute("files", fileList);
        return "fileList";
    }

	
	
	//////////////////
	@GetMapping("uploadFile")
	public void uploadFile() {
		
	}
	 @PostMapping("fileUpload")
	    public String fileUpload(MultipartFile[] uploads, Model model) {
	        String uploadFolder = "C:/Users/it/Desktop/sts/sts-3.9.18.RELEASE/dropins/springworkspace/apache-tomcat-9.0.84/Spring08/src/main/webapp/resources/imgs";
	        ArrayList<String> arr = new ArrayList<String>();
	        for (MultipartFile multipartFile : uploads) {
	            // 파일 이름 중복 피하기 위해 이름 수정
	            UUID uuid = UUID.randomUUID();
	            String uploadFileName = uuid.toString() + "_" + multipartFile.getOriginalFilename();
	            File saveFile = new File(uploadFolder + "/" + uploadFileName); // 저장 경로 지정

	            try {
	                if (!saveFile.getParentFile().exists()) { // 저장할 폴더가 존재하지 않으면 생성
	                    saveFile.getParentFile().mkdirs();
	                }
	                multipartFile.transferTo(saveFile); // 파일을 서버에 저장
	                arr.add(multipartFile.getOriginalFilename());
	            } catch (IllegalStateException | IOException e) { // 예외처리
	                e.printStackTrace();
	            }
	        }

	        // 파일 목록을 Model 객체에 저장하여 뷰로 전달합니다.
	        model.addAttribute("fileList", arr);

	        return "FileResult"; // 파일 업로드 결과를 보여줄 JSP 페이지로 이동합니다.
	    }
}
