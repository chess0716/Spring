package com.myspring.app07.model;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class GuestServiceImpl implements GuestService {
	@Autowired
	private GuestRepository repository;
	@Override
	public void guestInsert(GuestVO guest) {
		repository.dao_guestInsert(guest);
		
	}

	@Override
	public List<GuestVO> guestList(HashMap<String, String> hm) {
		
		return repository.dao_guestList(hm);
	}

	@Override
	public GuestVO findByNum(int num) {
		
		return repository.dao_findByNum(num);
	}

	@Override
	public void guestUpdate(GuestVO guest) {
		repository.dao_guestUpdate(guest);
		
	}

	@Override
	public void guestDelete(int num) {
		
		repository.dao_guestDelete(num);
	}

	@Override
	public int guestCount(HashMap<String, String> hm) {
		
		return 0;
	}
	   @Override
	    public List<GuestVO> searchGuest(HashMap<String, String> hm) {
	        return repository.searchGuest(hm);
	    }
}
