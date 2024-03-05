package com.ccp5.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ccp5.dto.BoardDTO;
import com.ccp5.dto.DataDTO;
import com.ccp5.dto.IngrBoard;
import com.ccp5.dto.IngrBoardListDTO;
import com.ccp5.dto.User;
import com.ccp5.repository.UserRepository;
import com.ccp5.service.BoardService;
import com.ccp5.service.IngrListService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class IngrController {
    
    @Autowired
    private IngrListService ilService;
    
    @Autowired
    private  BoardService boardService;
    @Autowired
    private UserRepository userRepository; 

    @GetMapping("/get_names")
    public String getNamesByCategory(@RequestParam("categoryId") String categoryId) {
        // categoryId를 이용하여 해당하는 카테고리에 속하는 이름 목록을 데이터베이스에서 조회하고 반환
        List<DataDTO> names = ilService.findNames(categoryId);
        // 객체를 JSON 문자열로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonNames = objectMapper.writeValueAsString(names);
            return jsonNames;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Error occurred while processing JSON";
        }
    }

    @PostMapping("/submit_all_forms")
    public ResponseEntity<?> submitAllForms(@RequestParam("formData") IngrBoardListDTO formData,
                                             @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            List<IngrBoard> forms = formData.getForms();
            if (forms != null && !forms.isEmpty()) {
                for (IngrBoard form : forms) {
                    ilService.insertIngr(form);
                }
            }

            BoardDTO boardDTO = new BoardDTO();

            if (file != null && !file.isEmpty()) {
                String imageUrl = boardService.uploadAndResizeImage(file);
                boardDTO.setImageUrl(imageUrl);
            }

            // 현재 사용자 정보 가져오기
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = userDetails.getUsername();
            // 사용자 정보로부터 User 엔티티 조회
            User user = userRepository.findByUsername(username);
            // BoardDTO의 writer 필드에 사용자 정보 설정
            boardDTO.setWriter(user);
            // 게시물 저장
            boardService.insertBoard(boardDTO);

            // 성공적인 응답 반환
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            // 오류가 발생한 경우 오류 응답 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing request.");
        }
    }




    @PostMapping("/submit_recipe")
    public String insertRecipe(@RequestBody BoardDTO board) {
        boardService.insertBoard(board);
        return "redirect:/index";
    }
    
    // 레시피 및 재료 수정
    @PostMapping("/submit_all_forms-update")
    public void submitAllFormsUpdate(@RequestBody List<IngrBoard> ingredientForms) {
        boardService.updateIngredientForms(ingredientForms);
    }
    
    @PutMapping("/submit_recipe_update")
    public void submitRecipeUpdate(@RequestBody BoardDTO recipeForm) {
        boardService.updateRecipeForm(recipeForm);
    }
    
   
    }
