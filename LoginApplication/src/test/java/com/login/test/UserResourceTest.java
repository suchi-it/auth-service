package com.login.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.login.model.Address;
import com.login.model.CreateUsersRequest;
import com.login.model.UpdateUsersRequest;
import com.login.model.User;
import com.login.resource.UserResource;
import com.login.service.impl.UserServiceImpl;
public class UserResourceTest {
	@Autowired
    MockMvc mockMvc;
	@Mock
	UserServiceImpl userServiceImpl;
	@InjectMocks
	UserResource userResource;
	public List<User> users;
    User user1;
    User user2;
    User user3;
    Address address;
    CreateUsersRequest request;
    UpdateUsersRequest uprequest;
    Date date;
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userResource).build();
        address = new Address("lane", "street", "city", "state", "country", "zipcode");
        date = new Date();
        request = new CreateUsersRequest("sai", "teja", "sai@gmail.com", "7386473912", address, "qqqqqq", "customer");
        uprequest = new UpdateUsersRequest("saitej29", "sai", "teja", "sai@gmail.com", "7386473912", address,"customer","admin");
      user1 = new User("chakri34", "chandra", "krishna", "chandra@gmail.com", "7386473913","employee", address, date, date, "ggggg",
                "approved", "admin");
       user2 = new User("krikan53", "krishna", "kanth", "kanth@gmail.com", "7386473911","customer", address, date, date, "hhhhh",
                "approved", "users");
        user3 = new User("bharam8", "bhanu", "ramya", "bhanu@gmail.com", "7386473914","employee", address, date, date, "yyyy",
                "approved", "users");
        users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
    }
}

 

 


