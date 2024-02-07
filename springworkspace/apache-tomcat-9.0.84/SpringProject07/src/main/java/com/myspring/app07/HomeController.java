package com.myspring.app07;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myspring.app07.model.GuestListVO;
import com.myspring.app07.model.GuestService;
import com.myspring.app07.model.GuestVO;

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
    @ResponseBody
    public String insert(@RequestBody GuestVO guest, HttpServletRequest request) {
        guest.setIpaddr(request.getRemoteAddr()); // IP 주소 설정
        service.guestInsert(guest);
        return "success";
    }
    
    @GetMapping("list")
    @ResponseBody
    public GuestListVO list(@RequestParam(required = false) String field, @RequestParam(required = false) String word) {
        HashMap<String, String> searchParams = new HashMap<>();
        if (field != null && word != null) {
            searchParams.put("field", field);
            searchParams.put("word", word);
        }
        List<GuestVO> arr = service.guestList(searchParams);
        int count = service.guestCount(searchParams);
        // 생성자를 이용하여 객체 생성
        GuestListVO listvo = new GuestListVO(arr, count);
        return listvo;
    }

    @GetMapping("view")
    @ResponseBody
    public GuestVO view(int num) {
        GuestVO guest = service.findByNum(num);
        return guest;
    }
    
    @GetMapping("delete")
    @ResponseBody
    public int delete(int num) {
        service.guestDelete(num);
        return num;
    }
    
    
}






