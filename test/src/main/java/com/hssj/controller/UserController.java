package com.hssj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@RequestMapping("/")
	public String seve() {
		System.out.println("mvc running---------------");
		return "index";
	}
	
	@RequestMapping("/sad")
	public String sev() {
		System.out.println("mvc running---------------");
		return "sad";
	}
}
