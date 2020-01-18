package com.mercury.pm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.pm.dtos.MeetingDTO;
import com.mercury.pm.http.Response;
import com.mercury.pm.services.MeetingService;

@RestController
@RequestMapping("/meeting")
public class MeetingController {

	@Autowired
	private MeetingService ms;
	
	// TODO validations 
	
	@GetMapping("/initiator/{initiatorId}")
	public List<MeetingDTO> findMeetingsByInitiatorId(@PathVariable int initiatorId) {
		return ms.findMeetingsByInitiatorId(initiatorId);
	}
	
	@GetMapping("/invitee/{inviteeId}")
	public List<MeetingDTO> findMeetingsByInviteeId(@PathVariable int inviteeId) {
		return ms.findMeetingsByInviteeId(inviteeId);
	}
	
	@GetMapping("/invitee-un/{inviteeId}")
	public List<MeetingDTO> findUndecidedMeetingsByInviteeId(@PathVariable int inviteeId) {
		return ms.findUndecidedMeetingsByInviteeId(inviteeId);
	}
	
	@PostMapping("/initiate")
	public int initiateMeeting(@RequestParam String title, 
			@RequestParam int initiatorId, @RequestParam String memo, @RequestParam String date, 
			@RequestParam String time) {
		return ms.initiateMeeting(title, initiatorId, memo, date, time);
	}
	
	@PostMapping("/invite")
	public int saveMeetingInvitee(@RequestParam int meetingId, @RequestParam int inviteeId) {
		return ms.saveMeetingInvitee(meetingId, inviteeId);
	}
	
	@PostMapping("/decision")
	public Response alterMeetingDecision(@RequestParam int meetingInviteesId, @RequestParam int decision) {
		return ms.alterMeetingDecision(meetingInviteesId, decision);
	}
	
	@PostMapping("/cancel")
	public Response cancelMeeting(@RequestParam int meetingId) {
		return ms.cancelMeeting(meetingId);
	}
	
}
