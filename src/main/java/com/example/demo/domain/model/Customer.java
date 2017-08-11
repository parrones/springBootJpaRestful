package com.example.demo.domain.model;

public class Customer 
{
	private long customerId;
	private String name;
	private String surname;
	private String email;
	private String password;
	private String phone;
	private String address;
	
	public Customer(Builder builder) 
	{
		this.customerId = builder.customerId;
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

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}



	public static class Builder
	{
		private long customerId;
		private String name;
		private String surname;
		private String email;
		private String password;
		private String phone;
		private String address;
		
		public Builder setCustomerId(long customerId) {
			this.customerId = customerId;
			return this;
		}
		
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
