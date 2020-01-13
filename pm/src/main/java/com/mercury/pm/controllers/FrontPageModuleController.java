package com.mercury.pm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.pm.dtos.CallAvgTimeDTO;
import com.mercury.pm.dtos.CallTotalTimeDTO;
import com.mercury.pm.dtos.OutcomeDTO;
import com.mercury.pm.services.FrontPageModuleService;

@RestController
@RequestMapping("/front-page")
public class FrontPageModuleController {
	
	@Autowired
	private FrontPageModuleService frontPageModuleService;
	
	@GetMapping("/outcomes/{days}")
	public List<OutcomeDTO> getOutcomesByDays(@PathVariable int days) {
		
		return frontPageModuleService.getOutcomesByDays(days);
	}
	
	@GetMapping("/calltotaltime")
	public CallTotalTimeDTO getCallTotalTimeByGroupIdAndDays(@RequestParam int gid,@RequestParam int days) {
		return frontPageModuleService.getCallTotalTimeByGroupIdAndDays(gid, days);
	}
	
	@GetMapping("/callavgtime")
	public CallAvgTimeDTO getCallAvgTimeByGroupIdAndDays(@RequestParam int gid,@RequestParam int days) {
		return frontPageModuleService.getCallAvgTimeByGroupIdAndDays(gid, days);
	}
}
