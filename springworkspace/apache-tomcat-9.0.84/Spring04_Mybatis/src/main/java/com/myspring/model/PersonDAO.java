package com.myspring.model;

import java.util.List;

import com.myspring.vo.Person4;

public interface PersonDAO {
	//�߰�
	public void dao_insert(String m_id,Person4 person);
	//��ü����
	public List<Person4> dao_list(String m_id);
	//��������
	public Person4 dao_view(String m_id,String id);
	//����
	public void dao_update(String m_id,Person4 person);
	//����
	public void dao_delete(String m_id,String id);
	//����
	public int dao_countAll(String m_id);
}
