package com.mercury.pmdsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.pmdsservice.beans.DashboardState;
import com.mercury.pmdsservice.services.DashboardStateService;

@RestController
@RequestMapping("/dashboard-state")
public class DashboardStateController {
	
	@Autowired
	private DashboardStateService dss;
	
	@GetMapping("/{uid}")
	public DashboardState getDashboardStateByUserId(@PathVariable int uid) {
		
		return dss.getDashboardStateByUserId(uid);
	}
	
	@PostMapping
	public DashboardState postDashboard(@RequestParam int userId, @RequestParam String dashboardState) {
		
		DashboardState ds = dss.getDashboardStateByUserId(userId);
		
		if (ds == null) {
			ds = new DashboardState();
			ds.setDashboardName("");
			ds.setDashboardState(dashboardState);
			ds.setUserId(userId);
			dss.saveDashboardState(ds);
		} else {
			ds.setDashboardState(dashboardState);
			dss.saveDashboardState(ds);
		}
		return ds;
	}
}
