package com.ustglobal.RestAssuredLearn;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.HashMap;

import io.restassured.RestAssured;

public class SampleTest1 {
	
	public static void main(String[] args) {
		
		//path parameter
		given().when().get("http://localhost:8080/getEmployee/Tharun0").then().log().body();
		System.out.println("-----------------------------------------");
		
		//using base url
		RestAssured.baseURI="http://localhost:8080/";
		given().when().get("getEmployee/Tharun0").then().log().body();
		System.out.println("-----------------------------------------");
		
		//query parameter
		RestAssured.baseURI="http://localhost:8080/";
		given().queryParam("EmployeeName", "Tharun").when().get("getEmployee").then().log().body();
		System.out.println("-----------------------------------------");
		
		//post method
		RestAssured.baseURI="http://localhost:8080/";
		given().header("Content-Type","application/json").body("{\r\n"
				+ "    \"eName\": \"Tharun\",\r\n"
				+ "    \"eSal\": \"50000\"\r\n"
				+ "}").when().post("addEmployee").then().log().all()
		.assertThat().statusCode(201)
		.header("Unique", containsString("Tharun"))
		.body("msg", equalTo("Success: Employee is Added"));
		
		
		//hash map
		RestAssured.baseURI="http://localhost:8080/";
		HashMap<String, String> data= new HashMap<String, String>();
		data.put("eName", "Tharuntt");
		data.put("eSal", "50000");
		
		given()
		      .contentType("application/json")
		      .body(data)
		.when()
		      .post("addEmployee")
		.then()
		      .log().body()
		      .assertThat().statusCode(201)
		      .body("msg", equalTo("Success: Employee is Added"));	
		
	}

}
