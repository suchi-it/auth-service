package com.login.model;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.login.constant.CollectionConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = CollectionConstants.USERS)
public class User {
	@Id
	private String userId;
	private String firstName;
	private String lastName;
	@Indexed(unique = true)
	@NotNull
	private String email;
	private String phone;
	private String usertype;
	private Address address;
	@CreatedDate
	private Date createdAt;
	@LastModifiedDate
	private Date lastModifiedDate;
	@JsonIgnore
	private String password;
	private String status;
	private String role;

}