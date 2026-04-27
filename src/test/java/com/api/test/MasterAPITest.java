package com.api.test;
// Created By Umesh
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class MasterAPITest {
	
	
	@Test
	public void masterAPITest() throws IOException {
		
		given()
			.spec(SpecUtil.requestSpecificationWithAuth(Role.FD))
		
		.when()
			.post("master")
			
		.then()
			.spec(SpecUtil.responseSpec_OK())
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
			.spec(SpecUtil.requestSpec())
	
	.when()
		.post("master")
		
	.then()
		.spec(SpecUtil.responseSpec(401));
	}

}
