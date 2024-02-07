package com.myspring.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myspring.vo.Person4;
@Repository
public class PersonDAOImpl implements PersonDAO {
	@Autowired
	private SqlSession sqlMapper;
	@Override
	public void dao_insert(String m_id, Person4 person) {
		sqlMapper.insert(m_id,person);
	}

	@Override
	public List<Person4> dao_list(String m_id) {
		
		return null;
	}

	@Override
	public Person4 dao_view(String m_id, String id) {
		
		return null;
	}

	@Override
	public void dao_update(String m_id, Person4 person) {
		
		
	}

	@Override
	public void dao_delete(String m_id, String id) {
		
		
	}

	@Override
	public int dao_countAll(String m_id) {
		
		return 0;
	}

}
