package com.myspring.model;


import java.util.HashMap;
import java.util.List;



public interface GuestService {
    // GuestService �޼������ �ñ״�ó ����
    void guestInsert(Guest guest);
    Guest guestView(int num);
    void guestUpdate(Guest guest);
    void guestDelete(int num);
    List<Guest> guestList(HashMap<String, String> paramMap);
  
	int guestCount(HashMap<String, String> paramMap);
	List<Guest> searchGuest(HashMap<String, String> paramMap);
	
}
