package com.example.DashboardDemo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

	private static String dashboard = "dashboard";
	
	@GetMapping
	public String getDashboards() {
		
		return dashboard;
	}
	
	@PostMapping(consumes = "text/plain")
	public String postDashboards(@RequestBody String body) {
		dashboard = body;
		return body;
	}
}
