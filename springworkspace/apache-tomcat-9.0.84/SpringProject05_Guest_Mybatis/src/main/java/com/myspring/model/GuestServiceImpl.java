package com.myspring.model;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestDAO dao;

    @Override
    public void guestInsert(Guest guest) {
        dao.dao_guestInsert("insertGuest", guest);
    }

    @Override
    public Guest guestView(int num) {
        return dao.dao_guestView("viewGuest", num);
    }

    @Override
    public void guestUpdate(Guest guest) {
        dao.dao_guestUpdate("updateGuest", guest);
    }

    @Override
    public void guestDelete(int num) {
        dao.dao_guestDelete("deleteGuest", num);
    }

    @Override
    public List<Guest> guestList(HashMap<String, String> paramMap) {
        return dao.dao_guestList("listGuest", paramMap);
    }

    @Override
    public int guestCount(HashMap<String, String> paramMap) {
        return dao.dao_guestCount("countGuest", paramMap);
    }

    @Override
    public List<Guest> searchGuest(HashMap<String, String> paramMap) {
        return dao.dao_guestList("listGuest", paramMap);
    }
}

