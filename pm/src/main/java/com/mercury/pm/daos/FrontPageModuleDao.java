package com.mercury.pm.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

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
}
