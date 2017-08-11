package com.example.demo.domain.ports.primary;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.demo.domain.model.CustomerResult;
import com.example.demo.domain.model.Customer;
import com.example.demo.domain.ports.secondary.CustomerRepository;

@RunWith(MockitoJUnitRunner.class)
public class CreateCustomerTest {
	@Mock
	private CustomerRepository customerRepository;
	@InjectMocks
	private CreateCustomerUseCase createCustomer;

	@Test
	public void createCustomerSucessfully() 
	{
		CreateCustomerRequest request = new CreateCustomerRequest("email@prueba.com", "password");
		CreateCustomerResponse response = new CreateCustomerResponse();
		when(customerRepository.findByEmail("email@prueba.com")).thenReturn(Optional.empty());
		
		createCustomer.execute(request, response);
		
		verify(customerRepository).save(any(Customer.class));
		Assert.assertThat(CustomerResult.OK, Matchers.is(response.getResult()));
		
	}
	
	@Test
	public void createCustomerFailBecauseExists()
	{
		Customer customer = Mockito.mock(Customer.class);
		CreateCustomerRequest request = new CreateCustomerRequest("email@prueba.com", "password");
		CreateCustomerResponse response = new CreateCustomerResponse();
		when(customerRepository.findByEmail("email@prueba.com")).thenReturn(Optional.of(customer));
		
		createCustomer.execute(request, response);
		
		verify(customerRepository,never()).save(customer);
		Assert.assertThat(CustomerResult.CUSTOMER_EXISTS, Matchers.is(response.getResult()));
	}
}
