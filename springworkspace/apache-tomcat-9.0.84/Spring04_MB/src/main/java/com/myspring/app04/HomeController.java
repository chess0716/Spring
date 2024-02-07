package com.myspring.app04;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
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
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
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
			return "redirect:person_list";
		}
		//전체보기 검색포함
		@GetMapping("person_list")
		public String list(String field,String word,Model model) {
			List <Person4> arr = service.list(field,word);
			int count = service.count(field, word);
			model.addAttribute("personList",arr);
			model.addAttribute("count",count);
			return "list";
		}
		
		
//		@GetMapping("person_list")
//		public String list(Model model) {
//		List <Person4> arr=	service.list();
//		model.addAttribute("personList",arr);
//		int count = service.countAll();
//			return "list";
//		}
		// 상세보기
	    @GetMapping("person_view")
	    public String view(String id, Model model) {
	        Person4 person = service.view(id);
	        model.addAttribute("person", person);
	       
	        return "view";
	    }

	    // 수정폼
	    @GetMapping("person_update")
	    public String update(String id, Model model) {
	        Person4 person = service.view(id);
	        model.addAttribute("person", person);
	        
	        return "update";
	    }

	    // 수정
	    @PostMapping("person_update")
	    public String update(Person4 person) {
	        service.update(person);
	        return "redirect:person_list";
	    }

	    // 삭제
	    @GetMapping("person_delete")
	    public String delete(String id) {
	        service.delete(id);
	        return "delete";
	    }
	    
}