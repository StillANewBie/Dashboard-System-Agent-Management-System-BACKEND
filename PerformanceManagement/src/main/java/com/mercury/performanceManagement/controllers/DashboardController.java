package com.mercury.performanceManagement.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	
	@GetMapping
	public String getDashboardState() {
		String res = "1";
		return res;
	}
}
