package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class MasterAPITest {
	
	
	@Test
	public void masterAPITest() throws IOException {
		
		given()
			.baseUri(ConfigManager.getProperty("BASE_URI"))
			.and()
			.contentType(ContentType.JSON)
			.and()
			.header("Authorization",AuthTokenProvider.getToken(Role.FD))
			.and()
			.accept(ContentType.ANY)
		
		.when()
			.post("master")
			
		.then()
			.statusCode(200)
			.log().all()
			.time(Matchers.lessThan(1000L))
			.body("message",Matchers.equalTo("Success"))
			.body("data",Matchers.notNullValue())
			.body("data",Matchers.hasKey("mst_oem"))
			.body("data",Matchers.hasKey("mst_model"))
			.body("$",Matchers.hasKey("message")) // $ indicates whole json
			.body("$",Matchers.hasKey("data")) // $ indicates whole json
			.body("data.mst_oem.size()",Matchers.greaterThanOrEqualTo(0))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
	}
	
	@Test
	public void invalidTokenMasterAPITest() throws IOException
	{
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		.contentType(ContentType.JSON)
		.and()
		.accept(ContentType.ANY)
	
	.when()
		.post("master")
		
	.then()
		.statusCode(401);
	}

}
