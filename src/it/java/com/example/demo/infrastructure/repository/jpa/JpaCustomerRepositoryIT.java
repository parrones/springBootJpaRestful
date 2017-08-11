package com.example.demo.infrastructure.repository.jpa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
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
	private static final long CUSTOMER_ID_2 = 2l;
	private static final long CUSTOMER_ID_NOT_EXISTING = 999l;
	private static final long CUSTOMER_ID_1 = 1l;
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
	
	@Test
	public void findAllCustomers()
	{
		List<Customer> customers = customerRepository.findAllCustomers();
		
		assertTrue(customers.size() > 0);
	}
	
	@Test
	public void findCustomerByIdSuccessfully()
	{
		Optional<Customer> optCustomer = customerRepository.findById(CUSTOMER_ID_1);
		
		assertTrue(optCustomer.isPresent());
	}
	
	@Test
	public void findCustomerByIdEmptyBecauseDoesntExist()
	{
		Optional<Customer> optCustomer = customerRepository.findById(CUSTOMER_ID_NOT_EXISTING);
		
		assertFalse(optCustomer.isPresent());
	}
	
	@Test
	public void saveCustomerProfileSuccessfully()
	{
		com.example.demo.domain.model.Customer customer = new com.example.demo.domain.model.Customer(new Builder().setEmail("prueba@domain.com").setPassword("password").setAddress("address"));
		
		Customer savedCustomer = customerRepository.save(customer);
		
		assertThat(savedCustomer.getAddress(), Matchers.is("address"));
	}
	
	@Test
	public void deleteCustomer()
	{	
		assertTrue(customerRepository.findById(CUSTOMER_ID_2).isPresent());
		
		customerRepository.delete(CUSTOMER_ID_2);
		
		assertFalse(customerRepository.findById(CUSTOMER_ID_2).isPresent());
	}
}