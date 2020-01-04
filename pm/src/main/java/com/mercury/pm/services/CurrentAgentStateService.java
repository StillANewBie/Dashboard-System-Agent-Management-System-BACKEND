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
import com.mercury.pm.daos.CurrentAgentStateDao;
import com.mercury.pm.jdbc.JdbcUtil;

@Service
public class CurrentAgentStateService {
	@Autowired
	private CurrentAgentStateDao casd;
	
	public List<CurrentAgentStateDTO> getCASD(int gid) {
		
		return casd.getCurrentAgentStateByGroupId(gid);
	}
}