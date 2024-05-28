package com.demo.api.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MembersRepositoryTests {
  @Autowired
  private MembersRepository membersRepository;

  @Test
  public void insertDummies() {
    IntStream.rangeClosed(1, 100).forEach(new IntConsumer() {
      @Override
      public void accept(int value) {

      }
    });
  }

}