package com.example.demo.infrastructure.repository.jpa;

import java.util.Optional;

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
	public long save(Customer customer) {
		com.example.demo.infrastructure.repository.jpa.Customer repoCustomer = crudRepository
				.findByEmail(customer.getEmail());
		if (repoCustomer == null) {
			repoCustomer = new com.example.demo.infrastructure.repository.jpa.Customer(customer.getName(),
					customer.getSurname(), customer.getEmail(), customer.getPassword(), customer.getPhone(),
					customer.getAddress());
			return crudRepository.save(repoCustomer).getId();
		}

		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Optional<Customer> findByEmail(String email) 
	{
		com.example.demo.infrastructure.repository.jpa.Customer repoCustomer = crudRepository.findByEmail(email);
		
		if(repoCustomer == null)
		{
			return Optional.empty();
		}
		Builder builder = new Builder().setAddress(repoCustomer.getAddress()).setEmail(repoCustomer.getEmail()).setName(repoCustomer.getName()).setPassword(repoCustomer.getPassword()).setPhone(repoCustomer.getPhone()).setSurname(repoCustomer.getSurname());
		// TODO Auto-generated method stub
		return Optional.of(new Customer(builder));
	}
}
