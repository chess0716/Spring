package com.myspring.model;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person4 dao_view(String mid, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dao_update(String mid, Person4 person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dao_delete(String mid, String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int dao_countAll(String mid) {
		// TODO Auto-generated method stub
		return 0;
	}

}
