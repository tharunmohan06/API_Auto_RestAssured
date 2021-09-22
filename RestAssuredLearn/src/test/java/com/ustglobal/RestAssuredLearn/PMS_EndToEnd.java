package com.ustglobal.RestAssuredLearn;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class PMS_EndToEnd {
	
	public static String response;
	public static String pId;
	public static String pMsg;
	
	public static void main(String[] args) throws Exception {
		
		RestAssured.baseURI= "http://localhost:8080/";
		
		addPizza();
		Thread.sleep(2000);
		
		getPizzaById();
		Thread.sleep(2000);
		
		updatePizza();
		Thread.sleep(2000);
		
		deletePizza();
		Thread.sleep(2000);
	}

	
	public static void addPizza()
	{
		response=
		given().contentType("application/json").body(addPizzaPayload())
		.when().post("addpizza")
		.then().assertThat().statusCode(201).log().body().extract().response().asString();
		
		JsonPath jpath= new JsonPath(response);
		pId= jpath.getString("id");
		pMsg= jpath.getString("msg");
		
		System.out.println("\nPizza id: "+pId);
		System.out.println("Message: "+pMsg);
		
		System.out.println("\nAdd Pizza execution is completed");
		System.out.println("----------------------------------");
	}
	
	
	public static void getPizzaById()
	{
		response=
		given().when().get("getpizza/"+pId)
		.then().assertThat().statusCode(200).log().body().extract().response().asString();
		
		JsonPath jpath= new JsonPath(response);
		Assert.assertEquals(pId, jpath.getString("pId"));
		
		System.out.println("\nGet Pizza execution is completed");
		System.out.println("------------------------------------");
	}
	
	
	public static void updatePizza()
	{
		given().contentType("application/json").body(updatePizzaPayload())
		.when().put("updatepizza/"+pId)
		.then().assertThat().statusCode(200).log().body();
		
		System.out.println("\nUpdate Pizza execution is completed");
		System.out.println("------------------------------------");
	}
	
	
	public static void deletePizza() 
	{
		given().contentType("application/json").body(deletePizzaPayload())
		.when().delete("deletepizza")
		.then().assertThat().statusCode(201).log().body();
		
		System.out.println("\nDelete Pizza execution is completed");
		System.out.println("------------------------------------");
	}
	
	
	
	public static String addPizzaPayload()
	{
		return "{\r\n"
				+ "    \"pProduct\": \"VeggieSupreme\",\r\n"
				+ "    \"pPrice\": \"500\"\r\n"
				+ "}";
	}
	
	
	public static String updatePizzaPayload()
	{
		return "{\r\n"
				+ "    \"pProduct\": \"NonVeggieSupreme\",\r\n"
				+ "    \"pPrice\": \"750\"\r\n"
				+ "}";
	}
	
	
	public static String deletePizzaPayload()
	{
		return "{\r\n"
				+ "    \"pId\": \""+pId+"\"\r\n"
				+ "}";
	}

}
