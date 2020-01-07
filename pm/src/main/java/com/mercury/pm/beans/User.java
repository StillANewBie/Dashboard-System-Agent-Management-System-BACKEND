package com.mercury.pm.beans;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "Users")
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "users_user_id_seq_gen", sequenceName = "users_user_id_seq", allocationSize = 1)
	@GeneratedValue(generator = "users_user_id_seq_gen", strategy = GenerationType.AUTO)
	private int userId;
	@Column
	private String username;
	@Column
	private String password;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinTable(name = "USER_GROUP", joinColumns = {
			@JoinColumn(name = "USER_ID", referencedColumnName = "userId") }, inverseJoinColumns = {
					@JoinColumn(name = "GROUP_ID", referencedColumnName = "groupId") })
	private Group group;
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private UserInfo userInfo;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinTable(name = "USER_ROLE", joinColumns = {
			@JoinColumn(name = "USER_ID", referencedColumnName = "userId") }, inverseJoinColumns = {
					@JoinColumn(name = "ROLE_ID", referencedColumnName = "roleId") })
	private List<Role> roles;

	public User() {
		super();
	}

	public User(int userId, String username, String password, Group group, UserInfo userInfo, List<Role> roles) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.group = group;
		this.userInfo = userInfo;
		this.roles = roles;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroups(Group group) {
		this.group = group;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", group=" + group
				+ ", userInfo=" + userInfo + ", roles=" + roles + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
