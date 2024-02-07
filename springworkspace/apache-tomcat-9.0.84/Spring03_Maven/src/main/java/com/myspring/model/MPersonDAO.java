package com.myspring.model;

import java.util.List;

public interface MPersonDAO {
	//추가
	public void per_insert(Person3 person);
	//전체보기
	public List<Person3> per_list();
	//상세보기
	public Person3 per_view(String id);
	//수정
	public void per_update(Person3 person);
	//삭제
	public void per_delete(String id);
	//개수
	public int per_count();
	
	public List<Person3> per_list(String field, String word);
	public int per_count(String field, String word);

}