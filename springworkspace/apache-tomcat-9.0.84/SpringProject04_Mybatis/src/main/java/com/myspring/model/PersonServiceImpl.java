package com.myspring.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspring.vo.Person4;

@Service
public class PersonServiceImpl  implements PersonService{
 @Autowired
	private PersonDAO dao;
	//추가
	@Override
	public void insert(Person4 person) {
		dao.dao_insert("personInsert", person);
		
	}

	@Override
	public List<Person4> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person4 view(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Person4 person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

}
