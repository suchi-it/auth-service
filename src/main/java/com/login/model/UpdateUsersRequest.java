package com.login.model;

public class UpdateUsersRequest {
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String usertype;
	private Address address;
	private String role;
	public UpdateUsersRequest(String userId, String firstName, String lastName, String email, String phone,
			Address address, String usertype, String role) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.usertype = usertype;
		this.address = address;
		this.role = role;
	}
	public UpdateUsersRequest(String string, String string2, String string3, String string4, Address address2, String string5, String string6) {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
