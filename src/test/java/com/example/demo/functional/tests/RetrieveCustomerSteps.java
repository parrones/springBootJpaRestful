package com.example.demo.functional.tests;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@CucumberStepsDefinition
public class RetrieveCustomerSteps 
{
	private long id;
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private TestResult testResult;
	
	@Given("^the email \"([^\"]*)\" and the id \"([^\"]*)\" of a customer$")
	public void the_email_and_the_id_of_a_customer(String email, long customerId) throws Throwable 
	{
		this.id = customerId;
	}

	@When("^there is a request to retrieve customer resource$")
	public void there_is_a_request_to_retrieve_customer_resource() throws Throwable 
	{
	    ResponseEntity<CustomerResponse> response = restTemplate.exchange("/customers/".concat(String.valueOf(id)), HttpMethod.GET, null, CustomerResponse.class);
	    testResult.setResponse(response);
	}
	
	@Then("^the email returned is \"([^\"]*)\"$")
	public void the_email_returned_is(String email) throws Throwable 
	{
		if(testResult.getResponse().getStatusCode().equals(HttpStatus.OK))
		{
			CustomerResponse body = (CustomerResponse) testResult.getResponse().getBody();
			Assert.assertThat(body.email, Matchers.is(email));
		}
	}
	
	public static class CustomerResponse
	{
		public String email;
	}
}
