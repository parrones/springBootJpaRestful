package com.example.demo.domain.ports.primary;

import static org.mockito.Mockito.*;

import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.demo.domain.model.Customer;
import com.example.demo.domain.model.CustomerResult;
import com.example.demo.domain.ports.secondary.CustomerRepository;

@RunWith(MockitoJUnitRunner.class)
public class DeleteCustomerUseCaseTest 
{
	private static final long ANY_CUSTOMER_ID = 1l;
	@Mock
	private CustomerRepository customerRepository;
	@InjectMocks
	private DeleteCustomerUseCase useCase;
	
	@Test
	public void deleteCustomerSuccessfully()
	{
		Customer customer = Mockito.mock(Customer.class);
		when(customerRepository.findById(ANY_CUSTOMER_ID)).thenReturn(Optional.of(customer));
		
		CustomerResult result = useCase.execute(ANY_CUSTOMER_ID);
		
		verify(customerRepository).delete(ANY_CUSTOMER_ID);
		Assert.assertThat(result, Matchers.is(CustomerResult.OK));
	}
	
	@Test
	public void deleteCustomerNotFound()
	{
		when(customerRepository.findById(ANY_CUSTOMER_ID)).thenReturn(Optional.empty());
		
		CustomerResult result = useCase.execute(ANY_CUSTOMER_ID);
		
		verify(customerRepository, never()).delete(ANY_CUSTOMER_ID);
		Assert.assertThat(result, Matchers.is(CustomerResult.NOT_FOUND));
	}
}
