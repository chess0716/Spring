package com.myspring.app07.model;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myspring.app07.mapper.GuestMapper;



@Repository
public class GuestRepositoryImpl implements GuestRepository{
	@Autowired
	private GuestMapper mapper;
	
	@Override
	public void dao_guestInsert(GuestVO guest) {
		mapper.insert(guest);	
		
	}

	@Override
	public List<GuestVO> dao_guestList(HashMap<String, String> hm) {
		
		 return mapper.list();
	}

	@Override
	public GuestVO dao_findByNum(int num) {
		
		return mapper.view(num);
	}

	@Override
	public void dao_guestUpdate(GuestVO guest) {
		mapper.update(guest);
		
	}

	@Override
	public void dao_guestDelete(int num) {
		mapper.delete(num);
		
	}

	@Override
	public int dao_guestCount(HashMap<String, String> hm) {
		
		return mapper.count();
	}

	@Override
	public List<GuestVO> dao_searchGuest(HashMap<String, String> hm) {
	    return mapper.searchGuest(hm);
	}
 @Override
    public List<GuestVO> searchGuest(HashMap<String, String> hm) {
        return mapper.searchGuest(hm);
    }

}
