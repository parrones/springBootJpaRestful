package com.example.demo.infrastructure.entry.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.model.Customer;
import com.example.demo.domain.model.Customer.Builder;
import com.example.demo.domain.model.CustomerResult;
import com.example.demo.domain.ports.primary.CreateCustomerUseCase;
import com.example.demo.domain.ports.primary.DeleteCustomerUseCase;
import com.example.demo.domain.ports.primary.RetrieveAllCustomersUseCase;
import com.example.demo.domain.ports.primary.RetrieveCustomerUseCase;
import com.example.demo.domain.ports.primary.SaveCustomerProfileUseCase;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/customers")
@Api(value = "customers")
public class CustomerController {
	
	@Autowired
	private CreateCustomerUseCase createCustomerUseCase;
	@Autowired
	private RetrieveAllCustomersUseCase retrieveAllCustomersUseCase;
	@Autowired
	private RetrieveCustomerUseCase retrieveCustomerUseCase;
	@Autowired
	private SaveCustomerProfileUseCase saveCustomerProfileUseCase;
	@Autowired
	private DeleteCustomerUseCase deleteCustomerUseCase;
	
	@ApiOperation(value = "Create a new customer")
	@ApiResponses(value = { @ApiResponse(code = 409, message = "The customer already exists"),
		      @ApiResponse(code = 200, message = "Created"),
		      @ApiResponse(code = 500, message = "Internal server error")})
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CreateCustomerResponse> createCustomer(@RequestBody CreateCustomerRequest restRequest) {
		com.example.demo.domain.ports.primary.CreateCustomerRequest request = new com.example.demo.domain.ports.primary.CreateCustomerRequest(
				restRequest.getEmail(), restRequest.getPassword());
		com.example.demo.domain.ports.primary.CreateCustomerResponse response = new com.example.demo.domain.ports.primary.CreateCustomerResponse();
		
		createCustomerUseCase.execute(request, response);
		if(response.isSuccess())
		{
			return new ResponseEntity<CreateCustomerResponse>(new CreateCustomerResponse(response.getCustomerId()), HttpStatus.OK);
		}
		if(response.isCustomerExists())
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CustomerResponse>> retrieveAllCustomers()
	{
		List<Customer> customers = retrieveAllCustomersUseCase.execute();
		
		return new ResponseEntity<List<CustomerResponse>>(adaptToCustomerResponseList(customers), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{customerId}",method = RequestMethod.GET)
	public ResponseEntity<Customer> retrieveCustomer(@PathVariable(name = "customerId", value = "customerId", required = true) long customerId)
	{
		Optional<Customer> optCustomer = retrieveCustomerUseCase.execute(customerId);
		if(optCustomer.isPresent())
		{
			return new ResponseEntity<Customer>(optCustomer.get(), HttpStatus.OK);
		}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@RequestMapping(value = "/{customerId}", method = RequestMethod.PUT)
	public ResponseEntity<CustomerResponse> saveCustomerProfile(@RequestBody SaveCustomerProfileRequest restRequest, @PathVariable(value = "customerId", required = true) long customerId)
	{
		Builder builder = new Builder().setAddress(restRequest.getAddress()).setCustomerId(customerId).setName(restRequest.getName()).setPhone(restRequest.getPhone()).setSurname(restRequest.getSurname());
		Customer customer = new Customer(builder);
		
		Optional<Customer> optCustomer = saveCustomerProfileUseCase.execute(customer);
		
		if(optCustomer.isPresent())
		{
			return new ResponseEntity<CustomerResponse>(toCustomerResponse(optCustomer.get()), HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@RequestMapping(value = "/{customerId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCustomer(@PathVariable(value = "customerId", required = true) long customerId)
	{
		CustomerResult result = deleteCustomerUseCase.execute(customerId);
		
		if(result == CustomerResult.OK)
		{
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	private List<CustomerResponse> adaptToCustomerResponseList(List<Customer> customers) {
		return customers.stream().map(c -> toCustomerResponse(c)).collect(Collectors.toList());

	}

	private CustomerResponse toCustomerResponse(Customer customer) {
		return new CustomerResponse(customer.getCustomerId(), customer.getName(), customer.getSurname(), customer.getEmail(), customer.getPhone(), customer.getAddress());
	}
}
