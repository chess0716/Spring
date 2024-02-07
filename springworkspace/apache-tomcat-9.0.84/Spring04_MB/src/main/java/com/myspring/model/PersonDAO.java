package com.myspring.model;

import java.util.HashMap;
import java.util.List;

import com.myspring.vo.Person4;

public interface PersonDAO {
	
	public void dao_insert(String mid, Person4 person);
	// 검색 미포함
	public List<Person4> dao_list(String mid);
	// 검색 포함
	public List<Person4> dao_list(String mid,HashMap<String, String>hm);
	
	public Person4 dao_view(String mid, String id);
	
	public void dao_update(String mid, Person4 person);
	
	public void dao_delete(String mid, String id);
	

	
	public int dao_count(String mid,HashMap<String, String>hm);
	
}
