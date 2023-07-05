package com.login.service.impl;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.login.constant.UsersConstants;
import com.login.model.CreateUsersRequest;
import com.login.model.Mapping;
import com.login.model.UpdateUsersRequest;
import com.login.model.User;
import com.login.resource.UserResource;
import com.login.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private MongoTemplate mongoTemplate;
	private static final Logger LOGGER=LoggerFactory.getLogger(UserResource.class);

	public String createUser(CreateUsersRequest request){
		// TODO Auto-generated method stub
		LOGGER.info("userrecord created successfully in implimentation");
		Random rand = new Random();
		Criteria criteria = new Criteria();
        criteria.orOperator(
				Criteria.where("email")
						.regex(Pattern.compile(request.getEmail(), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
				Criteria.where("phone")
						.regex(Pattern.compile(request.getPhone(), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
		Query query = new Query(criteria);
		User user = this.mongoTemplate.findOne(query, User.class);
		if (user == null) {
			user = new User();
			// Customer newcustomer = new Customer();
			BeanUtils.copyProperties(request, user);
			user.setUserId(
					user.getFirstName().substring(0, 3) + user.getLastName().substring(0, 3) + rand.nextInt(100));
			byte[] decryptedPassword = Base64.getDecoder().decode(request.getPassword());
			 //System.out.println("hi"+ decryptedPassword);
            String encryptPassword = Base64.getEncoder().encodeToString(decryptedPassword);
            //System.out.println("hi"+ encryptPassword);
            user.setPassword(encryptPassword);
			user.setStatus(UsersConstants.PENDING);
			 user.setRole(UsersConstants.USERS);
			 user.setCreatedAt(new Date(System.currentTimeMillis()));
			 Mapping mapping =new Mapping();
			 mapping.setId(
					 user.getFirstName().substring(0, 3) + user.getLastName().substring(0, 3) + rand.nextInt(100));
			 mapping.setUsertype("customers");
			 this.mongoTemplate.insert(mapping);
		this.mongoTemplate.insert(user);
			return "User successfully created with user id" + user.getUserId();
		} else {
			return "user already exist";
		}
	}
	public ResponseEntity<?> getUsers(String searchInput) {
		// TODO Auto-generated method stub
		LOGGER.info("userrecord getusers successfully in implimentation");
		Query query = new Query();
		if (StringUtils.isNotEmpty(searchInput)) {
			query = this.getSearchQuery(searchInput);
		}
		Set<User> users =new HashSet<>(this.mongoTemplate.find(query, User.class));
		if (!CollectionUtils.isEmpty(users)) {
			return new ResponseEntity<>(users, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new HashSet<>(), HttpStatus.OK);
		}
	}
    private Query getSearchQuery(String searchInput){
		// TODO Auto-generated method stub
		Query query = new Query();
		List<Criteria> criterias = new LinkedList<>();
		Criteria searchCriteria = new Criteria();
		searchCriteria.orOperator(
				Criteria.where("_id")
						.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
				Criteria.where("name")
						.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
				Criteria.where("email")
						.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
				Criteria.where("address.city")
						.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
				Criteria.where("address.state")
						.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
				Criteria.where("address.country")
						.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
				Criteria.where("address.line")
						.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
				Criteria.where("address.zipCode")
						.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
			    Criteria.where("usertype")
		                .regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
				Criteria.where("phone")
						.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
		Criteria.where("userId").regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE));
		Criteria.where("role").regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE));
		criterias.add(searchCriteria);
		if (!CollectionUtils.isEmpty(criterias)) {
			Criteria criteria = new Criteria();
			criteria.andOperator(criterias.stream().toArray(Criteria[]::new));
			query.addCriteria(criteria);
		}
		return query;
		}
	public ResponseEntity<?> getUserById(String userId) {
		// TODO Auto-generated method stub
		LOGGER.info("userrecord getuserbyid successfully in implimentation");
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(userId));
		User user = this.mongoTemplate.findOne(query, User.class);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new User(), HttpStatus.OK);
		}
	}
	public ResponseEntity<?> updateUser(UpdateUsersRequest request) {
		// TODO Auto-generated method stub
		LOGGER.info("userrecord update successfully in implimentation");
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(request.getUserId()));
		User user = this.mongoTemplate.findOne(query, User.class);
		if (user != null) {
			if (request.getFirstName() != null) {
				user.setFirstName(request.getFirstName());
			}
			if (request.getLastName() != null) {
				user.setLastName(request.getLastName());
			}
			if (request.getEmail() != null) {
				user.setEmail(request.getEmail());
			}
			if (request.getPhone() != null) {
				user.setPhone(request.getPhone());
			}
			if (request. getUsertype() != null) {
				user.setUsertype(request. getUsertype());
			}
			
			if (request.getAddress() != null) {
				user.setAddress(request.getAddress());
			}
			if (request.getRole() != null) {
				user.setRole(request.getRole());
			}

			this.mongoTemplate.save(user);
			return new ResponseEntity<>("User " + user.getUserId() + " is successfully updated", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No User found with Id- " + request.getUserId(), HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<?> deleteUser(String userId) {
		// TODO Auto-generated method stub
		LOGGER.info("userrecord delete successfully in implimentation");
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(userId));
		User user = this.mongoTemplate.findOne(query, User.class);
		if (user != null) {
			user.setStatus("INACTIVE");
			this.mongoTemplate.save(user);
			return new ResponseEntity<>("User " + userId + " is successfully deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No User found with Id-" + userId, HttpStatus.NOT_FOUND);
		}
	}
}
