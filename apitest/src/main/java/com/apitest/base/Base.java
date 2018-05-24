package com.apitest.base;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.apitest.utils.MethodUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class Base {
	public  MethodUtils methodUtils;
@BeforeClass
public void setup() {
	useRelaxedHTTPSValidation();//忽略https请求
//	RestAssured.baseURI = "https://travel.api.szjqb.net";//设置全局域名

	methodUtils = new MethodUtils();
}
@Test
public void test() {
//	String token = given().param("username","15827894541").param("password","zaizai123").when().post("https://travel.api.szjqb.net/api/Agency/User/loginAgent")
//		.then().extract().path("data.token");
//	System.out.println(token);
//			
//	Response response = given().param("username","15827894541").param("password","zaizai123").
//			when().post("https://travel.api.szjqb.net/api/Agency/User/loginAgent")
//			.then().statusCode(200)
//			.extract().response()
//			;
//	
//	String token = response.getBody().jsonPath().get("data.token");
//	int role = response.getBody().jsonPath().get("data.role");
//System.out.println(token+role);
	Map<String, String> data = new HashMap<String, String>();
	data.put("username", "15827894541");
	data.put("password", "zaizai123");
	Response response = methodUtils.post_json(data, "https://travel.api.szjqb.net/api/Agency/User/loginAgent");
	String token = response.getBody().jsonPath().get("data.token");
	System.out.println(token);
	
}
}
