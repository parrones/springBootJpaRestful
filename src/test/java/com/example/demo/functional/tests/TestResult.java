package com.example.demo.functional.tests;

import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;



@Component
@Scope("cucumber-glue")
public class TestResult 
{
	private ResponseEntity response;

	public ResponseEntity getResponse() {
		return response;
	}

	public void setResponse(ResponseEntity response) {
		this.response = response;
	}
}
