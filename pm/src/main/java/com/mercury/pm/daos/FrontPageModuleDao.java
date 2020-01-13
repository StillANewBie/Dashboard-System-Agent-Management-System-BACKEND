package com.mercury.pm.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mercury.pm.beans.CallTotalTimeDTO;
import com.mercury.pm.beans.OutcomeDTO;
import com.mercury.pm.jdbc.JdbcUtil;

@Repository
public class FrontPageModuleDao {
	public List<OutcomeDTO> getOutcomesByDays(int days) {
		List<OutcomeDTO> res = new ArrayList<>(); 
		try (Connection connection = JdbcUtil.getConnection();
				CallableStatement ps = connection.prepareCall("select * from getoutcomesbydays(?)")) {
			ps.setInt(1, days);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				OutcomeDTO tmp = new OutcomeDTO();
				tmp.daysBeforeNow = days;
				tmp.outcomeType = rs.getString("outcome_name"); 
				tmp.count = rs.getInt("count");
				res.add(tmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public CallTotalTimeDTO getCallTotalTimeByGroupIdAndDays(int gid, int days) {
		CallTotalTimeDTO res = new CallTotalTimeDTO();
		try (Connection connection = JdbcUtil.getConnection();
				CallableStatement ps = connection.prepareCall("select * from gettotalcalltimebytimeandgroup(?,?)")) {
			ps.setInt(1, days);
			ps.setInt(2, gid);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				res.days = days;
				res.groupId = gid;
				res.vruTimeTotal = rs.getInt("vru_time_total");
				res.queueTimeTotal = rs.getInt("queue_time_total");
				res.serviceTimeTotal = rs.getInt("service_time_total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
}
