package com.myspring.model;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GuestDAOImpl implements GuestDAO {
    @Autowired
    private SqlSession sqlMapper;

    @Override
    public void dao_guestInsert(String mid, Guest guest) {
        sqlMapper.insert(mid, guest);
    }

    @Override
    public List<Guest> dao_guestList(String mid, HashMap<String, String> hmd) {
        return sqlMapper.selectList(mid, hmd);
    }

    @Override
    public Guest dao_guestView(String mid, int num) {
        return sqlMapper.selectOne(mid, num);
    }

    @Override
    public void dao_guestUpdate(String mid, Guest guest) {
        sqlMapper.update(mid, guest);
    }

    @Override
    public void dao_guestDelete(String mid, int num) {
        sqlMapper.delete(mid, num);
    }

    @Override
    public int dao_guestCount(String mid, HashMap<String, String> hm) {
        return sqlMapper.selectOne(mid, hm);
    }

    @Override
    public List<Guest> dao_searchGuest(HashMap<String, String> paramMap) {
        return sqlMapper.selectList("com.myguest.mapper.Guest.searchGuest", paramMap);
    }
}
