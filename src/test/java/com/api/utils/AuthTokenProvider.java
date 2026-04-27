  package com.api.utils;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.hamcrest.Matchers;

import com.api.constant.Role;
import com.api.request.model.UserCredentials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {
	
	private AuthTokenProvider()
	{
		
	}

	// I want to make the request for login api and we want to extract the token and print it on the console
	public static String getToken(Role role) throws IOException  {
		
	//	UserCredentials userCredetial = new UserCredentials("iamfd","password");
		UserCredentials userCredentials=null;
		if(role == Role.FD) {
			userCredentials= new UserCredentials("iamfd", "password");
		}
		else if(role == Role.SUP) {
			userCredentials= new UserCredentials("iamsup", "password");
		}
		else if(role == Role.ENG){
			userCredentials= new UserCredentials("iameng", "password");
		}
		else if(role == Role.QC){
			userCredentials= new UserCredentials("iamqc", "password");
		}
		
		
		
		String token= given()
			.baseUri(ConfigManager.getProperty("BASE_URI"))
			.contentType(ContentType.JSON)
			.accept(ContentType.ANY)
			.body(userCredentials)
		
		.when()
			.post("login")
			
		.then()
			.log().ifValidationFails()
			.statusCode(200)
			.body("message",Matchers.equalTo("Success"))
			.extract()
			.body()
			.jsonPath()
			.getString("data.token");
		
		
		return token;

	}

}
