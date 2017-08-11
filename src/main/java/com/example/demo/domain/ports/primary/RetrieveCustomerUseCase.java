package com.example.demo.domain.ports.primary;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.model.Customer;
import com.example.demo.domain.ports.secondary.CustomerRepository;

@Component
public class RetrieveCustomerUseCase 
{
	@Autowired
	private CustomerRepository customerRepository;
	
	public Optional<Customer> execute(long customerId) 
	{
		return customerRepository.findById(customerId);
	}
}
