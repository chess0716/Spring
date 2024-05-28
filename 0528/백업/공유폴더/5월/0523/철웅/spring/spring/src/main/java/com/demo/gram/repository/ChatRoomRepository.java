package com.demo.gram.repository;

import com.demo.gram.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

  @Query("SELECT cr FROM ChatRoom cr JOIN FETCH cr.members WHERE cr.post.id = :postId")
  Optional<ChatRoom> findByPostIdWithMembers(@Param("postId") Long postId);
  Optional<ChatRoom> findByPostId(Long postId);
  @Query("SELECT cr FROM ChatRoom cr JOIN FETCH cr.members WHERE cr.id = :chatRoomId")
  Optional<ChatRoom> findByIdWithMembers(@Param("chatRoomId") Long chatRoomId);
}
