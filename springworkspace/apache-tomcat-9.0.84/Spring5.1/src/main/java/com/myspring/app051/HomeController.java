package com.myspring.app051;


import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myspring.model.Guest;
import com.myspring.model.GuestService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
		
	@Autowired
	private GuestService service;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		return "insert";
	}

	@PostMapping("gInsert")
	public String insert(Guest guest, HttpServletRequest request) {
		guest.setIpaddr(request.getRemoteAddr());
		service.guestInsert(guest);
		return "redirect:gList";
	}
	
	@GetMapping("gList")
	public String list(Model model) {
		return "list";
	}
	
}




