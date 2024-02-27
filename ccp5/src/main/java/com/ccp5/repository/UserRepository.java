package com.ccp5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccp5.dto.User;



public interface UserRepository 
    extends  JpaRepository<User, Long>{
	//select * from tbl_user4 where username='11'
	  User findByUsername(String username);
	

}
