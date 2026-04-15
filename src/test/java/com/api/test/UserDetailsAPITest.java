package com.api.test;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {

	@Test
	public void userDetailsAPITest() throws IOException {
		
		
		Header authHeader = new Header("Authorization",AuthTokenProvider.getToken(Role.FD));
		given()
			.baseUri(ConfigManager.getProperty("BASE_URI"))
			.and()
			.contentType(ContentType.JSON)
			.and()
			.accept(ContentType.ANY)
			.and()
			.header(authHeader)
		
		.when()
			.get("userdetails")
			
		.then()
			.log().all()
			.statusCode(200)
			.time(Matchers.lessThan(2000L))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
		
			
		
	}
}
