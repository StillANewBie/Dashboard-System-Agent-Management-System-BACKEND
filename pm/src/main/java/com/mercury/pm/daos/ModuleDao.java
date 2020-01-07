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

import com.mercury.pm.beans.CurrentAgentState;
import com.mercury.pm.beans.HeatmapData;
import com.mercury.pm.jdbc.JdbcUtil;

@Repository
public class ModuleDao {

	public List<CurrentAgentState> getCurrentAgentStateByGroupId(int gid) {
		CurrentAgentState el = null;
		List<CurrentAgentState> res = new ArrayList<>();
		try (Connection connection = JdbcUtil.getConnection();
				CallableStatement ps = connection.prepareCall("select * from get_currentagentstates_by_groupid(?)");) {
			ps.setInt(1, gid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				el = new CurrentAgentState(rs.getInt("call_id"), rs.getString("phone_number"), rs.getInt("priority"),
						rs.getDate("cur_date"), rs.getTime("queue_start"), rs.getTime("queue_exit"),
						rs.getInt("queue_time"), rs.getTime("service_start"), rs.getTime("service_exit"),
						rs.getInt("service_time"), rs.getString("outcome"), rs.getString("first_name"),
						rs.getString("last_name"), rs.getString("profile_image"), rs.getString("group_name"));
				res.add(el);
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public List<CurrentAgentState> getCurrentAgentStateByGroupIdTesting(int gid) {
		CurrentAgentState el = null;
		List<CurrentAgentState> res = new ArrayList<>();
		try (Connection connection = JdbcUtil.getConnection();
				CallableStatement ps = connection.prepareCall("select * from get_currentagentstates_by_groupid_test(?)");) {
			ps.setInt(1, gid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				el = new CurrentAgentState(rs.getInt("call_id"), rs.getString("phone_number"), rs.getInt("priority"),
						rs.getDate("cur_date"), rs.getTime("queue_start"), rs.getTime("queue_exit"),
						rs.getInt("queue_time"), rs.getTime("service_start"), rs.getTime("service_exit"),
						rs.getInt("service_time"), rs.getString("outcome"), rs.getString("first_name"),
						rs.getString("last_name"), rs.getString("profile_image"), rs.getString("group_name"));
				res.add(el);
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}
	
	public List<HeatmapData> getHeatmapDataByGroupId(int gid) {
		HeatmapData el = null;
		List<HeatmapData> res = new ArrayList<>();
		try (Connection connection = JdbcUtil.getConnection();
				CallableStatement ps = connection.prepareCall("select * from getHeatmapDataByGroupId(?)");) {
			ps.setInt(1, gid);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				el = new HeatmapData(rs.getInt("call_id"), rs.getString("area_code"), rs.getDate("cur_date"),
						rs.getTime("queue_start"), rs.getTime("queue_exit"), rs.getInt("queue_time"), rs.getTime("service_start"), 
						rs.getTime("service_exit"),
						rs.getDouble(9), rs.getDouble(10));
				res.add(el);
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}
}
