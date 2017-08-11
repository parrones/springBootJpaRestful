package com.example.demo.domain.ports.primary;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.model.Customer;
import com.example.demo.domain.model.CustomerResult;
import com.example.demo.domain.ports.secondary.CustomerRepository;

@Component
public class DeleteCustomerUseCase 
{
	@Autowired
	private CustomerRepository customerRepository;
	
	public CustomerResult execute(long customerId) 
	{
		Optional<Customer> optCustomer = customerRepository.findById(customerId);
		if(optCustomer.isPresent())
		{
			customerRepository.delete(customerId);
			return CustomerResult.OK;
		}
		return CustomerResult.NOT_FOUND;
	}
}
