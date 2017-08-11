package com.example.demo.infrastructure.entry.rest;

import java.util.List;

public class CustomersResponse 
{
	private List<CustomerResponse> customers;

	public CustomersResponse(List<CustomerResponse> customers) 
	{
		this.customers = customers;
	}

	public List<CustomerResponse> getCustomers() {
		return customers;
	}
}
