package com.myspring.model;


import java.util.HashMap;
import java.util.List;



public interface GuestService {
    // GuestService 메서드들의 시그니처 정의
    void guestInsert(Guest guest);
    Guest guestView(int num);
    void guestUpdate(Guest guest);
    void guestDelete(int num);
    List<Guest> guestList(HashMap<String, String> paramMap);
  
	int guestCount(HashMap<String, String> paramMap);
	List<Guest> searchGuest(HashMap<String, String> paramMap);
	
}
