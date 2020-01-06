package com.mercury.pm.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class HeatmapDataDTO {
	@Id
	private int callId;
	@Column
	private String areaCode;
	@Column
	private Date curDate;
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

	public HeatmapDataDTO() {
		super();
	}

	public HeatmapDataDTO(int callId, String areaCode, Date curDate, Date queueStart, Date queueExit, int queueTime,
			Date serviceStart, Date serviceExit) {
		super();
		this.callId = callId;
		this.areaCode = areaCode;
		this.curDate = curDate;
		this.queueStart = queueStart;
		this.queueExit = queueExit;
		this.queueTime = queueTime;
		this.serviceStart = serviceStart;
		this.serviceExit = serviceExit;
	}

	public int getCallId() {
		return callId;
	}

	public void setCallId(int callId) {
		this.callId = callId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Date getCurDate() {
		return curDate;
	}

	public void setCurDate(Date curDate) {
		this.curDate = curDate;
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

	@Override
	public String toString() {
		return "HeatmapDataDTO [callId=" + callId + ", areaCode=" + areaCode + ", curDate=" + curDate + ", queueStart="
				+ queueStart + ", queueExit=" + queueExit + ", queueTime=" + queueTime + ", serviceStart="
				+ serviceStart + ", serviceExit=" + serviceExit + "]";
	}

}
