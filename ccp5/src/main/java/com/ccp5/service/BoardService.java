package com.ccp5.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ccp5.dto.BoardDTO;
import com.ccp5.dto.IngrBoard;
import com.ccp5.repository.BoardRepsitory;
import com.ccp5.repository.IngrListRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	public final BoardRepsitory boardRepo;
	public final IngrListRepository ilRepo;

	// 메인
	public List<BoardDTO> getAllBoards() {
		// JPA Repository 사용
		return boardRepo.findAll();
	}

	// 뷰
	public BoardDTO getBoardByNum(int num) {
		// JPA Repository 사용
		return boardRepo.findById(num).orElse(null);
	}

	// 레시피 작성
	public void insertBoard(BoardDTO board) {
		// JPA Repository 사용
		boardRepo.save(board);
	}

	// 레시피 수정
	@Transactional
	public void updateIngredientForms(List<IngrBoard> ingredientForms) {
		for (IngrBoard ingrBoard : ingredientForms) {
			ilRepo.deleteByTitle(ingrBoard.getTitle());
		}
		ilRepo.deleteByNull();
		
		List<IngrBoard> filteredIngredientForms = ingredientForms.stream().filter(ingrBoard -> ingrBoard.getUnit() != 0)
				.collect(Collectors.toList());
		ilRepo.saveAll(filteredIngredientForms);
	}
	@Transactional
	public void updateRecipeForm(BoardDTO recipeForm) {
		boardRepo.save(recipeForm);
	}
	
	// 레시피 삭제
	@Transactional
	public void deleteRecipe(int num) {
		boardRepo.deleteById(num);		
	}
	@Transactional
	public void deleteIngredientForms(List<IngrBoard> ingredientForms) {
		for (IngrBoard ingrBoard : ingredientForms) {
			ilRepo.deleteByTitle(ingrBoard.getTitle());
		}
	}
}
