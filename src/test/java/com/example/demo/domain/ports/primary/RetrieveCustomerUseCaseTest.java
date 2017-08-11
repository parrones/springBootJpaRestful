package com.example.demo.domain.ports.primary;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.demo.domain.model.Customer;
import com.example.demo.domain.ports.secondary.CustomerRepository;

@RunWith(MockitoJUnitRunner.class)
public class RetrieveCustomerUseCaseTest 
{
	@Mock
	private CustomerRepository customerRepository;
	@InjectMocks
	private RetrieveCustomerUseCase useCase;
	
	@Test
	public void retrieveCustomerSuccessfully()
	{
		Customer customer = Mockito.mock(Customer.class);
		when(customerRepository.findById(1l)).thenReturn(Optional.of(customer));
		
		Optional<Customer> optCustomer = useCase.execute(1l);
		
		assertTrue(optCustomer.isPresent());
	}
	
	@Test
	public void retrieveCustomerEmptyBecauseDoesntExist()
	{
		when(customerRepository.findById(1l)).thenReturn(Optional.empty());
		
		Optional<Customer> optCustomer = useCase.execute(1l);
		
		assertFalse(optCustomer.isPresent());
	}
}
