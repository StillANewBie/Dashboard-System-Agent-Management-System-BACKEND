package com.mercury.pm.dtos;

import java.util.Date;

public class MeetingDTO {
	public int meetingId;
	public int meetingInviteesId;
	public String meetingTitle;
	public int initiatorId;
	public String initiatorFirstName;
	public String initiatorLastName;
	public String initiatorEmail;
	public String initiatorImage;
	public int inviteeId;
	public String inviteeFirstName;
	public String inviteeLastName;
	public String inviteeEmail;
	public String inviteeImage;
	public String meetingMemo;
	public Date date;
	public Date time;
	public boolean meetingCancelled;
	public int result;
}
