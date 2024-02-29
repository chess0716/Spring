package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.BoardMapper;
import com.example.demo.dto.BoardDTO;
import com.example.demo.dto.FileBoardDTO;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private BoardRepository boardRepository;

	@Override
	public void insert(BoardDTO board) {
		
		boardRepository.dao_insert(board);
	}

	@Override
	public List<BoardDTO> list(HashMap<String, Object> hm) {
		// TODO Auto-generated method stub
		return boardRepository.dao_list(hm);
	}

	@Override
	public BoardDTO view(int num) {
		// TODO Auto-generated method stub
		return boardRepository.dao_view(num);
	}

	@Override
	public void update(BoardDTO board) {
		
		boardRepository.dao_update(board);
		
	}

	@Override
	public void delete(int num) {
		
		boardRepository.dao_delete(num);
		
	}

	@Override
	public int getCount(HashMap<String, Object> hm) {
		
		return boardRepository.getCount(hm);
	}
	@Override
	public void fileInsert(FileBoardDTO fboard) {
	    UUID uuid = UUID.randomUUID();
	    MultipartFile file = fboard.getUpload();
	    String uploadFolder = "C:\\Users\\it\\Documents\\workspace-spring-tool-suite-4-4.21.0.RELEASE\\demo-1\\src\\main\\resources\\static\\images";
	    String uploadFileName = "";
	    
	    if (!file.isEmpty()) {
	        uploadFileName = uuid.toString() + "_" + file.getOriginalFilename();
	        File saveFile = new File(uploadFolder, uploadFileName);
	        try {
	            file.transferTo(saveFile);
	            fboard.setFileimage(uploadFileName);
	        } catch (IllegalStateException | IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    boardMapper.fileInsert(fboard);
	}



	@Override
	public List<FileBoardDTO> fileList() {
	 	return boardMapper.fileList();
	}

	@Override
	public void updateReplyCnt(long num) {
		// TODO Auto-generated method stub
		
	}
}
