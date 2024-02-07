package com.myspring.model;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GuestDAOImpl implements GuestDAO {
	@Autowired
	private SqlSession  sqlMapper;
    
	@Override    //mid = insertGuest
	public void dao_guestInsert(String mid, Guest guest) { 
		sqlMapper.insert(mid, guest);
		
	}

	@Override
	public List<Guest> dao_guestList(String mid) {
		// TODO Auto-generated method stub
		return null;
	}

}
