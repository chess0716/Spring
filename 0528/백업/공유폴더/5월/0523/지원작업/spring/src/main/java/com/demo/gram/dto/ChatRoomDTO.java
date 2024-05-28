package com.demo.gram.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDTO {
  private Long id;
  private String name;
  private LocalDateTime createdAt;
  private Long postId;
  private Set<Long> memberIds;
}
