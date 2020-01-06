package com.mercury.pm.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "GROUPS")
public class GroupDTO {
	@Id
	private int groupId;
	@Column
	private String groupName;
	@Column
	private int groupLevel;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH, mappedBy = "parentGroup")
	private List<GroupDTO> childGroups;
	@ManyToOne
	@JoinTable(name = "GROUP_RELATIONS", joinColumns = {
			@JoinColumn(name = "CHILD_GROUP_ID", referencedColumnName = "groupId") }, inverseJoinColumns = {
					@JoinColumn(name = "PARENT_GROUP_ID", referencedColumnName = "groupId") })
	private GroupDTO parentGroup;

	public GroupDTO(int groupId, String groupName, int groupLevel, List<GroupDTO> childGroups) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.groupLevel = groupLevel;
		this.childGroups = childGroups;
	}

	public GroupDTO() {
		super();
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getGroupLevel() {
		return groupLevel;
	}

	public void setGroupLevel(int groupLevel) {
		this.groupLevel = groupLevel;
	}

	public List<GroupDTO> getChildGroups() {
		return childGroups;
	}

	public void setChildGroups(List<GroupDTO> childGroups) {
		this.childGroups = childGroups;
	}

	@Override
	public String toString() {
		return "GroupDTO [groupId=" + groupId + ", groupName=" + groupName + ", groupLevel=" + groupLevel
				+ ", childGroups=" + childGroups + "]";
	}

}
