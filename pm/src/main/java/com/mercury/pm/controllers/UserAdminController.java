package com.mercury.pm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.mercury.pm.beans.Group;
import com.mercury.pm.beans.JView;
import com.mercury.pm.beans.Login;
import com.mercury.pm.services.GroupService;
import com.mercury.pm.services.UserService;

@RestController
@RequestMapping("/user-admin")
public class UserAdminController {

	@Autowired
	private GroupService gs;
	
	@Autowired
	private UserService us;

	@GetMapping("/users")
	public List<Login> getAllUsers() {
		return us.getAllUsers();
	}
	
	@GetMapping("/group")
	public Group getFirstGroup() {
		return gs.getGroupDTOByID(1);
	}
}
