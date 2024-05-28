package com.demo.api.entity;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Members extends BaseEntity{

  private Long mno;

  private String email;
  private String id;
  private String password;
  private String name;
  private String gender;
  private String zipcode;
  private String address;
  private String imgName;
  private String imgUuid;
  private String imgPath;
  private LocalDate birthday; // 생년월일
  private LocalDate stateday; // 상태변경일
  private boolean fromSocial;

}
