package com.mercury.pm.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.pm.beans.GroupDTO;

public interface GroupDao extends JpaRepository<GroupDTO, Integer> {
	
}
