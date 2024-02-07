package com.myspring.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestServiceImpl implements GuestService {
	@Autowired
	private GuestDAO dao;

	//추가
	@Override
	public void guestInsert(Guest guest) {
		dao.dao_guestInsert("insertGuest", guest);
		
	}

	@Override
	public List<Guest> guestList() {
		// TODO Auto-generated method stub
		return null;
	}

}
