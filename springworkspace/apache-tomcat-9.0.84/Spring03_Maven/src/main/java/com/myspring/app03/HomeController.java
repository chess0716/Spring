package com.myspring.app03;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.myspring.model.Person3;

import com.myspring.model.MPersonDAOImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
    
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    @Autowired
    private MPersonDAOImpl dao;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info("Welcome home! The client locale is {}.", locale);
        
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        
        String formattedDate = dateFormat.format(date);
        
        model.addAttribute("serverTime", formattedDate );
        
        return "home";
    }
    
    @GetMapping("insert")
    public String insert() {
        return "personInsert";
    }
    
    @PostMapping("insert")
    public String insert(Person3 person) {
        dao.per_insert(person);
        return "redirect:list";
    }
    
 // 전체보기
 	@GetMapping("list")
 	public String list(Model model,
 			@RequestParam(value = "field" ,required=false, defaultValue = "name" )String field, 
 			@RequestParam(value = "word" ,required=false, defaultValue = "" ) String word ) {
 		
 		List<Person3> arr = dao.per_list(field, word);
 		int count = dao.per_count(field, word);
 		model.addAttribute("plist", arr);
 		model.addAttribute("count", count);
 		return "personList";
 	}
    
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(Person3 person) {
        dao.per_update(person);
        return "redirect:list";
    }
    
    @GetMapping({"view","update"})
    public void view_update(String id, Model model) {
        Person3 person = dao.per_view(id);
        model.addAttribute("person", person);
    }

    @RequestMapping(value = "delete")
    public String delete(String id) {
        dao.per_delete(id);
        return "redirect:list";
    }

}
