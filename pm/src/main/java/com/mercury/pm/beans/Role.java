package com.mercury.pm.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "ROLES")
public class Role implements GrantedAuthority {
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "roles_role_id_seq_gen", sequenceName = "roles_role_id_seq", allocationSize = 1)
	@GeneratedValue(generator = "roles_role_id_seq_gen", strategy = GenerationType.AUTO)
	private int roleId;
	@Column
	private String roleName;

	public Role(int id, String roleName) {
		super();
		this.roleId = id;
		this.roleName = roleName;
	}

	public Role() {
		super();
	}

	public int getId() {
		return roleId;
	}

	public void setId(int id) {
		this.roleId = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "Role [id=" + roleId + ", roleName=" + roleName + "]";
	}

	@Override
	public String getAuthority() {
		return this.getRoleName();
	}

}
