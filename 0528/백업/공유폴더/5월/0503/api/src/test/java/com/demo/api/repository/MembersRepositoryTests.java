package com.demo.api.repository;

import com.demo.api.config.SecurityConfig;
import com.demo.api.entity.Members;
import com.demo.api.entity.MembersRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MembersRepositoryTests {
  @Autowired
  private MembersRepository membersRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  public void insertDummies() {
    IntStream.rangeClosed(1, 100).forEach(new IntConsumer() {
      @Override
      public void accept(int i) {
        Members members = Members.builder()
            .email("user" + i + "@example.com")
            .id("user" + i + "@example.com")
            .name("사용자" + i)
            .gender(new String[]{"여", "남"}[(int)(Math.random()*2)])
            .birthday(LocalDate.of((int)(Math.random()*20)+1990,
                (int)(Math.random()*12)+1,
                    (int)(Math.random()*28)+1))
            .fromSocial(false)
            .password(passwordEncoder.encode("1"))
            .state("0") // 0은 가입상태, 1번은 직원, 2번은 관리자
            .build();
        members.addMemberRole(MembersRole.USER);
        if(i>=80) members.addMemberRole(MembersRole.MANAGER);
        if(i>=90) members.addMemberRole(MembersRole.ADMIN);
        membersRepository.save(members);
      }
    });
  }
}