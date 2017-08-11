package com.example.demo.domain.ports.primary;

import com.example.demo.domain.model.CustomerResult;

public class CreateCustomerResponse 
{
	private CustomerResult result;
	private long customerId;
	
	public void success(long id)
	{
		this.result = CustomerResult.OK;
		this.customerId = id;
	}
	
	public void customerExists()
	{
		this.result = CustomerResult.CUSTOMER_EXISTS;
	}
	
	public boolean isSuccess()
	{
		return this.result == CustomerResult.OK;
	}
	
	public boolean isCustomerExists()
	{
		return this.result == CustomerResult.CUSTOMER_EXISTS;
	}

	public long getCustomerId() {
		return customerId;
	}

	public CustomerResult getResult() 
	{
		return result;
	}
}
