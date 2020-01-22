package com.mercury.pm.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercury.pm.beans.CurrentAgentState;
import com.mercury.pm.beans.UserInfo;
import com.mercury.pm.jdbc.JdbcUtil;

@Repository
public class UserJdbcDao {
	public void saveUserInfo(UserInfo ui, int uid, boolean register) {
		try (Connection connection = JdbcUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement("call saveuserinfo(?,?,?,?,?,?,?)");) {
			ps.setInt(1, uid);
			ps.setString(2, ui.getFirstName());
			ps.setString(3, ui.getLastName());
			ps.setString(4, ui.getEmail());
			ps.setString(5, ui.getProfileImage());
			ps.setString(6, ui.getDescription());
			ps.setBoolean(7, register);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void saveUserGroup(int uid, int gid) {
		try (Connection connection = JdbcUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement("call saveUserGroup(?,?)");) {
			ps.setInt(1, uid);
			ps.setInt(2, gid);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void saveUserRole(int uid, int rid) {
		try (Connection connection = JdbcUtil.getConnection();
				CallableStatement ps = connection.prepareCall("call saveuserrole_revised(?,?)");) {
			ps.setInt(1, uid);
			ps.setInt(2, rid);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getUndecidedEventsByUserId(int uid) {
		try (Connection connection = JdbcUtil.getConnection();
			CallableStatement cs = connection.prepareCall("select * from getundecidedeventsbyuserid(?)")) {
			cs.setInt(1, uid);
			ResultSet rs = cs.executeQuery();
			
			if (rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
}
