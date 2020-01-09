package com.mercury.pm.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mercury.pm.beans.Group;
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
	
	@PostMapping("/upload-image")
	public String imageUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes ){
		if (file.isEmpty()) {
			return "empty";
		}

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(file.getName());
		System.out.println(file.getContentType());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());

		return "success";
	}
}
