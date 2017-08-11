package com.example.demo.domain.ports.primary;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.model.Customer;
import com.example.demo.domain.ports.secondary.CustomerRepository;

@Component
public class SaveCustomerProfileUseCase 
{
	@Autowired
	private CustomerRepository customerRepository;
	
	public Optional<Customer> execute(Customer customer) 
	{
		Optional<Customer> optCustomer = customerRepository.findById(customer.getCustomerId());
		if(optCustomer.isPresent())
		{
			customer.setEmail(optCustomer.get().getEmail());
			return Optional.of(customerRepository.save(customer));
		}
		return Optional.empty();
	}
}
