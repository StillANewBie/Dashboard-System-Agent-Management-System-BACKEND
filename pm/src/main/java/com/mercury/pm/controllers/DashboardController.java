package com.mercury.pm.controllers;

import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.el.lang.ELArithmetic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.pm.beans.CurrentAgentState;
import com.mercury.pm.beans.Group;
import com.mercury.pm.beans.HeatmapData;
import com.mercury.pm.services.GroupService;
import com.mercury.pm.services.ModuleService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	private ModuleService ms;
	
	@Autowired
	private GroupService gs;
	
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
	
	@GetMapping("/currentagentstate/{gid}")
	public List<CurrentAgentState> getCurrentAgentStateByGroupId(@PathVariable int gid ) {
		List<CurrentAgentState> res = ms.getCurrentAgentStateByGroupId(gid);
		return ms.getCurrentAgentStateByGroupId(gid);
	}
	
	@GetMapping("/currentagentstate/testing/{gid}")
	public List<CurrentAgentState> getCurrentAgentStateByGroupIdTesting(@PathVariable int gid ) {
		List<CurrentAgentState> res = ms.getCurrentAgentStateByGroupIdTesting(gid);
		
		System.out.println(res.get(0).getServiceStart());
		Date temp = res.get(0).getServiceStart();
		System.out.println(temp.getHours());
		System.out.println(LocalTime.now().isAfter(LocalTime.of(temp.getHours(), temp.getMinutes(), temp.getSeconds())));
		
		return ms.getCurrentAgentStateByGroupIdTesting(gid);
	}
	
	@GetMapping("/group")
	public Group getFirstGroup() {
		return gs.getGroupDTOByID(1);
	}
	
	// temp
	private static String tempDashboard = "";
	@PostMapping(path = "/state", consumes = "text/plain")
	public String postDashboard(@RequestBody String body) {
		tempDashboard = body;
		return body;
	}
	
	// temp
	@GetMapping("/state")
	public String getDashboard() {
		return tempDashboard;
	}
	
	@GetMapping("/heatmap/testing/{gid}")
	public List<HeatmapData> getHeatmapDataByGroupIdTesting(@PathVariable int gid) {
		List<HeatmapData> res = ms.getHeatmapDataByGroupId(gid);
		return res;
	}
	
	@GetMapping("/heatmap/{gid}")
	public Map<String, List<HeatmapData>> getHeatmapDataByGroupId(@PathVariable int gid) {
		List<HeatmapData> rawData = ms.getHeatmapDataByGroupId(gid);
		
		Map<String, List<HeatmapData>> res = new LinkedHashMap<>();
		rawData.parallelStream().forEach(el -> {
			res.putIfAbsent(el.getAreaCode(), new ArrayList<HeatmapData>());
			res.get(el.getAreaCode()).add(el);
		});
		
		return res;
	}
	
	// TODO get dashboard by userid.
}
