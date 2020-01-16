package com.mercury.pm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercury.pm.daos.MeetingJDBCDao;
import com.mercury.pm.dtos.MeetingDTO;

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
}
