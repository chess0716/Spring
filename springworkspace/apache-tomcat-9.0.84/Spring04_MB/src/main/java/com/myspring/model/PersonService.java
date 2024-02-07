package com.myspring.model;

import java.util.List;

import com.myspring.vo.Person4;

public interface PersonService {

	public void insert(Person4 person);
	//��ü���� (�˻�������)
	public List<Person4> list();
	//��ü���� �˻�����
	public List<Person4> list(String field,String word);
	
	public Person4 view(String id);

	public void update(Person4 person);

	public void delete(String id);

	
;
	
	public int count(String field,String word);

}
