package com.example.demo.functional.tests;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import cucumber.api.java.en.Then;

@CucumberStepsDefinition
public class CommonSteps 
{
	@Autowired
	private TestResult testResult;
	
	@Then("^the response is \"([^\"]*)\"$")
	public void the_response_is(String result) throws Throwable 
	{
	   Assert.assertThat(testResult.getResponse().getStatusCode(), Matchers.is(HttpStatus.valueOf(result)));
	}
}
