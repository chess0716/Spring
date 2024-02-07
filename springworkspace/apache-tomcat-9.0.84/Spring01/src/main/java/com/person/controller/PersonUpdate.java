package com.person.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.person.model.Person;
import com.person.model.PersonDAOImpl;

public class PersonUpdate extends AbstractController {
	private PersonDAOImpl dao;
	

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		Person p = new Person();
		p.setGender(req.getParameter("gender"));
		p.setId(req.getParameter("id"));
		p.setJob(req.getParameter("job"));
		p.setName(req.getParameter("name"));
		p.setPassword(req.getParameter("password"));
		dao.personInsert(p);
		
		return new ModelAndView("redirect:personList.sp");
	}

}
