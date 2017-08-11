package com.example.demo.infrastructure.entry.rest;

public class CustomerResponse 
{
	private long customerId;
	private String name;
	private String surname;
	private String email;
	private String phone;
	private String address;
	
	public CustomerResponse(long customerId, String name, String surname, String email, String phone, String address) {
		this.customerId = customerId;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public long getCustomerId() {
		return customerId;
	}
}
