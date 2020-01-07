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

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "GROUPS")
public class Group implements GrantedAuthority {
	private static final long serialVersionUID = 1L;
	@Id
	private int groupId;
	@Column
	private String groupName;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "GROUP_LEVEL")
	private GroupLevel groupLevelInfo;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH, mappedBy = "parentGroup")
	private List<Group> childGroups;
	@ManyToOne
	@JoinTable(name = "GROUP_RELATIONS", joinColumns = {
			@JoinColumn(name = "CHILD_GROUP_ID", referencedColumnName = "groupId") }, inverseJoinColumns = {
					@JoinColumn(name = "PARENT_GROUP_ID", referencedColumnName = "groupId") })
	private Group parentGroup;

	public Group(int groupId, String groupName, GroupLevel groupLevelInfo, List<Group> childGroups,
			Group parentGroup) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.groupLevelInfo = groupLevelInfo;
		this.childGroups = childGroups;
		this.parentGroup = parentGroup;
	}

	public Group() {
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

	public GroupLevel getGroupLevelInfo() {
		return groupLevelInfo;
	}

	public void setGroupLevelInfo(GroupLevel groupLevelInfo) {
		this.groupLevelInfo = groupLevelInfo;
	}

	public List<Group> getChildGroups() {
		return childGroups;
	}

	public void setChildGroups(List<Group> childGroups) {
		this.childGroups = childGroups;
	}

	@JsonIgnore
	public Group getParentGroup() {
		return parentGroup;
	}

	@JsonIgnore
	public void setParentGroup(Group parentGroup) {
		this.parentGroup = parentGroup;
	}

	@Override
	public String toString() {
		return "GroupDTO [groupId=" + groupId + ", groupName=" + groupName + ", groupLevelInfo=" + groupLevelInfo
				+ ", childGroups=" + childGroups + ", parentGroup=" + parentGroup + "]";
	}

	@Override
	public String getAuthority() {
		return this.getGroupLevelInfo().getGroupLevelName();
	}

}
