package com.example.demo.functional.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"json:target/failsafe-reports/IntegrationTests.json"})
public class IntegrationTests 
{
	
}
