
	package com.myspring.model;

	import java.util.List;

	public interface GuestDAO {
		//추가
		public void dao_guestInsert(String mid, Guest guest);
		//전체보기
		public List<Guest> dao_guestList(String mid);
	}


