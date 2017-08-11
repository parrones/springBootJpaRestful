package com.example.demo.domain.ports.primary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.model.Customer;
import com.example.demo.domain.ports.secondary.CustomerRepository;

@Component
public class RetrieveAllCustomersUseCase 
{
	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> execute() 
	{
		return customerRepository.findAllCustomers();
	}
}
