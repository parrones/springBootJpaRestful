package com.example.demo.domain.ports.secondary;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.model.Customer;

public interface CustomerRepository 
{
	Customer save(Customer customer);
	Optional<Customer> findByEmail(String email);
	List<Customer> findAllCustomers();
	Optional<Customer> findById(long id);
	void delete(long id);
}
