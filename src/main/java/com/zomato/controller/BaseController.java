package com.zomato.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {
	@RequestMapping (value="/goToMenu")
	public String goToMenu() {
		
		System.out.println("move to menu page successfully..!!");
		return "result";
	}

}
