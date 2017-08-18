package com.example.demo.infrastructure.repository.jpa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.model.Customer;
import com.example.demo.domain.model.Customer.Builder;
import com.example.demo.domain.ports.secondary.CustomerRepository;

@Component
@Transactional
public class JpaCustomerRepository implements CustomerRepository {
	@Autowired
	private SpringCrudCustomerRepository crudRepository;

	@Override
	public Customer save(Customer customer) {
		com.example.demo.infrastructure.repository.jpa.Customers repoCustomer = crudRepository
				.findByEmail(customer.getEmail());
		if (repoCustomer == null) {
			repoCustomer = new com.example.demo.infrastructure.repository.jpa.Customers(customer.getName(),
					customer.getSurname(), customer.getEmail(), customer.getPassword(), customer.getPhone(),
					customer.getAddress());
			return toModelCustomer(crudRepository.save(repoCustomer));
		}
		else
		{
			repoCustomer.setAddress(customer.getAddress());
			repoCustomer.setName(customer.getName());
			repoCustomer.setPhone(customer.getPhone());
			repoCustomer.setSurname(customer.getSurname());
			return toModelCustomer(repoCustomer);
		}
	}

	@Override
	public Optional<Customer> findByEmail(String email) {
		com.example.demo.infrastructure.repository.jpa.Customers repoCustomer = crudRepository.findByEmail(email);

		if (repoCustomer == null) {
			return Optional.empty();
		}
		Builder builder = new Builder().setAddress(repoCustomer.getAddress()).setEmail(repoCustomer.getEmail())
				.setName(repoCustomer.getName()).setPassword(repoCustomer.getPassword())
				.setPhone(repoCustomer.getPhone()).setSurname(repoCustomer.getSurname());
		return Optional.of(new Customer(builder));
	}

	@Override
	public List<Customer> findAllCustomers() 
	{
		List<com.example.demo.infrastructure.repository.jpa.Customers> repoCustomers = crudRepository.findAll();
		return repoCustomers.stream().map(c -> toModelCustomer(c)).collect(Collectors.toList());
	}

	@Override
	public Optional<Customer> findById(long id) 
	{
		com.example.demo.infrastructure.repository.jpa.Customers repoCustomer = crudRepository.findOne(id);
		if(repoCustomer == null)
		{
			return Optional.empty();
		}
		return Optional.of(toModelCustomer(repoCustomer));
	}
	
	private Customer toModelCustomer(com.example.demo.infrastructure.repository.jpa.Customers repoCustomer) {
		Builder builder = new Builder().setCustomerId(repoCustomer.getId()).setAddress(repoCustomer.getAddress()).setEmail(repoCustomer.getEmail())
				.setName(repoCustomer.getName()).setPassword(repoCustomer.getPassword())
				.setPhone(repoCustomer.getPhone()).setSurname(repoCustomer.getSurname());
		return new Customer(builder);
	}

	@Override
	public void delete(long customerId) 
	{
		com.example.demo.infrastructure.repository.jpa.Customers repoCustomer = crudRepository.findOne(customerId);
		if(repoCustomer != null)
		{
			crudRepository.delete(repoCustomer);
		}
		
	}
}
