package com.example.demo.domain.ports.secondary;

import java.util.Optional;

import com.example.demo.domain.model.Customer;

public interface CustomerRepository 
{
	long save(Customer customer);
	Optional<Customer> findByEmail(String email);
}
