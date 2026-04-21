package com.api.test;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {
	
	@Test
	public void createJobAPITest() throws IOException {
		
		Customer customer = new Customer("Umesh", "Parab", "9639085172", "", "umesh.parabcr10@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("G-13", "River Park", "Girgaon", "Oberoi", "Mumbai", "400068", "India", "Maharashtra");
		CustomerProduct customerProduct = new CustomerProduct("2025-11-10T18:30:00.000Z", "55663432386425", "55663432386425", "55663432386425", "2025-11-10T18:30:00.000Z", 1, 1);
		Problems problems = new Problems(1,"Battery Issue");
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		
		
		
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);
		
		given()
			.spec(SpecUtil.requestSpecificationWithAuth(Role.FD, createJobPayload))
					
		
		.when()
			.post("/job/create")
			
		.then()
			.spec(SpecUtil.responseSpec_OK())
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
			.body("message", Matchers.equalTo("Job created successfully. "))
			.body("data.job_number",Matchers.startsWith("JOB_"))
			.body("data.mst_service_location_id",Matchers.equalTo(1));
			
			
			
	}

}
