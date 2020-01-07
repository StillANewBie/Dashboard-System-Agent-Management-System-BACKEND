package com.mercury.pm.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GROUP_LEVEL")
public class GroupLevel {
	@Id
	private int groupLevel;
	@Column
	private String groupLevelName;

	public GroupLevel(int groupLevel, String groupLevelName) {
		super();
		this.groupLevel = groupLevel;
		this.groupLevelName = groupLevelName;
	}

	public GroupLevel() {
		super();
	}

	public int getGroupLevel() {
		return groupLevel;
	}

	public void setGroupLevel(int groupLevel) {
		this.groupLevel = groupLevel;
	}

	public String getGroupLevelName() {
		return groupLevelName;
	}

	public void setGroupLevelName(String groupLevelName) {
		this.groupLevelName = groupLevelName;
	}

	@Override
	public String toString() {
		return "GroupLevelDTO [groupLevel=" + groupLevel + ", groupLevelName=" + groupLevelName + "]";
	}

}
