package com.example.demo.infrastructure.entry.rest;

public class CreateCustomerResponse 
{
	private long customerId;

	public CreateCustomerResponse(long customerId) 
	{
		this.customerId = customerId;
	}

	public long getCustomerId() {
		return customerId;
	}
}
