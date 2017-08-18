package com.example.demo.infrastructure.repository.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SpringCrudCustomerRepository extends CrudRepository<Customers, Long>
{
	Customers findByEmail(String email);
	List<Customers> findAll();
}
