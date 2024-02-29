package com.ccp5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ccp5.dto.BoardDTO;

@Repository
public interface BoardRepsitory extends JpaRepository<BoardDTO, Integer> {
	@Query(value = "select (SUM(ROUND(b.unit * (c.cost / c.unit), -1))) from board a, ingredients_board b, data c where a.title=b.title and b.name=c.name and a.num =:num", nativeQuery = true)
	Integer calculateTotalPriceByNum(@Param("num") int num);
}
