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

import com.mercury.pm.beans.CurrentAgentStateDTO;
import com.mercury.pm.beans.GroupDTO;
import com.mercury.pm.beans.HeatmapDataDTO;
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
		Date now = new Date();
		for (int i = 0; i < 24; i++) {
			res.put(LocalTime.now().minusMinutes(5 * i), new ArrayList<HeatmapDataDTO>());
//			res.put(now.toInstant().minusSeconds(300 * i), new ArrayList<HeatmapDataDTO>());

		}
		
		rawData.parallelStream().forEach(el -> {
			res.forEach((k, v) -> {
				if (k.isBefore(LocalTime.parse(el.getServiceExit().toString()))) {
					if (el.getQueueTime() == 0) {
						if (k.isAfter(LocalTime.parse(el.getServiceStart().toString()))) {
							res.get(k).add(el);
						}
					} else {
						if (k.isAfter(LocalTime.parse(el.getQueueStart().toString()))) {
							res.get(k).add(el);
						}
					}
				}
			});
		});
//		
//		rawData.forEach(el -> System.out.println(LocalTime.parse(el.getServiceExit().toString())));
		
//		res.forEach((k, v) -> {
//			System.out.println(LocalTime.parse(rawData.get(6).getServiceExit().toString()));
//			System.out.println(k);
//			System.out.println(k.isBefore(LocalTime.parse(rawData.get(6).getServiceExit().toString())));
//		});
		
		// for Javascript time compare
		// Date.parse(a.toDateString() + ' 00:10:00')
		// (new Date()).getTime() 

		
		return res;
	}
	
	// TODO get dashboard by userid.
}
