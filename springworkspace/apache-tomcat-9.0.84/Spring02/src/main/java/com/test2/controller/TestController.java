package com.test2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class TestController  {
	@RequestMapping("test.go")
	public String test() {
		return "result";
		
	}
	@RequestMapping("test2.go")
	public void test2() {
		
	}
}
