package com.sctrcd.buspassws.model;

import com.sctrcd.buspassws.enumeration.UserType;

public class User {

	private String username;
	private String password;
	private String name;
	private String surname;
	private UserType type;
	private String dateOfRegistration;
	private BuyerProfile buyerProfile = null;

	public User() {
		super();
	}

	public User(String username, String password, String name, String surname, UserType type, String dateOfRegistration) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.type = type;
		this.dateOfRegistration = dateOfRegistration;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public String getDateOfRegistration() {
		return dateOfRegistration;
	}

	public void setDateOfRegistration(String dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}

	public BuyerProfile getBuyerProfile() {
		return buyerProfile;
	}

	public void setBuyerProfile(BuyerProfile buyerProfile) {
		this.buyerProfile = buyerProfile;
	}
	
	
}
