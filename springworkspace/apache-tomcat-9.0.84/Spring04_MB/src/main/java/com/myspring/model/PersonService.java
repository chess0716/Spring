package com.myspring.model;

import java.util.List;

import com.myspring.vo.Person4;

public interface PersonService {

	public void insert(Person4 person);
	//전체보기 (검색미포함)
	public List<Person4> list();
	//전체보기 검색포함
	public List<Person4> list(String field,String word);
	
	public Person4 view(String id);

	public void update(Person4 person);

	public void delete(String id);

	
;
	
	public int count(String field,String word);

}
