package com.myspring.app07.model;

import java.util.HashMap;
import java.util.List;

import org.springframework.ui.Model;

public interface GuestService {
    // �߰�
    public void guestInsert(GuestVO guest);
    
    // ��ü����
    public List<GuestVO> guestList(HashMap<String, String> hm);
    // �󼼺���
    public GuestVO findByNum(int num);
    
    // ����
    public void guestUpdate(GuestVO guest);
    
    // ����
    public void guestDelete(int num);
    
    // ����
    public int guestCount(HashMap<String, String> hm);

    public List<GuestVO> searchGuest(HashMap<String, String> hm);
}
