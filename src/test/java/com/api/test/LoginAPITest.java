package com.api.test;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
 
	
	@Test
	public void loginAPITest() throws IOException{
		
		
		UserCredentials userCredtials = new UserCredentials("iamfd","password");
		
		System.out.println("------------> "+System.getProperty("env"));
		
		given()
			.baseUri(ConfigManager.getProperty("BASE_URI"))
			.and()
			.contentType(ContentType.JSON)
			.and()
			.accept(ContentType.ANY)
			.and()
			.body(userCredtials)
			.log().uri()
			.log().method()
			.log().headers()
			.log().body()
			
			
		.when()
			.post("login")
			
		.then()
			.log().all()
			.statusCode(200)
			.time(Matchers.lessThan(2000L))
			.body("message",Matchers.equalTo("Success"))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
		
	
		
	}
	
	
	
}
