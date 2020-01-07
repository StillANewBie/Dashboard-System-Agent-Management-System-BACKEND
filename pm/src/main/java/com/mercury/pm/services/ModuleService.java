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

import com.mercury.pm.beans.CurrentAgentState;
import com.mercury.pm.beans.HeatmapData;
import com.mercury.pm.daos.ModuleDao;
import com.mercury.pm.jdbc.JdbcUtil;

@Service
public class ModuleService {
	@Autowired
	private ModuleDao md;
	
	public List<CurrentAgentState> getCurrentAgentStateByGroupId(int gid) {
		
		return md.getCurrentAgentStateByGroupId(gid);
	}
	public List<CurrentAgentState> getCurrentAgentStateByGroupIdTesting(int gid) {
		
		return md.getCurrentAgentStateByGroupIdTesting(gid);
	}
	
	public List<HeatmapData> getHeatmapDataByGroupId(int gid) {
		return md.getHeatmapDataByGroupId(gid);
	}
}