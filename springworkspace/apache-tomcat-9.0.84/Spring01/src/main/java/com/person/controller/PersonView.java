package com.person.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.person.model.Person;
import com.person.model.PersonDAOImpl;

public class PersonView extends AbstractController {
	private PersonDAOImpl dao;
	

	public void setDao(PersonDAOImpl dao) {
		this.dao = dao;
	}



	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		String id = req.getParameter("id");
		Person person =  dao.personView(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("person", person);
		mv.setViewName("WEB-INF/jsp/personView.jsp");
		return mv;
	}

}