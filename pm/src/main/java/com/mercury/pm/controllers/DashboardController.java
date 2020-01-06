package com.mercury.pm.controllers;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.pm.beans.AreaCodeCoordinateDTO;
import com.mercury.pm.beans.CurrentAgentStateDTO;
import com.mercury.pm.beans.GroupDTO;
import com.mercury.pm.beans.HeatmapDataDTO;
//import com.mercury.pm.daos.CurrentAgentStateDao;
import com.mercury.pm.services.AreaCodeCoordinateService;
import com.mercury.pm.services.ModuleService;
import com.mercury.pm.services.GroupService;

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
	public List<CurrentAgentStateDTO> getCurrentAgentStateByGroupId(@PathVariable int gid ) {
		List<CurrentAgentStateDTO> res = ms.getCurrentAgentStateByGroupId(gid);
		return ms.getCurrentAgentStateByGroupId(gid);
	}
	
	@GetMapping("/currentagentstate/testing/{gid}")
	public List<CurrentAgentStateDTO> getCurrentAgentStateByGroupIdTesting(@PathVariable int gid ) {
		List<CurrentAgentStateDTO> res = ms.getCurrentAgentStateByGroupIdTesting(gid);
		
		System.out.println(res.get(0).getServiceStart());
		Date temp = res.get(0).getServiceStart();
		System.out.println(temp.getHours());
		System.out.println(LocalTime.now().isAfter(LocalTime.of(temp.getHours(), temp.getMinutes(), temp.getSeconds())));
		
		return ms.getCurrentAgentStateByGroupIdTesting(gid);
	}
	
	@GetMapping("/group")
	public GroupDTO getFirstGroup() {
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
	public List<HeatmapDataDTO> getHeatmapDataByGroupIdTesting(@PathVariable int gid) {
		List<HeatmapDataDTO> res = ms.getHeatmapDataByGroupId(gid);
		return res;
	}
	
	@GetMapping("/heatmap/{gid}")
	public Map<LocalTime, List<HeatmapDataDTO>> getHeatmapDataByGroupId(@PathVariable int gid) {
		List<HeatmapDataDTO> rawData = ms.getHeatmapDataByGroupId(gid);
		
		Map<LocalTime, List<HeatmapDataDTO>> res = new LinkedHashMap<>();
		
		for (int i = 0; i < 12; i++) {
			res.put(LocalTime.now().minusMinutes(5 * i), new ArrayList<HeatmapDataDTO>());
		}
		
		return res;
	}
	
	// TODO get dashboard by userid.
}
