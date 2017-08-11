package com.example.demo.domain.ports.primary;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.model.Customer;
import com.example.demo.domain.model.Customer.Builder;
import com.example.demo.domain.ports.secondary.CustomerRepository;

@Component
public class CreateCustomerUseCase {

	@Autowired
	private CustomerRepository customerRepository;
	
	public void execute(CreateCustomerRequest request, CreateCustomerResponse response) 
	{
		Optional<Customer> optEmail = customerRepository.findByEmail(request.getEmail());
		if(optEmail.isPresent())
		{
			response.customerExists();
		}
		else
		{
			Builder builder = new Builder().setEmail(request.getEmail()).setPassword(request.getPassword());
			response.success(customerRepository.save(new Customer(builder)).getCustomerId());
		}
	}
}
