package com.myspring.model;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.myspring.vo.Person4;

@Repository
public class PersonDAOImpl  implements PersonDAO{
  @Autowired
	private SqlSession sqlMapper;
	@Override
	public void dao_insert(String mid, Person4 person) {
		sqlMapper.insert(mid, person);
		
	}

	@Override
	public List<Person4> dao_list(String mid) {
		
		return sqlMapper.selectList(mid); //mid : personList 
	}

	@Override
	public Person4 dao_view(String mid, String id) {
	    return sqlMapper.selectOne(mid, id);
	}

	@Override
	public void dao_update(String mid, Person4 person) {
	    sqlMapper.update(mid, person);
	}

	@Override
	public void dao_delete(String mid, String id) {
	    sqlMapper.delete(mid, id);
	}



	@Override
	public List<Person4> dao_list(String mid, HashMap<String, String> hm) {
		
		return sqlMapper.selectList(mid,hm);
	}

	
	

	

	@Override
	public int dao_count(String mid, HashMap<String, String> hm) {
		// TODO Auto-generated method stub
		return 0;
	}

	




}
