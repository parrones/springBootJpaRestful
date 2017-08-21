package com.example.demo.functional.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.example.demo.domain.ports.primary.CreateCustomerRequest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

@CucumberStepsDefinition
public class CreateCustomerSteps 
{
	@Autowired
	private TestRestTemplate restTemplate;
	private String email;
	private String password;
	@Autowired
	private TestResult testResult;
	
	@Given("^an \"([^\"]*)\" and a \"([^\"]*)\"$")
	public void an_and_a(String email, String password) throws Throwable 
	{
	    this.email = email;
	    this.password = password;
	}

	@When("^there is a request to signup resource$")
	public void there_is_a_request_to_signup_resource() throws Throwable 
	{
		CreateCustomerRequest request = new CreateCustomerRequest(this.email, this.password);
	    ResponseEntity<String> response = restTemplate.exchange("/customers", HttpMethod.POST, new HttpEntity<>(request), String.class);
	    testResult.setResponse(response);
	}
}
