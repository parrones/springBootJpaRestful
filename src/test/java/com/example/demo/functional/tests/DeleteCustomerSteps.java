package com.example.demo.functional.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

@CucumberStepsDefinition
public class DeleteCustomerSteps 
{
	private long id;
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private TestResult testResult;
	
	@Given("^the id \"([^\"]*)\" of a customer$")
	public void the_id_of_a_customer(long customerId) throws Throwable 
	{
	    this.id = customerId;
	}

	@When("^there is a request to delete resource$")
	public void there_is_a_request_to_delete_resource() throws Throwable 
	{
		ResponseEntity<String> response = restTemplate.exchange("/customers".concat("/").concat(String.valueOf(id)), HttpMethod.DELETE, null, String.class);
		testResult.setResponse(response);
	}
}
