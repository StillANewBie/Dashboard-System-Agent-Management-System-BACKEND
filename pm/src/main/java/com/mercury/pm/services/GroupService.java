package com.mercury.pm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercury.pm.beans.GroupDTO;
import com.mercury.pm.daos.GroupDao;

@Service
public class GroupService {
	@Autowired
	private GroupDao gd;
	
	public GroupDTO getGroupDTOByID(int gid) {
		return gd.findById(gid).get();
	}
}
