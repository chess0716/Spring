
	package com.myspring.model;

	import java.util.List;

	public interface GuestDAO {
		//�߰�
		public void dao_guestInsert(String mid, Guest guest);
		//��ü����
		public List<Guest> dao_guestList(String mid);
	}


