package com.ustglobal.RestAssuredLearn;

import static io.restassured.RestAssured.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class ReadFromFileTest {

	public static String response;
	public static String vId;
	public static String vMsg;

	public static void main(String[] args) throws InterruptedException, IOException {

		RestAssured.baseURI = "http://localhost:8080/";

		createEmployee();
		Thread.sleep(5000);

	}

	public static void createEmployee() throws IOException 
	{
		String filepath= "C:/Users/Tharun06/git/API_Auto_RestAssured/RestAssuredLearn/AddEmpPayload.json";
		response= given().header("Content-Type", "application/json").body(new String (Files.readAllBytes(Paths.get(filepath))))
				  .when().post("addEmployee")
				  .then().assertThat().statusCode(201)
				  .extract().response().asString();

		System.out.println(response);
		JsonPath jpath= new JsonPath(response);
		vId= jpath.getString("eid");
		vMsg= jpath.getString("msg");

		System.out.println("Employee id: "+vId);
		System.out.println("Message: "+vMsg);
		
		System.out.println("Add Employee execution is completed...");
		System.out.println("----------------------------------");
	}
}
