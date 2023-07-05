package com.login.model;

public class Address {
	private String lane;
	private String street;
	private String city;
	private String state;
	private String country;
	private String zipCode;

	public Address(String lane, String street, String city, String state, String country, String zipCode) {
		super();
		this.lane = lane;
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
	}

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getLane() {
		return lane;
	}

	public void setLane(String lane) {
		this.lane = lane;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
