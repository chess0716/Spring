package com.myspring.model;

import java.util.HashMap;
import java.util.List;

// GuestDAO 인터페이스 정의

public interface GuestDAO {
    // 메서드들의 시그니처 정의
    void dao_guestInsert(String queryId, Guest guest);
    
    Guest dao_guestView(String queryId, int num);
    
    void dao_guestUpdate(String queryId, Guest guest);
    
    
    void dao_guestDelete(String queryId, int num);
    List<Guest> dao_guestList(String queryId, HashMap<String, String> paramMap);
    List<Guest> dao_searchGuest(HashMap<String, String> paramMap);
    int dao_guestCount(String queryId, HashMap<String, String> paramMap);
}
