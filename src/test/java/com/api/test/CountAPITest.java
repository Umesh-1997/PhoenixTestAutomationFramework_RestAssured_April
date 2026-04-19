package com.api.test;

import org.hamcrest.Matcher;
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

public class CountAPITest {
	
	@Test
	public void CountAPITest() throws IOException {
		
		given()
			.spec(SpecUtil.requestSpecificationWithAuth(Role.FD))
		
		.when()
			.get("dashboard/count")
			
		.then()
			.spec(SpecUtil.responseSpec_OK())
			.body("message",Matchers.equalTo("Success"))
			.body("data",Matchers.notNullValue())
			.body("data.size()",Matchers.equalTo(3)) // Size of Array should be 3
			.body("data.count",Matchers.everyItem(Matchers.greaterThanOrEqualTo(0))) // Value of count key should be more than 0
			.body("data.label",Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json"))
			.body("data.key",Matchers.containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"));
			
	}
	
	@Test
	public void countAPITest_MissingAuthToken() throws IOException {
		
		given()
			.spec(SpecUtil.requestSpec())
	
	.when()
		.get("dashboard/count")
		
	.then()
		.spec(SpecUtil.responseSpec(401));
		
		
	}

}
