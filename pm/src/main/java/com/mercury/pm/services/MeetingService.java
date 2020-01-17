package com.mercury.pm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercury.pm.daos.MeetingJDBCDao;
import com.mercury.pm.dtos.MeetingDTO;
import com.mercury.pm.http.Response;

@Service
public class MeetingService {
	
	@Autowired
	private MeetingJDBCDao mjd;
	
	public List<MeetingDTO> findMeetingsByInitiatorId(int initiatorId) {
		return mjd.findMeetingsByInitiatorId(initiatorId);
	}
	
	public List<MeetingDTO> findMeetingsByInviteeId(int inviteeId) {
		return mjd.findMeetingsByInviteeId(inviteeId);
	}
	
	public List<MeetingDTO> findUndecidedMeetingsByInviteeId(int inviteeId) {
		return mjd.findUndecidedMeetingsByInviteeId(inviteeId);
	}
	
	public int initiateMeeting(String title, int initiatorId, String memo, String date, String time) {
		return mjd.initiateMeeting(title, initiatorId, memo, date, time);
	}
	
	public int saveMeetingInvitee(int meetingId, int inviteeId) {
		return mjd.saveMeetingInvitee(meetingId, inviteeId);
	}
	
	public void alterMeetingDecision(int meetingInviteesId, int decision) {
		mjd.alterMeetingDecision(meetingInviteesId, decision);
	}
	
	public Response cancelMeeting(int meetingId) {
		return mjd.cancelMeeting(meetingId);
	}
}
