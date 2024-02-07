package com.person.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class PersonDAOImpl implements PersonDAO {

    private JdbcTemplate template;

    // setter
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    // 추가
    @Override
    public void personInsert(Person person) {
        String sql = "insert into person values(?,?,?,?,?)";
        Object[] param = new Object[] { person.getId(), person.getName(), person.getPassword(), person.getGender(),
                person.getJob() };
        template.update(sql, param);
    }

    @Override
    public List<Person> personList() {
        String sql = "select * from person";
        List<Person> personlist = template.query(sql, new RowMapper<Person>() {
            @Override
            public Person mapRow(ResultSet rs, int arg1) throws SQLException {
                Person p = new Person();
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

    // 상세보기
    @Override
    public Person personView(String id) {
        String sql = "select * from person where id = ?";
        try {
            return template.queryForObject(sql, new RowMapper<Person>() {
                @Override
                public Person mapRow(ResultSet rs, int arg1) throws SQLException {
                    Person p = new Person();
                    p.setGender(rs.getString("gender"));
                    p.setId(rs.getString("id"));
                    p.setJob(rs.getString("job"));
                    p.setName(rs.getString("name"));
                    p.setPassword(rs.getString("password"));
                    return p;
                }
            }, id);
        } catch (EmptyResultDataAccessException e) {
            // 쿼리 결과가 없을 때의 처리
            // 예를 들어, 로그 출력
            System.out.println("ID에 해당하는 레코드가 없습니다. ID: " + id);
            return null; // 또는 다른 방식으로 처리
        }
    }

    @Override
    public void personUpdate(Person person) {
        String sql = "update person set gender = ?, job=?, name=?, password=? where id=?";
        Object[] param = new Object[] { person.getGender(), person.getJob(), person.getName(), person.getPassword(),
                person.getId() };
        template.update(sql, param);
    }

    // 삭제
    @Override
    public void personDelete(String id) {
        String sql = "delete from person where id = ?";
        template.update(sql, id);
    }
}

