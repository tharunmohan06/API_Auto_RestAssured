package com.ustglobal.RestAssuredLearn;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class EMS_EndToEnd {

	public static String response;
	public static String vId;
	public static String vMsg;

	public static void main(String[] args) throws InterruptedException {

		RestAssured.baseURI = "http://localhost:8080/";

		createEmployee();
		Thread.sleep(5000);

		getEmployee();
		Thread.sleep(5000);

		updateEmployee();
		Thread.sleep(5000);

		deleteEmployee();
		Thread.sleep(5000);

	}

	public static void createEmployee() {
		response = given().header("Content-Type", "application/json").body(addEmployeePayload())
				.when().post("addEmployee")
				.then().assertThat().statusCode(201).extract().response().asString();

		JsonPath jpath = new JsonPath(response);
		vId = jpath.getString("eid");
		vMsg = jpath.getString("msg");

		System.out.println("Employee id: "+vId);
		System.out.println("Message: "+vMsg);
		
		System.out.println("\nAdd Employee execution is completed");
		System.out.println("---------------------------------------");
	}

	public static void getEmployee() {
		response = given().when().get("getEmployee/" + vId).then().log().all().extract().response().asString();
		JsonPath jpath = new JsonPath(response);
		Assert.assertEquals(vId, jpath.getString("eId"));
		
		System.out.println("\nGet Employee execution is completed");
		System.out.println("---------------------------------------");
	}

	public static void updateEmployee() {
		given().header("Content-Type", "application/json").body(updateEmployeePayload())
		.when().put("updateEmployee/" + vId)
		.then().log().body();
		
		System.out.println("\nUpdate Employee execution is completed");
		System.out.println("-----------------------------------------");
	}

	public static void deleteEmployee() {
		given().header("Content-Type", "application/json").body(deleteEmployeePayload())
		.when().delete("deleteEmployee")
		.then().log().body();
		
		System.out.println("\nDelete Employee execution is completed");
		System.out.println("-----------------------------------------");
	}
	

	public static String addEmployeePayload() {
		return "{\r\n" + "    \"eName\": \"SonuTr\",\r\n" + "    \"eSal\": \"50000\"\r\n" + "}";
	}

	public static String updateEmployeePayload() {
		return "{\r\n" + "    \"eName\": \"Tharun Mohan\",\r\n" + "    \"eSal\": \"60000\"\r\n" + "}";
	}

	public static String deleteEmployeePayload() {
		return "{\r\n" + "    \"eId\": \"" + vId + "\"\r\n" + "}";
	}

}
