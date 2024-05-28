package com.demo.api.repository;

import com.demo.api.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembersRepository extends JpaRepository<Members, Long> {

}
