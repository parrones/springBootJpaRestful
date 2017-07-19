package com.example.demo.infrastructure.repository.jpa;

import org.springframework.data.repository.CrudRepository;

public interface SpringCrudCustomerRepository extends CrudRepository<Customer, Long>
{
	Customer findByEmail(String email);
}
