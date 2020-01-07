package com.mercury.pm.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

@Entity
//@NamedStoredProcedureQuery(name = "getCurrentAgentStatesByGroupId", procedureName = "get_currentagentstates_by_groupid", parameters = {
//		@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "groupId") }, resultClasses = CurrentAgentStateDTO.class)
public class CurrentAgentState implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private int callId;
	@Column
	private String phoneNumber;
	@Column
	private int priority;
	@Column
	private Date cur_date;
	@Column
	private Date queueStart;
	@Column
	private Date queueExit;
	@Column
	private int queueTime;
	@Column
	private Date serviceStart;
	@Column
	private Date serviceExit;
	@Column
	private int serviceTime;
	@Column
	private String outcome;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String profileImage;
	@Column
	private String groupName;

	public CurrentAgentState() {
		super();
	}

	public CurrentAgentState(int callId, String phoneNumber, int priority, Date cur_date, Date queueStart,
			Date queueExit, int queueTime, Date serviceStart, Date serviceExit, int serviceTime, String outcome,
			String firstName, String lastName, String profileImage, String groupName) {
		super();
		this.callId = callId;
		this.phoneNumber = phoneNumber;
		this.priority = priority;
		this.cur_date = cur_date;
		this.queueStart = queueStart;
		this.queueExit = queueExit;
		this.queueTime = queueTime;
		this.serviceStart = serviceStart;
		this.serviceExit = serviceExit;
		this.serviceTime = serviceTime;
		this.outcome = outcome;
		this.firstName = firstName;
		this.lastName = lastName;
		this.profileImage = profileImage;
		this.groupName = groupName;
	}

	public int getCallId() {
		return callId;
	}

	public void setCallId(int callId) {
		this.callId = callId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Date getCur_date() {
		return cur_date;
	}

	public void setCur_date(Date cur_date) {
		this.cur_date = cur_date;
	}

	public Date getQueueStart() {
		return queueStart;
	}

	public void setQueueStart(Date queueStart) {
		this.queueStart = queueStart;
	}

	public Date getQueueExit() {
		return queueExit;
	}

	public void setQueueExit(Date queueExit) {
		this.queueExit = queueExit;
	}

	public int getQueueTime() {
		return queueTime;
	}

	public void setQueueTime(int queueTime) {
		this.queueTime = queueTime;
	}

	public Date getServiceStart() {
		return serviceStart;
	}

	public void setServiceStart(Date serviceStart) {
		this.serviceStart = serviceStart;
	}

	public Date getServiceExit() {
		return serviceExit;
	}

	public void setServiceExit(Date serviceExit) {
		this.serviceExit = serviceExit;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Override
	public String toString() {
		return "CurrentAgentStateDTO [callId=" + callId + ", phoneNumber=" + phoneNumber + ", priority=" + priority
				+ ", cur_date=" + cur_date + ", queueStart=" + queueStart + ", queueExit=" + queueExit + ", queueTime="
				+ queueTime + ", serviceStart=" + serviceStart + ", serviceExit=" + serviceExit + ", ServiceTime="
				+ serviceTime + ", outcome=" + outcome + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", profileImage=" + profileImage + ", groupName=" + groupName + "]";
	}

}
