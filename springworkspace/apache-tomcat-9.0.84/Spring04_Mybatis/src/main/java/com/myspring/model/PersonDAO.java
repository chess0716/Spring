package com.myspring.model;

import java.util.List;

import com.myspring.vo.Person4;

public interface PersonDAO {
	//추가
	public void dao_insert(String m_id,Person4 person);
	//전체보기
	public List<Person4> dao_list(String m_id);
	//상제보기
	public Person4 dao_view(String m_id,String id);
	//수정
	public void dao_update(String m_id,Person4 person);
	//삭제
	public void dao_delete(String m_id,String id);
	//개수
	public int dao_countAll(String m_id);
}
