package com.mercury.pm.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mercury.pm.dtos.MeetingDTO;
import com.mercury.pm.dtos.OutcomeDTO;
import com.mercury.pm.http.Response;
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
				m.inviteeFirstName = rs.getString("invitee_first_name");
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
				m.initiatorFirstName = rs.getString("initiator_first_name");
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

	public List<MeetingDTO> findUndecidedMeetingsByInviteeId(int inviteeId) {
		List<MeetingDTO> res = new ArrayList<MeetingDTO>();
		try (Connection connection = JdbcUtil.getConnection();
				CallableStatement cs = connection.prepareCall("select * from findundecidedmeetingsbyinviteeid(?)")) {
			cs.setInt(1, inviteeId);

			ResultSet rs = cs.executeQuery();

			while (rs.next()) {
				MeetingDTO m = new MeetingDTO();

				m.meetingId = rs.getInt("meeting_id");
				m.meetingInviteesId = rs.getInt(2);
				m.meetingTitle = rs.getString("meeting_title");
				m.initiatorId = rs.getInt("meeting_invitee_id");
				m.initiatorFirstName = rs.getString("initiator_first_name");
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

	public int initiateMeeting(String title, int initiatorId, String memo, String date, String time) {
		int res = 0;
		try (Connection connection = JdbcUtil.getConnection();
				CallableStatement ps = connection.prepareCall("select * from savemeeting(?, ?, ? ,? , ?)")) {
			ps.setString(1, title);
			ps.setInt(2, initiatorId);
			ps.setString(3, memo);
			ps.setString(4, date);
			ps.setString(5, time);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public int saveMeetingInvitee(int meetingId, int inviteeId) {
		int res = 0;
		try (Connection connection = JdbcUtil.getConnection();
				CallableStatement ps = connection.prepareCall("select * from savemeetinginvitee(?, ?)")) {
			ps.setInt(1, meetingId);
			ps.setInt(2, inviteeId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public Response alterMeetingDecision(int meetingInviteesId, int decision) {
		try (Connection connection = JdbcUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement("call altermeetingresult(?, ?)")) {
			ps.setInt(1, meetingInviteesId);
			ps.setInt(2, decision);

			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return new Response(false, "unable to alter decision");
		}
		
		return new Response(true, 200, "decision altered");
	}

	public Response cancelMeeting(int meetingId) {
		try (Connection connection = JdbcUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement("call cancelmeeting(?)")) {
			ps.setInt(1, meetingId);

			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return new Response(false, "unable to cancel meeting");
		}
		
		return new Response(true, 200, "meeting canceled");
	}

}
