package com.mercury.pm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/cas")
	public List<CurrentAgentStateDTO> getCAS() {
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
//		EntityManager em = entityManagerFactory.createEntityManager();
//
//		StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("get_currentagentstates_by_groupid");
//		// set parameters
//		storedProcedure.registerStoredProcedureParameter("gid", Double.class, ParameterMode.IN);
//		storedProcedure.registerStoredProcedureParameter("res", List.class, ParameterMode.OUT);
//		storedProcedure.setParameter("subtotal", 1f);
//		// execute SP
//		storedProcedure.execute();
//		// get result
//		List<CurrentAgentStateDTO> res = (List<CurrentAgentStateDTO>)storedProcedure.getOutputParameterValue("res");
		return casd.getCASD(1);
//		return casd.getCurrentAgentStatesByGroupId(1);
	}
}
