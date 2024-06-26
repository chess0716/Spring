package com.demo.gram.dto;

import com.demo.gram.entity.Members;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

  private Long id;
  private Members user;
  private String title;
  private String content;
  private Long numberOfUsers;
  private String regdate;
  private String endDate;
  private Long viewCount;


}
