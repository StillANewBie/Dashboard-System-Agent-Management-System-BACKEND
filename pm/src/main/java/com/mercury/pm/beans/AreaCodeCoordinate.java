package com.mercury.pm.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "area_code_coordinate")
public class AreaCodeCoordinate {
	@Id
	private String areaCode;
	@Column
	private double latitude;
	@Column
	private double longitude;

	public AreaCodeCoordinate() {
		super();
	}

	public AreaCodeCoordinate(String areaCode, double latitude, double longitude) {
		super();
		this.areaCode = areaCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "AreaCodeCoordinateDTO [areaCode=" + areaCode + ", latitude=" + latitude + ", longitude=" + longitude
				+ "]";
	}

}
