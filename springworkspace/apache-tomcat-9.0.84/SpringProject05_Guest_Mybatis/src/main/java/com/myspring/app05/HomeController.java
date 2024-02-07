package com.myspring.app05;

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

import com.myspring.model.Guest;
import com.myspring.model.GuestService;

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

    @PostMapping("gInsert")
    public String insert(Guest guest, HttpServletRequest request) {
        guest.setIpaddr(request.getRemoteAddr());
        service.guestInsert(guest);
        return "redirect:gList";
    }
    @GetMapping("gInsert")
    public String insertForm() {
        // Your logic here
        return "insert";
    }

    @GetMapping("gList")
    public String list(@RequestParam(value = "field", required = false) String field,
                       @RequestParam(value = "word", required = false, defaultValue = "") String word,
                       Model model) {
        // HashMap<String, String> ��ü�� �����Ͽ� �ʿ��� �����͸� ����ϴ�.
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("field", field);
        paramMap.put("word", word);

        // GuestService�� guestList �޼��忡 paramMap�� �����մϴ�.
        List<Guest> arr;
        int count;

        // ���� �˻���(word)�� ������� �ʴٸ� �˻� ����� ���������� �մϴ�.
        if (!word.isEmpty()) {
            arr = service.searchGuest(paramMap);
            count = service.guestCount(paramMap);
        } else {
            arr = service.guestList(paramMap);
            count = service.guestCount(paramMap);
        }

        model.addAttribute("guests", arr);
        model.addAttribute("count", count);

        return "list";
    }

    @GetMapping("gView")
    public String view(int num, Model model) {
        Guest guest = service.guestView(num);
        model.addAttribute("guest", guest);
        return "view";
    }

    @PostMapping("gUpdate")
    public String update(Guest guest, HttpServletRequest request) {
        guest.setIpaddr(request.getRemoteAddr());
        service.guestUpdate(guest);
        return "redirect:gList";
    }

    @GetMapping("gDelete")
    public String delete(int num) {
        service.guestDelete(num);
        return "redirect:gList";
    }
}




