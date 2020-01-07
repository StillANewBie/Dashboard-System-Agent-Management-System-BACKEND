package com.mercury.pm.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.pm.beans.Group;

public interface GroupDao extends JpaRepository<Group, Integer> {
	
}
