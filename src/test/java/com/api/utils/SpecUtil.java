package com.api.utils;

import java.io.IOException;

import org.hamcrest.Matchers;

import com.api.constant.Role;
import com.api.pojo.UserCredentials;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {

	
	// Can be used for GET and DEL request
	public static RequestSpecification requestSpec() throws IOException {
		
		RequestSpecification requestspecification = new RequestSpecBuilder()
		.setBaseUri(ConfigManager.getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.ANY)
		.log(LogDetail.URI)
		.log(LogDetail.METHOD)
		.log(LogDetail.HEADERS)
		.log(LogDetail.BODY)
		.build();
		return requestspecification;
	}
	
	// Method Overloading(Same name different parameters) - Can be used for POST,PUT and PATCH
	public static RequestSpecification requestSpec(Object userCreds) throws IOException {
		
		RequestSpecification requestspecification = new RequestSpecBuilder()
		.setBaseUri(ConfigManager.getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.ANY)
		.setBody(userCreds)
		.log(LogDetail.URI)
		.log(LogDetail.METHOD)
		.log(LogDetail.HEADERS)
		.log(LogDetail.BODY)
		.build();
		return requestspecification;
	}
	
	public static RequestSpecification requestSpecificationWithAuth(Role role) throws IOException {
		RequestSpecification requestspecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.ANY)
				.addHeader("Authorization",AuthTokenProvider.getToken(role))
				.log(LogDetail.URI)
				.log(LogDetail.METHOD)
				.log(LogDetail.HEADERS)
				.log(LogDetail.BODY)
				.build();
				return requestspecification;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static ResponseSpecification responseSpec_OK() {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
		.expectStatusCode(200)
		.expectContentType(ContentType.JSON)
		.expectResponseTime(Matchers.lessThan(1500L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
	}
	
	public static ResponseSpecification responseSpec(int statusCode) {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
		.expectStatusCode(statusCode)
		.expectContentType(ContentType.JSON)
		.expectResponseTime(Matchers.lessThan(1500L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
	}
	
	
	
	
	
	
}
