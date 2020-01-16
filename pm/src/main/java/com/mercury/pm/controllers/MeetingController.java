package com.mercury.pm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.pm.dtos.MeetingDTO;
import com.mercury.pm.services.MeetingService;

@RestController
@RequestMapping("/meeting")
public class MeetingController {

	@Autowired
	private MeetingService ms;
	
	@GetMapping("/initiator/{initiatorId}")
	public List<MeetingDTO> findMeetingsByInitiatorId(@PathVariable int initiatorId) {
		return ms.findMeetingsByInitiatorId(initiatorId);
	}
	
	@GetMapping("/invitee/{initiatorId}")
	public List<MeetingDTO> findMeetingsByInviteeId(@PathVariable int initiatorId) {
		return ms.findMeetingsByInviteeId(initiatorId);
	}
	
}
