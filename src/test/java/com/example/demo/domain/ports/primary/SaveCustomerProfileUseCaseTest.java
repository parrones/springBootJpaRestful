package com.example.demo.domain.ports.primary;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.demo.domain.model.Customer;
import com.example.demo.domain.model.Customer.Builder;
import com.example.demo.domain.ports.secondary.CustomerRepository;

@RunWith(MockitoJUnitRunner.class)
public class SaveCustomerProfileUseCaseTest 
{
	@Mock
	private CustomerRepository customerRepository;
	@InjectMocks
	private SaveCustomerProfileUseCase useCase;
	
	@Test
	public void saveCustomerProfileSuccessfully()
	{
		Builder builderToSave = new Builder().setCustomerId(1l).setAddress("address").setName("name").setPhone("phone").setSurname("surname").setEmail("email@domain.com").setPassword("password");
		Customer customerToSave = new Customer(builderToSave);
		Builder builder = new Builder().setEmail("email@domain.com");
		Customer customer = new Customer(builder);
		when(customerRepository.findById(1l)).thenReturn(Optional.of(customer));
		when(customerRepository.save(customerToSave)).thenReturn(customerToSave);
		
		Optional<Customer> optCustomer = useCase.execute(customerToSave);
		
		verify(customerRepository).save(customerToSave);
		assertTrue(optCustomer.isPresent());
	}
	
	@Test
	public void saveCustomerProfileNotFound()
	{
		Builder builderToSave = new Builder().setCustomerId(1l).setAddress("address").setName("name").setPhone("phone").setSurname("surname").setEmail("email@domain.com").setPassword("password");
		Customer customerToSave = new Customer(builderToSave);
		when(customerRepository.findById(1l)).thenReturn(Optional.empty());
		
		Optional<Customer> optCustomer = useCase.execute(customerToSave);
		
		verify(customerRepository,never()).save(any(Customer.class));
		assertFalse(optCustomer.isPresent());
	}
}
