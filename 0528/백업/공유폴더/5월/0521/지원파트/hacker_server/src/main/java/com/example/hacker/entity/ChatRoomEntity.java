package com.example.hacker.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChatRoomEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cno; // 채팅방 번호 (자동증가)

  @Column(unique = true) // 중복 방지
  private String id;
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "mno") // 멤버 번호를 외래 키로 사용
  private Members member;


}
