package com.example.demo.domain.model;

public class Customer 
{
	private String name;
	private String surname;
	private String email;
	private String password;
	private String phone;
	private String address;
	
	public Customer(Builder builder) 
	{
		this.name = builder.name;
		this.surname = builder.surname;
		this.email = builder.email;
		this.password = builder.password;
		this.phone = builder.phone;
		this.address = builder.address;
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

	public String getPassword() {
		return password;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public static class Builder
	{
		private String name;
		private String surname;
		private String email;
		private String password;
		private String phone;
		private String address;
		
		public Builder setName(String name) 
		{
			this.name = name;
			return this;
		}
		public Builder setSurname(String surname) {
			this.surname = surname;
			return this;
		}
		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}
		public Builder setPassword(String password) {
			this.password = password;
			return this;
		}
		public Builder setPhone(String phone) {
			this.phone = phone;
			return this;
		}
		public Builder setAddress(String address) {
			this.address = address;
			return this;
		}
	}	
}
