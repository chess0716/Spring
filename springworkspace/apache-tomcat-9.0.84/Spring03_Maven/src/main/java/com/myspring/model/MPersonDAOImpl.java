package com.myspring.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class MPersonDAOImpl implements MPersonDAO {
  @Autowired
	private JdbcTemplate template;
	@Override
	public void per_insert(Person3 person) {
		String sql="insert into person values(?,?,?,?,?)";
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
	public List<Person3> per_list() {
		String sql = "select * from person";
		List<Person3> personlist  = template.query(sql, new RowMapper<Person3>() {
			@Override
			public Person3 mapRow(ResultSet rs, int arg1) throws SQLException {
				Person3 p = new Person3();
				p.setGender(rs.getString("gender"));
				p.setId(rs.getString("id"));
				p.setJob(rs.getString("job"));
				p.setName(rs.getString("name"));
				p.setPassword(rs.getString("password"));
				return p;
			}
		});
		return personlist;
	}

	@Override
	public Person3 per_view(String id) {
		String sql = "select * from person where id='"+id+"'";
		Person3 person = template.queryForObject(sql, new RowMapper<Person3>(){
			@Override
			public Person3 mapRow(ResultSet rs, int arg1) throws SQLException {
				Person3 p = new Person3();
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
	public void per_update(Person3 person) {
		String sql = "update person set name=?, password=?, "
				+ "gender=?, job=? where id=?";
		Object[] param = new Object[] {
				person.getName(),
				person.getPassword(),
				person.getGender(),
				person.getJob(),
				person.getId()
		};
		template.update(sql, param);
		
	}

	@Override
	public void per_delete(String id) {
		String sql = "delete from person where id ='"+id+"'";
		template.update(sql);
		
	}

	@Override
	public int per_count() {
		String sql = "select count(*) from person";
		int count = template.queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public List<Person3> per_list(String field, String word) {
		String sql = "select * from person where "+field +" like '%"+word+"%'";
		List<Person3> personlist  = template.query(sql, new RowMapper<Person3>() {
			@Override
			public Person3 mapRow(ResultSet rs, int arg1) throws SQLException {
				Person3 p = new Person3();
				p.setGender(rs.getString("gender"));
				p.setId(rs.getString("id"));
				p.setJob(rs.getString("job"));
				p.setName(rs.getString("name"));
				p.setPassword(rs.getString("password"));
				return p;
			}
		});
		return personlist;
	}
	@Override
	public int per_count(String field, String word) {
		String sql = "select count(*) from person where "+field +" like '%"+word+"%'";
		int count = template.queryForObject(sql, Integer.class);
		return count;
	}

}
