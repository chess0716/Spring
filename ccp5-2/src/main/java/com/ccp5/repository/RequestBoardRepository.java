package com.ccp5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccp5.dto.RequestBoard;


@Repository
public interface RequestBoardRepository extends JpaRepository<RequestBoard, Integer> {

}
