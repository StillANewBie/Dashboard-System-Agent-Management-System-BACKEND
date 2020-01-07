package com.mercury.pm.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USER_INFO")
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "user_info_id_seq_gen")
	@SequenceGenerator(name = "user_info_id_seq_gen", sequenceName = "user_info_id_seq", allocationSize = 1)	private int userId;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String email;
	@Column
	private String profileImage;

	@OneToOne
	@JoinColumn(name = "USER_ID")
	@JsonIgnore
	private User user;

	public UserInfo(int userId, String firstName, String lastName, String email, String profileImage) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.profileImage = profileImage;
	}

	public UserInfo() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public User getUser() {
		return user;
	}

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", profileImage=" + profileImage + ", user=" + user + "]";
	}

}
