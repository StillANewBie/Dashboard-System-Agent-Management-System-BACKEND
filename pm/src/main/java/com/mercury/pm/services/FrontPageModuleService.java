package com.mercury.pm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercury.pm.beans.CallTotalTimeDTO;
import com.mercury.pm.beans.OutcomeDTO;
import com.mercury.pm.daos.FrontPageModuleDao;

@Service
public class FrontPageModuleService {
	
	@Autowired
	private FrontPageModuleDao frontPageModuleDao;
	
	public List<OutcomeDTO> getOutcomesByDays(int days) {
		return frontPageModuleDao.getOutcomesByDays(days);
	}
	
	public CallTotalTimeDTO getCallTotalTimeByGroupIdAndDays(int gid, int days) {
		return frontPageModuleDao.getCallTotalTimeByGroupIdAndDays(gid, days);
	}
}
