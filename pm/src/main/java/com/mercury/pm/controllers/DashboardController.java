package com.mercury.pm.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.pm.beans.AreaCodeCoordinateDTO;
import com.mercury.pm.beans.CurrentAgentStateDTO;
//import com.mercury.pm.daos.CurrentAgentStateDao;
import com.mercury.pm.services.AreaCodeCoordinateService;
import com.mercury.pm.services.CurrentAgentStateService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	private AreaCodeCoordinateService accs;
	
	@Autowired
	private CurrentAgentStateService casd;
	
	@GetMapping
	public String getDashboardData() {
		String res = "1";
		return res;
	}
	
	@PostMapping(consumes = "text/plain")
	public boolean postDashboardData(@RequestBody String body) {
		System.out.println(body);
		return true;
	}
	
	@GetMapping("/accs")
	public List<AreaCodeCoordinateDTO> getACCS() {
		return accs.getAreaCodeCoordinates();
	}
	
	@GetMapping("/currentagentstate/{gid}")
	public List<CurrentAgentStateDTO> getCAS(@PathVariable int gid ) {
		List<CurrentAgentStateDTO> res = casd.getCASD(gid);
		return casd.getCASD(gid);
	}
}
