package com.myspring.app04;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myspring.model.PersonService;
import com.myspring.vo.Person4;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private PersonService service;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
			return "home";
	}
	//추가폼
	@GetMapping("person_insert")
	public String insert() {
		return "insert";
	}
	//추가
	@PostMapping("person_insert")
	public String insert(Person4 person) {
		service.insert(person);
		return null;
	}
	
}











