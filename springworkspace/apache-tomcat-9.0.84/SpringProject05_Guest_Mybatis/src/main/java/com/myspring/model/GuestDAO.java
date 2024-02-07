package com.myspring.model;

import java.util.HashMap;
import java.util.List;

// GuestDAO �������̽� ����

public interface GuestDAO {
    // �޼������ �ñ״�ó ����
    void dao_guestInsert(String queryId, Guest guest);
    
    Guest dao_guestView(String queryId, int num);
    
    void dao_guestUpdate(String queryId, Guest guest);
    
    
    void dao_guestDelete(String queryId, int num);
    List<Guest> dao_guestList(String queryId, HashMap<String, String> paramMap);
    List<Guest> dao_searchGuest(HashMap<String, String> paramMap);
    int dao_guestCount(String queryId, HashMap<String, String> paramMap);
}
