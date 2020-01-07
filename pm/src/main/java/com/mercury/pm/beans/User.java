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
	private int id;
	@Column
	private String username;
	@Column
	private String password;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinTable(name = "USER_GROUP", joinColumns = {
			@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID") }, inverseJoinColumns = {
					@JoinColumn(name = "GROUP_ID", referencedColumnName = "GROUP_ID") })
	private List<Group> groups;

	public User() {
		super();
	}

	public User(int id, String username, String password, List<Group> groups) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.groups = groups;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", groups=" + groups + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return groups;
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
