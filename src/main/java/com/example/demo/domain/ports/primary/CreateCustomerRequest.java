package com.example.demo.domain.ports.primary;

public class CreateCustomerRequest 
{
	private String email;
	private String password;
	
	public CreateCustomerRequest(String email, String password) 
	{
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}
