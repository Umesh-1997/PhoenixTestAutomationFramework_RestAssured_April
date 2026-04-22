package com.api.test;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
 
	
	@Test
	public void loginAPITest() throws IOException{
		
		
		UserCredentials userCredtials = new UserCredentials("iamfd","password");
		
		System.out.println("------------> "+System.getProperty("env"));
		
		given()
			.spec(SpecUtil.requestSpec(userCredtials))
			.and()
				
		.when()
			.post("login")
			
		.then()
			.spec(SpecUtil.responseSpec_OK())
			.body("message", Matchers.equalTo("Success"))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
		
	
		
	}
	
	
	
}
