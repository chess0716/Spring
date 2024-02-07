package com.person.model;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class PersonDAOImpl implements PersonDAO{

	private JdbcTemplate  template;
	//setter
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	//추가
	@Override
	public void personInsert(Person person) {
		String sql ="insert into person values(?,?,?,?,?)";
		Object[] param = new Object[] {
				person.getId(),
				person.getName(),
				person.getPassword(),
				person.getGender(),
				person.getJob()
		};
		template.update(sql, param);
		
		
	}

	

	@Override
	public List<Person> personList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person personView(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void personUpdate(Person person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void personDelete(String id) {
		// TODO Auto-generated method stub
		
	}

}
