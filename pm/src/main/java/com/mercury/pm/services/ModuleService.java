package com.mercury.pm.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercury.pm.beans.CurrentAgentStateDTO;
import com.mercury.pm.beans.HeatmapDataDTO;
import com.mercury.pm.daos.ModuleDao;
import com.mercury.pm.jdbc.JdbcUtil;

@Service
public class ModuleService {
	@Autowired
	private ModuleDao md;
	
	public List<CurrentAgentStateDTO> getCurrentAgentStateByGroupId(int gid) {
		
		return md.getCurrentAgentStateByGroupId(gid);
	}
	public List<CurrentAgentStateDTO> getCurrentAgentStateByGroupIdTesting(int gid) {
		
		return md.getCurrentAgentStateByGroupIdTesting(gid);
	}
	
	public List<HeatmapDataDTO> getHeatmapDataByGroupId(int gid) {
		return md.getHeatmapDataByGroupId(gid);
	}
}