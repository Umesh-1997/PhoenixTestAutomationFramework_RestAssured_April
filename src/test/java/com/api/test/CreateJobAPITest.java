package com.api.test;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.DateTimeUtil;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {
	
	@Test
	public void createJobAPITest() throws IOException {
		
		System.out.println(Instant.now().minus(10,ChronoUnit.DAYS));
		System.out.println("###############################");
		
		Customer customer = new Customer("Umesh", "Parab", "9639085172", "", "umesh.parabcr10@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("G-13", "River Park", "Girgaon", "Oberoi", "Mumbai", "400068", "India", "Maharashtra");
		CustomerProduct customerProduct = new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10),"55663432383575", "55663432383575", "55663432383575",DateTimeUtil.getTimeWithDaysAgo(10),1,1);
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
