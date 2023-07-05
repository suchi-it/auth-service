package com.login.service;

import org.springframework.http.ResponseEntity;

public interface LoginService {
	ResponseEntity<?> loginAuthentication(String searchinput, String password);

	String accountApproval(String id, String status);
}
