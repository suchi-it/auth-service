
package com.login.service;

import org.springframework.http.ResponseEntity;

import com.login.model.CreateUsersRequest;
import com.login.model.UpdateUsersRequest;

public interface UserService {
	String createUser(CreateUsersRequest request);

	ResponseEntity<?> getUsers(String searchInput);

	ResponseEntity<?> getUserById(String userId);

	ResponseEntity<?> updateUser(UpdateUsersRequest request);

	ResponseEntity<?> deleteUser(String userId);
}
