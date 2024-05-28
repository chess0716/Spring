package com.demo.gram.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity(name = "Posts")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = true)
  private Members user;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private Long numberOfUsers;

  @Column(nullable = false)
  private LocalDate endDate;

  @Column(nullable = true)
  private Long viewCount = 0L;

//  @ManyToMany
//  @JoinTable(
//      name = "post_tags",
//      joinColumns = @JoinColumn(name = "post_id"),
//      inverseJoinColumns = @JoinColumn(name = "tag_id")
//  )
//  private Set<Tag> tags = new HashSet<>();


}