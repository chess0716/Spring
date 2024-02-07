package com.person2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.person2.model.Person2;
import com.person2.model.Person2DAOImpl;

@Controller
public class Person2Controller {
	@Autowired
	private Person2DAOImpl dao;
	
	@RequestMapping("person_insert.go")
	public String insert() {
		return "personInsert";
	}
	@RequestMapping(value="person_insert.go", method= RequestMethod.POST)
	public String insert(Person2 person) {
		dao.person_insert(person);
		return "redirect:person_list.go";
		
	}

	@RequestMapping("person_list.go")
	public String list(Model model) {
		List<Person2> arr = dao.person_list();
		int count = dao.person_count();
		    
		model.addAttribute("plist",arr);
		model.addAttribute("count",count);
		return"personList";
	}
	//상세보기
	@RequestMapping ("person_view.go")
	public String view(String id,Model model) {
		Person2 person = dao.person_view(id);
		model.addAttribute("person",person);
		return "personView";
	}
	//수정폼
	@RequestMapping("person_update.go")
	public String update(String id, Model model) {
	    Person2 person = dao.person_view(id);
	    model.addAttribute("person", person);
	    return "personUpdate";
	}

	@RequestMapping(value = "person_update.go", method = RequestMethod.POST)
	public String update(Person2 person) {
	    dao.person_update(person);
	    return "redirect:person_list.go";
	}

	@RequestMapping(value = "person_delete.go")
	public String delete(String id) {
	    dao.person_delete(id);
	    return "redirect:person_list.go";
	}
	
}
