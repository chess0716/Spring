package com.myspring.app06;

import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myspring.app06.model.GuestService;
import com.myspring.app06.model.GuestVO;

@Controller
public class HomeController {
    @Autowired
    private GuestService service;
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info("Welcome home! The client locale is {}.", locale);
        return "insert";
    }

    @PostMapping("insert")
    public String insert(GuestVO guest, HttpServletRequest request) {
        guest.setIpaddr(request.getRemoteAddr());
        service.guestInsert(guest);
        return "redirect:list";
    }

    @GetMapping("insert")
    public String insertForm() {
        return "insert";
    }

    @GetMapping("list")
    public String list(Model model) {
        HashMap<String, String> parameters = new HashMap<>();
        model.addAttribute("guests", service.guestList(parameters));
        return "list";
    }

    @GetMapping("view")
    public String view(@RequestParam int num, Model model) {
        model.addAttribute("guest", service.findByNum(num));
        return "view";
    }

    @PostMapping("update")
    public String update(@RequestParam int num, GuestVO guest, HttpServletRequest request) {
        guest.setNum(num);
        guest.setIpaddr(request.getRemoteAddr());
        service.guestUpdate(guest);
        return "redirect:list";
    }

    @GetMapping("delete")
    public String delete(int num) {
        service.guestDelete(num);
        return "redirect:list";
    }
    @PostMapping("search")
    @ResponseBody
    public List<GuestVO> searchGuest(@RequestParam String field, @RequestParam String word) {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("field", field);
        hm.put("word", word);
        return service.searchGuest(hm);
    }
}
