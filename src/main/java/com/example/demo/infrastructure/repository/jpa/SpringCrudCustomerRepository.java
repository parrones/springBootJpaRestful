package com.example.demo.infrastructure.repository.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SpringCrudCustomerRepository extends CrudRepository<Customer, Long>
{
	Customer findByEmail(String email);
	List<Customer> findAll();
}
