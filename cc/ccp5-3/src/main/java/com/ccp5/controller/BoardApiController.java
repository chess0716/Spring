package com.ccp5.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ccp5.dto.BoardDTO;
import com.ccp5.dto.IngrBoard;
import com.ccp5.dto.UpdatePriceRequest;
import com.ccp5.repository.BoardRepository;
import com.ccp5.service.BoardService;
import com.ccp5.service.IngrListService;
import com.ccp5.service.IngrListService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@RestController
@RequestMapping("/api/boards")
public class BoardApiController {
	
	@Autowired
	BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;
    @Autowired
	private IngrListService ilService;

    @GetMapping("/search")
    public ResponseEntity<List<BoardDTO>> searchBoards(@RequestParam String title) {
        // 검색 로직 구현
        List<BoardDTO> searchResults =boardService.searchByTitle(title);
        return ResponseEntity.ok(searchResults);
    }
   
    @GetMapping("/list")
    public ResponseEntity<?> getAllBoards() {
        List<BoardDTO> boards = boardService.getAllBoards();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        try {
            if (boards != null && !boards.isEmpty()) {
                String jsonNames = objectMapper.writeValueAsString(boards);
                return ResponseEntity.ok(jsonNames);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing JSON");
        }
    }


    @GetMapping("/{num}")
    public ResponseEntity<BoardDTO> getBoardByNum(@PathVariable int num) {
        BoardDTO board = boardService.getBoardByNum(num);
        if (board != null) {
            return ResponseEntity.ok(board);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
 // 모바일로 재료 리스트 데이터 보내기
 	@GetMapping("/{num}/ingredients")
 	public ResponseEntity<List<IngrBoard>> getIngredientsForBoard(@PathVariable int num) {
 		BoardDTO board = boardService.getBoardByNum(num);
 		List<IngrBoard> ingrBoards = ilService.findByTitle(board.getTitle());
 		if (ingrBoards != null && !ingrBoards.isEmpty()) {
 			return ResponseEntity.ok(ingrBoards);
 		} else {
 			return ResponseEntity.notFound().build();
 		}
 	}

	// 모바일로 총 가격 데이터 보내기
	@GetMapping("/{num}/totalPrice")
	public ResponseEntity<Integer> getTotalPrice(@PathVariable int num) {
		Integer totalPrice = boardRepository.calculateTotalPriceByNum(num);
		if (totalPrice != null) {
			return ResponseEntity.ok(totalPrice);
		} else {
			return ResponseEntity.notFound().build();
		}
	}


    @PostMapping
    public ResponseEntity<Void> insertBoard(@RequestBody BoardDTO boardDTO) {
        boardService.insertBoard(boardDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{boardNum}/calculatePrice")
    @ResponseBody
    public ResponseEntity<Integer> updatePrice(@RequestBody UpdatePriceRequest updatePriceRequest, @PathVariable int boardNum) {
        String ingredientName = updatePriceRequest.getIngredientName();
        boolean isOwned = updatePriceRequest.isOwned();
        System.out.println("ingredientName : " + ingredientName);
        System.out.println("isOwned : " + isOwned);

        Integer price;
        if (!isOwned) {
            price = boardRepository.calculateTotalPriceByIngredientName(ingredientName, boardNum);
        } else {
            price = boardRepository.calculateTotalPriceByIngredientName(ingredientName, boardNum) * -1;
        }
        System.out.println("totalprice : " + price);
        return ResponseEntity.ok(price);
    }



    @GetMapping("/category/{categoryId}")
    public List<BoardDTO> getBoardsByCategory(@PathVariable Long categoryId) {
        return boardRepository.findByCategoryId(categoryId);
    }
}
