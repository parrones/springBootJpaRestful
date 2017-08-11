package com.example.demo.infrastructure.entry.rest;

public class CreateCustomerRequest 
{
	private String email;
	private String password;
	
	public CreateCustomerRequest() 
	{
//		Default constructor
	}

	public CreateCustomerRequest(String email, String password) 
	{
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
