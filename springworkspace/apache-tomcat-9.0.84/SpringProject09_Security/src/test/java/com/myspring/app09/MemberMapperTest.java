package com.myspring.app09;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myspring.dto.MemberDTO;
import com.myspring.mapper.MemberMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class MemberMapperTest {
	@Autowired
	private MemberMapper memberMapper;
	
	@Test
	public void testRead() {
		MemberDTO member = memberMapper.read("admin90");
		System.out.println(member);
	}
}
