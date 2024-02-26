package com.ccp4.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ccp4.dto.CommentDTO;

@Mapper
public interface CommentMapper {
    @Insert("INSERT INTO commentboard(writer, content, regdate, bnum) VALUES(#{writer}, #{content}, #{regdate}, #{bnum})")
    public void insertComment(CommentDTO comment);

    @Select("SELECT * FROM commentboard WHERE bnum = #{bnum}")
    public List<CommentDTO> getCommentsByBnum(int bnum);

    @Delete("DELETE FROM commentboard WHERE cnum = #{cnum}")
    public void deleteComment(int cnum);

    @Select("SELECT * FROM commentboard WHERE cnum = #{cnum}")
    public CommentDTO getCommentByCnum(int cnum);

    @Select("SELECT COUNT(*) FROM commentboard WHERE bnum = #{bnum}")
    public int countCommentsByBnum(int bnum);
}
