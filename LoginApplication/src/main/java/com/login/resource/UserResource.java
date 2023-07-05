package com.login.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.login.model.CreateUsersRequest;
import com.login.model.UpdateUsersRequest;
import com.login.service.UserService;
@RestController
@RequestMapping("/login/users")
public class UserResource {
	@Autowired
	UserService userService;
	private static final Logger LOGGER=LoggerFactory.getLogger(UserResource.class);

	//http://localhost:8080/login/users/createuser
	@PostMapping("/createuser")
	public ResponseEntity<?> createUser(@RequestBody CreateUsersRequest request) {
		LOGGER.info("userrecord created successfully in resource");
		return new ResponseEntity<String>(userService.createUser(request),HttpStatus.OK);
	}
	//http://localhost:8080/login/users/getusers
	@GetMapping("/getusers")
	public ResponseEntity<?> getUsers(@RequestParam(required = false) String searchInput) {
		LOGGER.info("userrecord getusers successfully in resource");
		return this.userService.getUsers(searchInput);
	}
	//http://localhost:8080/login/users/getuser?userId=analak8
	@GetMapping("/getuser")
	public ResponseEntity<?> getUserById(@RequestParam String userId) {
		LOGGER.info("userrecord getuserbyid successfully in resource");
		return this.userService.getUserById(userId);
    }
	//http://localhost:8080/login/users/updateuser
	@PutMapping("/updateuser")
	public ResponseEntity<?> updateUser(@RequestBody UpdateUsersRequest request) {
		LOGGER.info("userrecord update successfully in resource");
		return this.userService.updateUser(request);
	}
	//http://localhost:8080/login/users/deleteuser?userId=analak8
	@DeleteMapping("/deleteuser")
	public ResponseEntity<?> deleteUser(@RequestParam String userId) {
		LOGGER.info("userrecord delete successfully in resource");
		return this.userService.deleteUser(userId);
	}
}

