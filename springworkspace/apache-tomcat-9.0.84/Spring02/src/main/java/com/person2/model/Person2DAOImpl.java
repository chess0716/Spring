package com.person2.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;





@Repository
public class Person2DAOImpl implements Person2DAO {
	@Autowired
	private JdbcTemplate template;
	
	@Override
	public void person_insert(Person2 person) {
		String sql="insert into person values(?,?,?,?,?)";
		Object[] param = new Object[] {
				person.getId(),
				person.getPassword(),
				person.getName(),
				person.getGender(),
				person.getJob()
				
		};
		template.update(sql,param);
	}

	@Override
	public List<Person2> person_list() {
	    String sql = "SELECT * FROM person";
	    List<Person2> personList = template.query(sql, new RowMapper<Person2>() {
	  
	    	@Override
	        public Person2 mapRow(ResultSet rs, int arg1) throws SQLException {
	            Person2 p = new Person2();
	            p.setGender(rs.getString("gender"));
	            p.setId(rs.getString("id"));
	            p.setJob(rs.getString("job"));
	            p.setName(rs.getString("name"));
	            p.setPassword(rs.getString("password"));
	            return p;
	        }
	    });
	    return personList;
	}

	@Override
	public Person2 person_view(String id) {
		String sql = "select * from person where id ='" +id+ "'";
	    Person2 person = template.queryForObject(sql, new RowMapper<Person2>() {
	                @Override
	                public Person2 mapRow(ResultSet rs, int arg1) throws SQLException {
	                	Person2 p = new Person2();
	    	            p.setGender(rs.getString("gender"));
	    	            p.setId(rs.getString("id"));
	    	            p.setJob(rs.getString("job"));
	    	            p.setName(rs.getString("name"));
	    	            p.setPassword(rs.getString("password"));
	    	            return p;
	                }
	            });
		return person;
	        
	        }
	

	@Override
	public void person_update(Person2 person) {
		  String sql = "update person set name = ?, password=?, gender=?, job=? where id=?";
	        Object[] param = new Object[] { person.getName(), person.getPassword(), person.getGender(), person.getJob(),
	                person.getId() };
	        template.update(sql, param);
		
	}

	@Override
	public void person_delete(String id) {
		  String sql = "delete from person where id ='"+id+"'";
	      template.update(sql);
		
	}

	@Override
	public int person_count() {
	    String sql = "SELECT COUNT(*) FROM person";
	    int count = template.queryForObject(sql, Integer.class);
	    return count;
	}

	
	

}
