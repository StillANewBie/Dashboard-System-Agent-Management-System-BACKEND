package com.mercury.pm.controllers;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.pm.beans.CurrentAgentState;
import com.mercury.pm.beans.Group;
import com.mercury.pm.beans.HeatmapData;
import com.mercury.pm.beans.User;
import com.mercury.pm.services.GroupRoleService;
import com.mercury.pm.services.ModuleService;
import com.mercury.pm.services.UserService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	private ModuleService ms;
	
	@Autowired
	private GroupRoleService gs;
	
	@Autowired
	private UserService us;
	
	@GetMapping("/currentagentstate/{gid}")
	public List<CurrentAgentState> getCurrentAgentStateByGroupId(@PathVariable int gid ) {
		List<CurrentAgentState> res = ms.getCurrentAgentStateByGroupId(gid);
		return ms.getCurrentAgentStateByGroupId(gid);
	}
	
	
	// temp
	private static String tempDashboard = "";
	@PostMapping(path = "/state")
	public String postDashboard(@RequestParam int userId, @RequestParam String dashboardState) {
		System.out.println(userId);
		System.out.println(dashboardState);
		return dashboardState;
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
