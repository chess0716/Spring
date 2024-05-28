package com.example.hacker.repository;

import com.example.hacker.dto.ChatRoomDTO;
import com.example.hacker.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Long> {

  // 특정 사용자가 참여한 채팅방을 가져오는 메서드
  List<ChatRoomEntity> findByMember_Mno(Long mno);

}
