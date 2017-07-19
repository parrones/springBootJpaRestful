package com.example.demo.infrastructure.repository.jpa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.domain.model.Customer;
import com.example.demo.domain.model.Customer.Builder;
import com.example.demo.domain.ports.secondary.CustomerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JpaCustomerRepositoryIT 
{
	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	public void saveSuccessfully()
	{
		com.example.demo.domain.model.Customer customer = new com.example.demo.domain.model.Customer(new Builder().setEmail("email").setPassword("password"));
		
		assertFalse(customerRepository.findByEmail("email").isPresent());
		
		customerRepository.save(customer);
		
		assertTrue(customerRepository.findByEmail("email").isPresent());
	}
	
	@Test
	public void findByEmailSuccesfully()
	{
		Optional<Customer> optCustomer = customerRepository.findByEmail("prueba@domain.com");
		
		assertTrue(optCustomer.isPresent());
	}
	
	@Test
	public void findByEmailEmptyBecauseEmailDoesntExist()
	{
		Optional<Customer> optCustomer = customerRepository.findByEmail("any@domain.com");
		
		assertFalse(optCustomer.isPresent());
	}
}
