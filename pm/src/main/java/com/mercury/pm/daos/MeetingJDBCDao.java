package com.mercury.pm.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mercury.pm.dtos.MeetingDTO;
import com.mercury.pm.jdbc.JdbcUtil;

@Repository
public class MeetingJDBCDao {
	public List<MeetingDTO> findMeetingsByInitiatorId(int initiatorId) {
		List<MeetingDTO> res = new ArrayList<MeetingDTO>();
		try (Connection connection = JdbcUtil.getConnection();
				CallableStatement cs = connection.prepareCall("select * from findmeetingsbyinitiatorid(?)")) {
			cs.setInt(1, initiatorId);
			
			ResultSet rs = cs.executeQuery();
			
			while (rs.next()) {
				MeetingDTO m = new MeetingDTO();
				
				m.meetingId = rs.getInt("meeting_id");
				m.inviteeId = rs.getInt("meeting_invitees_id");
				m.meetingTitle = rs.getString("meeting_title");
				m.inviteeId = rs.getInt("meeting_invitee_id");
				m.inviteeFirstname = rs.getString("invitee_first_name");
				m.inviteeLastName = rs.getString("invitee_last_name");
				m.inviteeEmail = rs.getString(7);
				m.inviteeImage = rs.getString(8);
				m.initiatorId = initiatorId;
				m.meetingMemo = rs.getString(10);
				m.date = rs.getDate(11);
				m.time = rs.getTime(12);
				m.result = rs.getInt(13);
				m.meetingCancelled = rs.getBoolean(14);
				
				res.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public List<MeetingDTO> findMeetingsByInviteeId(int inviteeId) {
		List<MeetingDTO> res = new ArrayList<MeetingDTO>();
		try (Connection connection = JdbcUtil.getConnection();
				CallableStatement cs = connection.prepareCall("select * from findmeetingsbyinviteeid(?)")) {
			cs.setInt(1, inviteeId);
			
			ResultSet rs = cs.executeQuery();
			
			while (rs.next()) {
				MeetingDTO m = new MeetingDTO();
				
				m.meetingId = rs.getInt("meeting_id");
				m.meetingInviteesId = rs.getInt(2);
				m.meetingTitle = rs.getString("meeting_title");
				m.initiatorId = rs.getInt("meeting_invitee_id");
				m.initiatorFirstname = rs.getString("initiator_first_name");
				m.initiatorLastName = rs.getString("initiator_last_name");
				m.initiatorEmail = rs.getString(7);
				m.initiatorImage = rs.getString(8);
				m.inviteeId = inviteeId;
				m.meetingMemo = rs.getString(10);
				m.date = rs.getDate(11);
				m.time = rs.getTime(12);
				m.result = rs.getInt(13);
				m.meetingCancelled = rs.getBoolean(14);
				
				res.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
}
