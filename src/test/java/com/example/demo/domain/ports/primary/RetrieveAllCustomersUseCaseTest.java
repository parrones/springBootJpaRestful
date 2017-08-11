package com.example.demo.domain.ports.primary;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.demo.domain.model.Customer;
import com.example.demo.domain.ports.secondary.CustomerRepository;

@RunWith(MockitoJUnitRunner.class)
public class RetrieveAllCustomersUseCaseTest 
{
	@Mock
	private CustomerRepository customerRepository;
	@InjectMocks
	private RetrieveAllCustomersUseCase useCase;
	
	@Test
	public void retrieveAllCustomersSuccessfully()
	{
		Customer customer1 = Mockito.mock(Customer.class);
		Customer customer2 = Mockito.mock(Customer.class);
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(customer1);
		customers.add(customer2);
		when(customerRepository.findAllCustomers()).thenReturn(customers);
		
		List<Customer> allCustomers = useCase.execute();
		
		assertTrue(allCustomers.size() > 0);
	}
	
	@Test
	public void retrieveAllCustomersEmptyList()
	{
		when(customerRepository.findAllCustomers()).thenReturn(new ArrayList<Customer>());
		
		List<Customer> allCustomers = useCase.execute();
		
		assertTrue(allCustomers.isEmpty());
	}
}
