package com.mercury.pm.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.mercury.pm.beans.Login;
import com.mercury.pm.beans.Role;
import com.mercury.pm.services.GroupRoleService;
import com.mercury.pm.services.UserService;

@RestController
@RequestMapping("/user-admin")
public class UserAdminController {

	@Autowired
	private GroupRoleService gs;
	
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
	
	@GetMapping("/groups") 
	public List<Group> getFlatGroups() {
		return gs.getFlatGroups();
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
		    s3client.putObject(new PutObjectRequest(bucketName, "images/" + userId + ".jpg", f).withCannedAcl(CannedAccessControlList.PublicRead));
		    f.delete();
		}
		return "success";
	}
	
	@GetMapping("/roles")
	public List<Role> getRoles() {
		return gs.getAllRoles();
	}
	
	@GetMapping("/group-level")
	public List<GroupLevel> getGroupLevels() {
		return gs.getAllGroupLevel();
	}

}
