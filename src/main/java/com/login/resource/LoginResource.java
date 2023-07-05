package com.login.resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.login.model.LoginRequest;
import com.login.service.LoginService;
@RestController
@RequestMapping("/login/users")
public class LoginResource {
	@Autowired
private  LoginService  loginService;
	private static final Logger LOGGER=LoggerFactory.getLogger(LoginResource.class);
	//http://localhost:8080/login/users/login
    @PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		LOGGER.info(request.getSearchinput());
		return this.loginService.loginAuthentication(request.getSearchinput(), request.getPassword());
	}
	//http://localhost:8080/login/users/statusapproval/bhacha33/approved
	@GetMapping("/statusapproval/{id}/{status}")
	public String accountApproval(@PathVariable String id,@PathVariable String status) {
		LOGGER.info("loginrecord statusapproval successfully in resource");
		return this.loginService.accountApproval(id,status);
		}
    }







	
