package com.mercury.pm.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mercury.pm.beans.CurrentAgentStateDTO;
import com.mercury.pm.jdbc.JdbcUtil;

@Repository
public class CurrentAgentStateDao {

	public List<CurrentAgentStateDTO> getCurrentAgentStateByGroupId(int gid) {
		CurrentAgentStateDTO el = null;
		List<CurrentAgentStateDTO> res = new ArrayList<>();
		try (Connection connection = JdbcUtil.getConnection();
				CallableStatement ps = connection.prepareCall("select * from get_currentagentstates_by_groupid(?)");
				) {
			ps.setInt(1, gid);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				el = new CurrentAgentStateDTO(rs.getInt("call_id"), rs.getString("phone_number"), rs.getInt("priority"), rs.getDate("cur_date"), rs.getTime("queue_start"),
												rs.getTime("queue_exit"), rs.getInt("queue_time"), rs.getTime("service_start"), rs.getTime("service_exit"),
												rs.getInt("service_time"), rs.getString("outcome"), rs.getString("first_name"), rs.getString("last_name"),
												rs.getString("profile_image"), rs.getString("group_name"));
				res.add(el);
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return res;
	}}