package com.ustglobal.RestAssuredLearn;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;

public class JiraApiAddComment {

	public static String username="tharunmohan06@gmail.com";
	public static String password="PfAhHlUhFpd0aXhDZuqJ79B6";
	
	public static void main(String[] args) {
		
		String issueId="TES-2";

		RestAssured.baseURI="https://tharun06.atlassian.net/";
		given().auth().preemptive().basic(username, password).header("Content-Type","application/json").body(addJiraIssueBody())
		.when().post("rest/api/2/issue/"+issueId+"/comment").then().log().all();
		
	}

	public static String addJiraIssueBody()
	{
		return "{\r\n"
				+ "    \"body\": \"This is a comment regarding the quality of the response.\"\r\n"
				+ "}";
	}
}
