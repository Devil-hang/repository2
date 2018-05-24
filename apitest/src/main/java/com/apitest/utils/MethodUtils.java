package com.apitest.utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;


public class MethodUtils {

//post方法---	使用json传参,并返回response
	public Response post_json(Map<String, String> data, String url_Path) {

		Response response = null;
			try {
		response = given().params(data).when().post(url_Path).then().extract().response();
		
			if(response.statusCode() == 200) {
				return response;
			}else {
				return null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
