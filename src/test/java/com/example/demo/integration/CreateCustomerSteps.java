package com.example.demo.integration;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.domain.ports.primary.CreateCustomerRequest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@CucumberStepsDefinition
public class CreateCustomerSteps 
{
	private TestRestTemplate restTemplate;
	private String email;
	private String password;
	private HttpStatus statusCode;
//	@Autowired
//	private TestResult testResult;
	
	@Given("^an \"([^\"]*)\" and a \"([^\"]*)\"$")
	public void an_and_a(String email, String password) throws Throwable 
	{
	    this.email = email;
	    this.password = password;
	    this.restTemplate = new TestRestTemplate();
	}

	@When("^there is a request to signup resource$")
	public void there_is_a_request_to_signup_resource() throws Throwable 
	{
		CreateCustomerRequest request = new CreateCustomerRequest(this.email, this.password);
	    ResponseEntity<String> response = restTemplate.exchange(IntegrationTestConstants.CUSTOMERS_URI, HttpMethod.POST, new HttpEntity<>(request), String.class);
//	    testResult.setResponse(response);
	    this.statusCode = response.getStatusCode();
	}
	
	@Then("^the response is \"([^\"]*)\"$")
	public void the_response_is(String result) throws Throwable 
	{
	   Assert.assertThat(statusCode, Matchers.is(HttpStatus.valueOf(result)));
	}
}
