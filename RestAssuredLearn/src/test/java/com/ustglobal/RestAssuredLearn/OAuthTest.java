package com.ustglobal.RestAssuredLearn;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;


public class OAuthTest {
	
	public static String accesTocken;
	
	public static void main(String[] args) {
		
		generateAccessToken();
		RestAssured.baseURI= "http://coop.apps.symfonycasts.com/api/2235/";
		
		feedYourChickens();
		unlockTheBarn();
		putTheToiletSeatDown();
		collectEggsFromYourChickens();
		getTheNumberOfEggsCollectedToday();			
	}
	
	public static void generateAccessToken()
	{
		Response resp= given()
				.formParam("client_id", "Tharun2")
				.formParam("client_secret", "476f078d11eff5d9643036e6a12d64af")
				.formParam("grant_type", "client_credentials")
				.when().post("http://coop.apps.symfonycasts.com/token");
		
		//System.out.println(resp.asString());
		accesTocken= resp.jsonPath().get("access_token");
	}
	
	public static void feedYourChickens()
	{
		Response resp2= given().auth().oauth2(accesTocken).when().post("chickens-feed");
		System.out.println("Status code: "+resp2.statusCode());
		System.out.println("Response body: "+resp2.getBody().asString());
		
		System.out.println("Execution is completed..\n");
	}
	
	public static void unlockTheBarn()
	{
		Response resp3= given().auth().oauth2(accesTocken).when().post("barn-unlock");
		System.out.println("Status code: "+resp3.statusCode());
		System.out.println("Response body: "+resp3.getBody().asString());
		
		System.out.println("Execution is completed..\n");
	}
	
	public static void putTheToiletSeatDown()
	{
		Response resp4= given().auth().oauth2(accesTocken).when().post("toiletseat-down");
		System.out.println("Status code: "+resp4.statusCode());
		System.out.println("Response body: "+resp4.getBody().asString());
		
		System.out.println("Execution is completed..\n");
	}
	
	public static void collectEggsFromYourChickens()
	{
		Response resp5= given().auth().oauth2(accesTocken).when().post("eggs-collect");
		System.out.println("Status code: "+resp5.statusCode());
		System.out.println("Response body: "+resp5.getBody().asString());
		
		System.out.println("Execution is completed..\n");
	}
	
	public static void getTheNumberOfEggsCollectedToday()
	{
		Response resp6= given().auth().oauth2(accesTocken).when().post("eggs-count");
		System.out.println("Status code: "+resp6.statusCode());
		System.out.println("Response body: "+resp6.getBody().asString());
		
		System.out.println("Execution is completed..\n");
	}
	
	
	
	
	
	
	
	
	
}
