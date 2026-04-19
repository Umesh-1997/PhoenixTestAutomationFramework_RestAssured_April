package com.api.test;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.ResponseSpecification;

public class UserDetailsAPITest {

	@Test
	public void userDetailsAPITest() throws IOException {
		
		
		given()
		.spec(SpecUtil.requestSpecificationWithAuth(Role.FD))
		
		.when()
			.get("userdetails")
			
		.then()
			.spec(SpecUtil.responseSpec_OK())
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
		
			
		
	}
}
