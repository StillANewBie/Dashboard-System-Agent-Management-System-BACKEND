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
	public void saveUserInfo(UserInfo ui) {
		try (Connection connection = JdbcUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement("call saveuserinfo(?,?,?,?,?,?)");) {
			ps.setInt(1, ui.getId());
			ps.setString(2, ui.getFirstName());
			ps.setString(3, ui.getLastName());
			ps.setString(4, ui.getEmail());
			ps.setString(5, ui.getProfileImage());
			ps.setString(6, ui.getDescription());
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
				PreparedStatement ps = connection.prepareStatement("call saveUserRole(?,?)");) {
			ps.setInt(1, uid);
			ps.setInt(2, rid);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
