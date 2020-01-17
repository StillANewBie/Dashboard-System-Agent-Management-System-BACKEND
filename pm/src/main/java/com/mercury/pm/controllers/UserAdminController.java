package com.mercury.pm.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.mercury.pm.beans.Group;
import com.mercury.pm.beans.GroupLevel;
import com.mercury.pm.beans.Role;
import com.mercury.pm.beans.User;
import com.mercury.pm.beans.UserInfo;
import com.mercury.pm.http.Response;
import com.mercury.pm.security.jwt.JwtRequest;
import com.mercury.pm.security.jwt.JwtTokenUtil;
import com.mercury.pm.services.GroupRoleService;
import com.mercury.pm.services.UserService;

@RestController
@RequestMapping("/user-admin")
public class UserAdminController {

	@Autowired
	private GroupRoleService groupRoleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@GetMapping("/users")
	public List<User> getAllUsers(HttpServletRequest request) {
		String jwtToken = request.getHeader("Authorization").substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		User temp = userService.findByUsername(username);
		if (temp != null) {
			if (temp.getRoles().stream().filter(el -> el.getRoleName().equals("ADMIN")).findAny().isPresent() )
			return userService.getAllUsers();
		}

		return null;
	}
	
	@GetMapping("/group")
	public Group getFirstGroup() {
		return groupRoleService.getGroupDTOByID(1);
	}
	
	@GetMapping("/groups") 
	public List<Group> getFlatGroups() {
		return groupRoleService.getFlatGroups();
	}

	// TODO redundant
	@PostMapping("/user-info/{uid}/{register}")
	public void saveUserInfo(@RequestBody UserInfo ui, @PathVariable int uid, @PathVariable boolean register) {
		userService.saveUserInfo(ui, uid, register);
	}
	
	@GetMapping("/current-user")
	public User getCurrentUser(HttpServletRequest request) {
		String jwtToken = request.getHeader("Authorization").substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		return userService.findByUsername(username);
	}
	
	@PostMapping("/user-group")
	public void saveUserGroup(@RequestParam("userId") int uid, @RequestParam("groupId") int gid ) {
		if (uid != 0 && gid != 0 &&  userService.findByUserId(uid) != null && groupRoleService.getGroupDTOByID(gid) != null) {
			userService.saveUserGroup(uid, gid);
		}
	}
	
	@PostMapping("/user-role")
	public void saveUserRole(@RequestParam("userId") int uid, @RequestParam("roleId") int rid ) {
		if (uid != 0 && rid != 0 &&  userService.findByUserId(uid) != null 
				&& groupRoleService.getAllRoles().stream().filter(el -> el.getId() == rid).findAny().isPresent()) {
			userService.saveUserRole(uid, rid);
		}
	}
	
	@PostMapping("/upload-image")
	public String imageUpload(@RequestParam("data") MultipartFile file, @RequestParam("userId") int userId) throws IOException{
		// TODO change file name to userID
		File f = new File(System.getProperty("java.io.tmpdir") + "/" + "tmp.jpg");
		try {
			file.transferTo(f);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AWSCredentials credentials = new BasicAWSCredentials(
				  "AKIA4ODCR2OKWYQYQUXG", 
				  "BYfH+jcI8GB+vnazbMvK2o+gHM553Q1Hg6i5KVu4"
				);
		
		AmazonS3 s3client = AmazonS3ClientBuilder
				  .standard()
				  .withCredentials(new AWSStaticCredentialsProvider(credentials))
				  .withRegion(Regions.US_EAST_1)
				  .build();
		
		String bucketName = "mercury-pm-images";
		if(s3client.doesBucketExist(bucketName)) {
			s3client.deleteObject(bucketName, "images/" + userId + ".jpg");
		    s3client.putObject(new PutObjectRequest(bucketName, "images/" + userId + ".jpg", f).withCannedAcl(CannedAccessControlList.PublicReadWrite));
		}
	    f.delete();
		return "success";
	}
	
	@PostMapping("/active")
	public Response saveUserRole(@RequestParam("userId") int uid, @RequestParam("active") boolean active) {
		User u = userService.findByUserId(uid);
		u.setActive(active);
		return userService.saveUser(u);
	}
	
	@PostMapping("/register")
	public User registerUser(@RequestParam("username") String username, @RequestParam("password") String password) {
		User u = new User();
		u.setActive(true);
		u.setPassword(password);
		u.setUsername(username);
		return userService.register(u);
	}
	
	@GetMapping("/roles")
	public List<Role> getRoles() {
		return groupRoleService.getAllRoles();
	}
	
	@GetMapping("/group-level")
	public List<GroupLevel> getGroupLevels() {
		return groupRoleService.getAllGroupLevel();
	}
	
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable int id) {
		return userService.findByUserId(id);
	}

}
