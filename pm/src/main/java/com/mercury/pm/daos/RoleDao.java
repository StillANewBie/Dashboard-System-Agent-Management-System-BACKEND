package com.mercury.pm.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.pm.beans.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {

}
