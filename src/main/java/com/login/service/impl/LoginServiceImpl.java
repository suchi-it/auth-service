package com.login.service.impl;
import java.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import io.micrometer.core.instrument.util.StringUtils;
import com.login.constant.UsersConstants;
import com.login.model.User;
import com.login.resource.LoginResource;
import com.login.service.LoginService;
import com.mongodb.client.result.UpdateResult;
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
	private MongoTemplate mongoTemplate;
	//private User request;
	private static final Logger LOGGER=LoggerFactory.getLogger(LoginResource.class);
	//private static final User request = null;
     @Override
	public ResponseEntity<?> loginAuthentication(String searchinput, String password) {
		// TODO Auto-generated method stub
    	 LOGGER.info("login service successfully in implimentation");
    	 byte[] decryptedPassword = Base64.getDecoder().decode(password);
 		String encryptPassword = Base64.getEncoder().encodeToString(decryptedPassword);
		if (StringUtils.isEmpty(searchinput) || (StringUtils.isEmpty(encryptPassword))) {
			return new ResponseEntity<>("email or Password should not be blank", HttpStatus.BAD_REQUEST);
		}
		Criteria criteria = new Criteria();
		criteria.orOperator(Criteria.where("email").is(searchinput), Criteria.where("phone").is(searchinput),
				Criteria.where("_id").is(searchinput));
	       Query query = new Query();
		query.addCriteria(criteria);
		query.addCriteria(Criteria.where("status").is(UsersConstants.APPROVED));
		if (this.mongoTemplate.count(query, User.class) == 0) {
			return new ResponseEntity<>("Email doesn't exist", HttpStatus.BAD_REQUEST);
		}
		query.addCriteria(Criteria.where("password").is(encryptPassword));
		User user = this.mongoTemplate.findOne(query, User.class);
		if (user != null) {
			return new ResponseEntity<>("Login Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Invalid credentials", HttpStatus.FORBIDDEN);
		}
}
	public String accountApproval(String id, String status) {
		// TODO Auto-generated method stub
		LOGGER.info(" loginservice created successfully in implimentation");
		System.out.println(status);
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(id));
		Update updateDefi = new Update().set("status", status);
		UpdateResult updateFirst = mongoTemplate.updateFirst(query, updateDefi, User.class);
		System.out.println(updateFirst.getMatchedCount());
		String msg = "";
		if (updateFirst != null) {
			msg = "User Status Approved" + id;
		} 
		else {
		msg = "User Status is Rejected" + id;
		
		}
		return msg;
	}
}
