package com.demo.gram.controller;

import com.demo.gram.dto.ChatRoomDTO;
import com.demo.gram.dto.MembersDTO;
import com.demo.gram.dto.PostDTO;
import com.demo.gram.dto.ResponseDTO;
import com.demo.gram.entity.Members;
import com.demo.gram.service.MembersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/members/")
@RequiredArgsConstructor
public class MembersController {

  private final MembersService membersService;

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

  @DeleteMapping(value = "/delete/{num}", produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> delete(@PathVariable("num") Long num, HttpServletRequest request) {
    String authorizationHeader = request.getHeader("Authorization");
    log.info("Authorization Header: {}", authorizationHeader);
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      String token = authorizationHeader.substring(7);
      try {
        Long userId = membersService.getUserIdFromToken(token);
        log.info("User ID from token: {}", userId);
        if (!userId.equals(num)) {
          log.warn("Unauthorized delete attempt. Token User ID: {}, Requested User ID: {}", userId, num);
          return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        membersService.removeMembers(num);
        log.info("User ID {} successfully deleted", num);
        return new ResponseEntity<>("removed", HttpStatus.OK);
      } catch (Exception e) {
        log.error("Failed to delete member: {}", e.getMessage());
        return new ResponseEntity<>("Failed to delete member", HttpStatus.INTERNAL_SERVER_ERROR);
      }
    } else {
      log.warn("Missing or invalid Authorization header");
      return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
  }

  @GetMapping(value = "/get/{num}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<MembersDTO> read(@PathVariable("num") Long num) {
    log.info("read..." + num);
    return new ResponseEntity<>(membersService.get(num), HttpStatus.OK);
  }

  @GetMapping(value = "/get/all", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<MembersDTO>> getAll() {
    log.info("getList...");
    return new ResponseEntity<>(membersService.getAll(), HttpStatus.OK);
  }

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

  @GetMapping(value = "/{userId}/posts", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<PostDTO>> getUserPosts(@PathVariable("userId") Long userId, HttpServletRequest request) {
    String authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      String token = authorizationHeader.substring(7);
      try {
        Long tokenUserId = membersService.getUserIdFromToken(token);
        if (!userId.equals(tokenUserId)) {
          return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<PostDTO> userPosts = membersService.getUserPosts(userId);
        return ResponseEntity.ok(userPosts);
      } catch (Exception e) {
        log.error("Failed to get user posts", e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    } else {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }

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
