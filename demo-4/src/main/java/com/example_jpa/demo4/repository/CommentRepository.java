package com.example_jpa.demo4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example_jpa.demo4.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    @Query("select sc from tbl_comment4 sc where sc.board.num = ?1")
    public List<Comment> findByBnum(long bnum);

    @Query("delete from tbl_comment4 sc where sc.cnum = :cnum")
    void deleteByCnum(@Param("cnum") long cnum);
}
