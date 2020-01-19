package com.mercury.pmdsservice.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DASHBOARD_STATE")
public class DashboardState {

	@Id
	@SequenceGenerator(name = "dashboard_state_dashboard_id_seq_gen", sequenceName = "dashboard_state_dashboard_id_seq", allocationSize = 1)
	@GeneratedValue(generator = "dashboard_state_dashboard_id_seq_gen", strategy = GenerationType.AUTO)
	private int dashboardId;
	@Column
	private int userId;
	@Column
	private String dashboardName;
	@Column
	private String dashboardState;

	public DashboardState() {
		super();
	}

	public DashboardState(int dashboardId, int userId, String dashboardName, String dashboardState) {
		super();
		this.dashboardId = dashboardId;
		this.userId = userId;
		this.dashboardName = dashboardName;
		this.dashboardState = dashboardState;
	}

	public int getDashboardId() {
		return dashboardId;
	}

	public void setDashboardId(int dashboardId) {
		this.dashboardId = dashboardId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDashboardName() {
		return dashboardName;
	}

	public void setDashboardName(String dashboardName) {
		this.dashboardName = dashboardName;
	}

	public String getDashboardState() {
		return dashboardState;
	}

	public void setDashboardState(String dashboardState) {
		this.dashboardState = dashboardState;
	}

	@Override
	public String toString() {
		return "DashboardState [dashboardId=" + dashboardId + ", userId=" + userId + ", dashboardName=" + dashboardName
				+ ", dashboardState=" + dashboardState + "]";
	}

}
