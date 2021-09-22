package com.ustglobal.RestAssuredLearn;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import io.restassured.RestAssured;

public class SampleTest2 {
	
	public static void main(String[] args) {
		
		RestAssured.baseURI="http://localhost:8080/";
		given().header("Content-Type","application/json").body(addEmployeeBody()).when().post("addEmployee").then().log().all()
		.header("Unique", containsString("Tharun1"))
		.body("msg", equalTo("Success: Employee is Added"));

	}

	
	public static String addEmployeeBody()
	{
		return "{\r\n"
				+ "    \"eName\": \"Tharun\",\r\n"
				+ "    \"eSal\": \"50000\"\r\n"
				+ "}";
	}
}
