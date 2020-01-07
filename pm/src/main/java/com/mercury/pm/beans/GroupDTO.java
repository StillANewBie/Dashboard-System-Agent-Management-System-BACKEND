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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "GROUPS")
public class GroupDTO {
	@Id
	private int groupId;
	@Column
	private String groupName;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "GROUP_LEVEL")
	private GroupLevelDTO groupLevelInfo;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH, mappedBy = "parentGroup")
	private List<GroupDTO> childGroups;
	@ManyToOne
	@JoinTable(name = "GROUP_RELATIONS", joinColumns = {
			@JoinColumn(name = "CHILD_GROUP_ID", referencedColumnName = "groupId") }, inverseJoinColumns = {
					@JoinColumn(name = "PARENT_GROUP_ID", referencedColumnName = "groupId") })
	private GroupDTO parentGroup;

	public GroupDTO(int groupId, String groupName, GroupLevelDTO groupLevelInfo, List<GroupDTO> childGroups,
			GroupDTO parentGroup) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.groupLevelInfo = groupLevelInfo;
		this.childGroups = childGroups;
		this.parentGroup = parentGroup;
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

	public GroupLevelDTO getGroupLevelInfo() {
		return groupLevelInfo;
	}

	public void setGroupLevelInfo(GroupLevelDTO groupLevelInfo) {
		this.groupLevelInfo = groupLevelInfo;
	}

	public List<GroupDTO> getChildGroups() {
		return childGroups;
	}

	public void setChildGroups(List<GroupDTO> childGroups) {
		this.childGroups = childGroups;
	}

	@JsonIgnore
	public GroupDTO getParentGroup() {
		return parentGroup;
	}

	@JsonIgnore
	public void setParentGroup(GroupDTO parentGroup) {
		this.parentGroup = parentGroup;
	}

	@Override
	public String toString() {
		return "GroupDTO [groupId=" + groupId + ", groupName=" + groupName + ", groupLevelInfo=" + groupLevelInfo
				+ ", childGroups=" + childGroups + ", parentGroup=" + parentGroup + "]";
	}

}
