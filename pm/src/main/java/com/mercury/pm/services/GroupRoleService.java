package com.mercury.pm.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercury.pm.beans.Group;
import com.mercury.pm.beans.GroupLevel;
import com.mercury.pm.beans.Role;
import com.mercury.pm.daos.GroupDao;
import com.mercury.pm.daos.GroupLevelDao;
import com.mercury.pm.daos.RoleDao;

@Service
public class GroupRoleService {
	@Autowired
	private GroupDao gd;
	@Autowired
	private RoleDao rd;
	@Autowired
	private GroupLevelDao gld;
	
	public Group getGroupDTOByID(int gid) {
		return gd.findById(gid).get();
	}
	
	public List<Group> getFlatGroups() {
		List<Group> gs = new ArrayList<Group>();
		Group g = gd.findById(1).get();
		
		flatGroups(g, gs);
		
		return gs;
	}
	
	private static void flatGroups(Group g, List<Group> gs) {
		if (g != null) {
			gs.add(g);
		}
		
		for (Group el : g.getChildGroups()) {
			flatGroups(el, gs);
		}
	}
	
	public List<Role> getAllRoles() {
		return rd.findAll();
	}
	
	public List<GroupLevel> getAllGroupLevel() {
		return gld.findAll();
	}
}
