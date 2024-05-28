package com.example.hacker.controller;

import com.example.hacker.dto.ChatRoomDTO;
import com.example.hacker.dto.MembersDTO;
import com.example.hacker.dto.MyPostDTO;
import com.example.hacker.dto.ResponseDTO;
import com.example.hacker.entity.Members;
import com.example.hacker.service.MembersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/members/")
@RequiredArgsConstructor
public class MembersController {

  private final MembersService membersService;

  // 회원수정
  @PutMapping(value = "/update", produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> update(@RequestBody MembersDTO membersDTO) {
    log.info("update..." + membersDTO);
    try {
      membersService.updateMembersDTO(membersDTO);
      return new ResponseEntity<>("modified", HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      log.error("Invalid request data: " + e.getMessage());
      return new ResponseEntity<>("Invalid request data", HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      log.error("Failed to update member: " + e.getMessage());
      return new ResponseEntity<>("Failed to update member", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // 특정회원삭제
  @DeleteMapping(value = "/delete/{num}", produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> delete(@PathVariable("num") Long num, HttpServletRequest request) {
    String authorizationHeader = request.getHeader("Authorization");
    log.info("Authorization Header: {}", authorizationHeader); // 로그 추가
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      String token = authorizationHeader.substring(7);
      try {
        Long userId = membersService.getUserIdFromToken(token);
        log.info("User ID from token: {}", userId); // 로그 추가
        if (!userId.equals(num)) {
          log.warn("Unauthorized delete attempt. Token User ID: {}, Requested User ID: {}", userId, num); // 로그 추가
          return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        membersService.removeMembers(num);
        log.info("User ID {} successfully deleted", num); // 로그 추가
        return new ResponseEntity<>("removed", HttpStatus.OK);
      } catch (Exception e) {
        log.error("Failed to delete member: {}", e.getMessage()); // 로그 추가
        return new ResponseEntity<>("Failed to delete member", HttpStatus.INTERNAL_SERVER_ERROR);
      }
    } else {
      log.warn("Missing or invalid Authorization header"); // 로그 추가
      return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
  }

  // 특정 회원정보 추출
  @GetMapping(value = "/get/{num}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<MembersDTO> read(@PathVariable("num") Long num) {
    log.info("read..." + num);
    return new ResponseEntity<>(membersService.get(num), HttpStatus.OK);
  }

  // 전체 회원정보 추출
  @GetMapping(value = "/get/all", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<MembersDTO>> getAll() {
    log.info("getList...");
    return new ResponseEntity<>(membersService.getAll(), HttpStatus.OK);
  }

  // 로그인된 사용자 정보 가져오기
  @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Members> getCurrentUser(HttpServletRequest request) {
    log.info("///////////");
    String authorizationHeader = request.getHeader("Authorization");
    log.info("Authorization Header: " + authorizationHeader);
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      String token = authorizationHeader.substring(7);
      log.info("토큰: " + token);
      try {
        log.info("트라이 들어왔음");
        Members loggedInUser = membersService.getCurrentLoggedInUser(token);
        log.info("여기까지 왔음");
        return ResponseEntity.ok(loggedInUser);
      } catch (Exception e) {
        log.error("Failed to get current user", e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    } else {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }

  // 내가 작성한 게시글 불러오기 (마이페이지)
  @GetMapping(value = "/{userId}/posts", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<MyPostDTO>> getUserPosts(@PathVariable("userId") Long userId, HttpServletRequest request) {
    String authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      String token = authorizationHeader.substring(7);
      try {
        Long tokenUserId = membersService.getUserIdFromToken(token);
        if (!userId.equals(tokenUserId)) {
          return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<MyPostDTO> userPosts = membersService.getUserPosts(userId);
        return ResponseEntity.ok(userPosts);
      } catch (Exception e) {
        log.error("Failed to get user posts", e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    } else {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }

  // 내가 참여한 채팅방 불러오기 (마이페이지)
  @GetMapping(value = "/{userId}/chatrooms", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ChatRoomDTO>> getUserChatRooms(@PathVariable("userId") Long userId, HttpServletRequest request) {
    String authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      String token = authorizationHeader.substring(7);
      try {
        Long tokenUserId = membersService.getUserIdFromToken(token);
        if (!userId.equals(tokenUserId)) {
          return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<ChatRoomDTO> userChatRooms = membersService.getUserChatRooms(userId);
        return ResponseEntity.ok(userChatRooms);
      } catch (Exception e) {
        log.error("Failed to get user chat rooms", e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    } else {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }

  // 회원탈퇴 (DB에서 값 삭제)
  @DeleteMapping("/me")
  public ResponseEntity<ResponseDTO> deleteAccount(HttpServletRequest request) {
    String authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      String token = authorizationHeader.substring(7);
      try {
        Long userId = membersService.getUserIdFromToken(token);
        membersService.removeMembers(userId);
        return new ResponseEntity<>(new ResponseDTO("Account deleted", true), HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(new ResponseDTO("Failed to delete account", false), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    } else {
      return new ResponseEntity<>(new ResponseDTO("Unauthorized", false), HttpStatus.UNAUTHORIZED);
    }
  }
}
