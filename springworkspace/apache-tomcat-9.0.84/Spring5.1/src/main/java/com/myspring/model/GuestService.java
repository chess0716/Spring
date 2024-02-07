package com.myspring.model;

import java.util.List;

public interface GuestService {
	//추가
	public void guestInsert(Guest guest);
	//전체보기
	public List<Guest> guestList();
}
