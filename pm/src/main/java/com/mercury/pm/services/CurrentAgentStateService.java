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

@Service
public class CurrentAgentStateService {
	@Autowired
	private CurrentAgentStateDao casd;
	
	public List<CurrentAgentStateDTO> getCASD(int gid) {
		CurrentAgentStateDTO el = null;
		List<CurrentAgentStateDTO> res = new ArrayList<>();
		try (Connection connection = JdbcUtil.getConnection();
				CallableStatement ps = connection.prepareCall("select * from get_currentagentstates_by_groupid_test(1)");
				) {
//			ps.registerOutParameter(1, Types.OTHER);
//			ps.setInt(2, gid);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				el = new CurrentAgentStateDTO(rs.getInt("call_id"), rs.getString("phone_number"), rs.getInt("priority"), rs.getDate("cur_date"), rs.getDate("queue_start"),
												rs.getDate("queue_exit"), rs.getInt("queue_time"), rs.getDate("service_start"), rs.getDate("service_exit"),
												rs.getInt("service_time"), rs.getString("outcome"), rs.getString("first_name"), rs.getString("last_name"),
												rs.getString("profile_image"), rs.getString("group_name"));
				res.add(el);
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
		return res;
	}
}

class JdbcUtil {
	// for connecting to DB by using JDBC
	// need below 4 informations:
	
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost:5432/CallCenterDB";
	private static final String USERNAME = "bu";
	private static final String PASSWORD = "password";
	
	public static Connection getConnection(){
		Connection conn = null;
		try{
			// Class.forName() will load the class into Perm Gen during runtime
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}catch(Exception e){
			System.err.println(e);
		}
		return conn;
	}
}