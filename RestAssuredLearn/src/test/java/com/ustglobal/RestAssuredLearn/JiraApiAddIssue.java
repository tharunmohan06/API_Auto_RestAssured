package com.ustglobal.RestAssuredLearn;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;

public class JiraApiAddIssue {

	public static String username="tharunmohan06@gmail.com";
	public static String password="PfAhHlUhFpd0aXhDZuqJ79B6";
	
	public static void main(String[] args) {

		RestAssured.baseURI="https://tharun06.atlassian.net/";
		given().auth().preemptive().basic(username, password).header("Content-Type","application/json").body(addJiraIssueBody())
		.when().post("rest/api/2/issue/").then().log().all();
		
	}

	public static String addJiraIssueBody()
	{
		return "{\r\n"
				+ "    \"fields\": {\r\n"
				+ "        \"project\": {\r\n"
				+ "            \"key\": \"TES\"\r\n"
				+ "        },\r\n"
				+ "        \"summary\": \"REST ye merry gentlemen.\",\r\n"
				+ "        \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\r\n"
				+ "        \"issuetype\": {\r\n"
				+ "            \"name\": \"Bug\"\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ "}";
	}
}
