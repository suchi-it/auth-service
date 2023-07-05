package com.login.model;

public class CreateUsersRequest {
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String usertype;
	private Address address;
	private String password;
	public CreateUsersRequest(String firstName, String lastName, String email, String phone, Address address,
			String usertype, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.usertype = usertype;
		this.address = address;
		this.password = password;
	}
	
   public CreateUsersRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
