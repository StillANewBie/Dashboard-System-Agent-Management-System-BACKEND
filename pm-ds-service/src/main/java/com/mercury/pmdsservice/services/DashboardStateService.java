package com.mercury.pmdsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercury.pmdsservice.beans.DashboardState;
import com.mercury.pmdsservice.daos.DashboardStateDao;

@Service
public class DashboardStateService {

	@Autowired
	private DashboardStateDao dsd;
	
	public DashboardState getDashboardStateByUserId(int userId) {
		return dsd.findByUserId(userId);
	}
	
	public DashboardState saveDashboardState(DashboardState ds) {
		return dsd.save(ds);
	}
}
