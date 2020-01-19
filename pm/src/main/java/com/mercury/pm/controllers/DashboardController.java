package com.mercury.pm.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.mercury.pm.beans.CurrentAgentState;
import com.mercury.pm.beans.DashboardState;
import com.mercury.pm.beans.HeatmapData;
import com.mercury.pm.beans.User;
import com.mercury.pm.security.jwt.JwtTokenUtil;
import com.mercury.pm.services.DashboardStateService;
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
	
	@Autowired
	private DashboardStateService dss;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@GetMapping("/currentagentstate/{gid}")
	public List<CurrentAgentState> getCurrentAgentStateByGroupId(@PathVariable int gid ) {
		List<CurrentAgentState> res = ms.getCurrentAgentStateByGroupId(gid);
		return ms.getCurrentAgentStateByGroupId(gid);
	}
	
	@PostMapping(path = "/state")
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
	
	@GetMapping("/state")
	public DashboardState getDashboard(HttpServletRequest request) {
		
		User temp = jwtTokenUtil.getUserByJwt(request);
		
		return dss.getDashboardStateByUserId(temp.getUserId());
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
	
	@GetMapping("/microtest")
	public DashboardState getDashboardStateFromOtherService() {
		
		return webClientBuilder.build()
				.get()
				.uri("http://localhost:8081/dashboard-state/2")
				.retrieve()
				.bodyToMono(DashboardState.class)
				.block();
		
//		RestTemplate restTemplate = new RestTemplate();
//		return restTemplate.getForObject("http://localhost:8081/dashboard-state/2", DashboardState.class);
	}
}
