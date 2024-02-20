package com.example_jpa.demo4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example_jpa.demo4.model.User;

public interface UserRepository 
    extends  JpaRepository<User, Long>{
	//select * from tbl_user4 where username='11'
	  User findByUsername(String username);
	

}
