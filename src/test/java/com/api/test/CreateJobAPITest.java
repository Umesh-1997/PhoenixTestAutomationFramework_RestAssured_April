package com.api.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;

public class CreateJobAPITest {
	
	@Test
	public void createJobAPITest() throws IOException {
		
		Customer customer = new Customer("Umesh", "Parab", "9639085172", "", "umesh.parabcr10@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("G-13", "River Park", "Girgaon", "Oberoi", "Mumbai", "400068", "India", "Maharashtra");
		CustomerProduct customerProduct = new CustomerProduct("2025-11-10T18:30:00.000Z", "12371379386425", "12371379386425", "12371379386425", "2025-11-10T18:30:00.000Z", 1, 1);
		Problems problems = new Problems(1,"Battery Issue");
		Problems[] problemsArray = new Problems[1];
		problemsArray[0]=problems;
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
		
		given()
			.spec(SpecUtil.requestSpecificationWithAuth(Role.FD, createJobPayload))
					
		
		.when()
			.post("/job/create")
			
		.then()
			.spec(SpecUtil.responseSpec_OK());
			
			
	}

}
