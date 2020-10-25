package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {
	
	@GetMapping("/mvc_signup")
	public String signup() {
		return "Signup";
	}

}
