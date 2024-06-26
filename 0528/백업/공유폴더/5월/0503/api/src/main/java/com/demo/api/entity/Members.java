package com.demo.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Members extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long mno;

  @Column(unique = true) // 중복 방지
  private String email;

  @Column(unique = true)
  private String id;

  @Column(nullable = false)
  private String password;

  private String state;
  private String name;
  private String gender;
  private String mobile;
  private String zipcode;
  private String address;
  private String imgName;
  private String imgUuid;
  private String imgPath;
  private LocalDate birthday; // 생년월일
  private LocalDate stateday; // 상태변경일
  private boolean fromSocial;
  private LocalDateTime regDate;
  private LocalDateTime modDate;

  @ElementCollection(fetch = FetchType.LAZY)
  @Builder.Default
  private Set<MembersRole> roleSet = new HashSet<>();

  public void addMemberRole(MembersRole membersRole) {
    roleSet.add(membersRole);
  }


}
