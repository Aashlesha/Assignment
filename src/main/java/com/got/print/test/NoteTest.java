package com.got.print.test;

import static com.jayway.restassured.RestAssured.given;

import org.json.JSONObject;
import org.junit.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class NoteTest extends TestBaseClass {

//	@Test
    public void testGetNoteById() throws JsonProcessingException, Exception {
    	
    	String mainResourcesUrl = baseURL+"/gotPrint/users/1/notes/1" ;
    	
    	System.out.println("Main URL:"+  mainResourcesUrl);
    	System.out.println("***************************testGetNoteById********************************* ");
		
	   
	    RequestSpecBuilder builder = new RequestSpecBuilder(); 
	    
	    builder.setContentType("application/json; charset=UTF-8");
	    RequestSpecification requestSpec = builder.build(); 
	    Response response = given().
	    		authentication().
	    		preemptive().
	    		basic("","") .
	    		spec(requestSpec).
	    		when().get(mainResourcesUrl); 
	    
	    
	    JSONObject JSONResponseBody = new JSONObject(response.body().asString());
	    
	    	
		Assert.assertEquals(response.statusCode(), 200);
		System.out.println("Response JSON ::"+JSONResponseBody);
    }
    
//	@Test
    public void testNoteCreate() throws JsonProcessingException, Exception {
    	
		String topLevelResourcesUrl = baseURL+"/gotPrint/users/1/notes/" ;
		
		System.out.println("Main URL "+  topLevelResourcesUrl);
		System.out.println("*************************** testNoteCreate ********************************* ");
		
	   	String input =   "{\"title\":\"title val\","
	   					 + "\"note\":\"note desc\"}";
	   	
	   
	    RequestSpecBuilder builder = new RequestSpecBuilder(); 

	    builder.setBody(input); 
	    builder.setContentType("application/got.print.note+json; charset=UTF-8");
	    
	    RequestSpecification requestSpec = builder.build(); 
	    
	    Response response = given().
	    		authentication().
	    		preemptive().
	    		basic("","") .
	    		spec(requestSpec).
	    		when().post(topLevelResourcesUrl); 
	    
	    Assert.assertEquals(response.statusCode(), 201);
	}
    
//	@Test
    public void testNoteUpdate() throws JsonProcessingException, Exception {
    	
		String topLevelResourcesUrl = baseURL+"/gotPrint/users/1/notes/1" ;
		
		System.out.println("Main URL "+  topLevelResourcesUrl);
		System.out.println("*************************** testNoteUpdate ********************************* ");
		
	   	String input =   "{\"title\":\"title val update\","
	   					 + "\"note\":\"note desc update\"}";
	   	
	   
	    RequestSpecBuilder builder = new RequestSpecBuilder(); 

	    builder.setBody(input); 
	    builder.setContentType("application/got.print.note+json; charset=UTF-8");
	    
	    RequestSpecification requestSpec = builder.build(); 
	    
	    Response response = given().
	    		authentication().
	    		preemptive().
	    		basic("","") .
	    		spec(requestSpec).
	    		when().post(topLevelResourcesUrl); 
	    
	    Assert.assertEquals(response.statusCode(), 200);
	}
    
//	@Test
    public void testDeleteNoteById() throws JsonProcessingException, Exception {
    	
    	String mainResourcesUrl = baseURL+"/gotPrint/users/2/notes/6" ;
    	
    	System.out.println("Main URL:"+  mainResourcesUrl);
    	System.out.println("***************************testDeleteNoteById********************************* ");
		
	   
	    RequestSpecBuilder builder = new RequestSpecBuilder(); 
	    
	    builder.setContentType("application/json; charset=UTF-8");
	    RequestSpecification requestSpec = builder.build(); 
	    Response response = given().
	    		authentication().
	    		preemptive().
	    		basic("","") .
	    		spec(requestSpec).
	    		when().delete(mainResourcesUrl); 
	    
	    
		Assert.assertEquals(response.statusCode(), 204);
    }
    
//	@Test
    public void testDeleteNoteById_InvalidID() throws JsonProcessingException, Exception {
    	
    	String mainResourcesUrl = baseURL+"/gotPrint/users/2/notes/qqq6" ;
    	
    	System.out.println("Main URL:"+  mainResourcesUrl);
    	System.out.println("***************************testDeleteNoteById********************************* ");
		
	   
	    RequestSpecBuilder builder = new RequestSpecBuilder(); 
	    
	    builder.setContentType("application/json; charset=UTF-8");
	    RequestSpecification requestSpec = builder.build(); 
	    Response response = given().
	    		authentication().
	    		preemptive().
	    		basic("","") .
	    		spec(requestSpec).
	    		when().delete(mainResourcesUrl); 
	    
	    
		Assert.assertEquals(response.statusCode(), 500);
    }
    
//	@Test
    public void testNoteById_InvalidID() throws JsonProcessingException, Exception {
    	
    	String mainResourcesUrl = baseURL+"/gotPrint/users/2/notes/qqq6" ;
    	
    	System.out.println("Main URL:"+  mainResourcesUrl);
    	System.out.println("***************************testDeleteNoteById********************************* ");
		
	   
	    RequestSpecBuilder builder = new RequestSpecBuilder(); 
	    
	    builder.setContentType("application/json; charset=UTF-8");
	    RequestSpecification requestSpec = builder.build(); 
	    Response response = given().
	    		authentication().
	    		preemptive().
	    		basic("","") .
	    		spec(requestSpec).
	    		when().delete(mainResourcesUrl); 
	    
	    
		Assert.assertEquals(response.statusCode(), 500);
    }
    
//	@Test
    public void testNoteById_InvalidUserId() throws JsonProcessingException, Exception {
    	
    	String mainResourcesUrl = baseURL+"/gotPrint/users/2asasa/notes/4" ;
    	
    	System.out.println("Main URL:"+  mainResourcesUrl);
    	System.out.println("***************************testDeleteNoteById********************************* ");
		
	   
	    RequestSpecBuilder builder = new RequestSpecBuilder(); 
	    
	    builder.setContentType("application/json; charset=UTF-8");
	    RequestSpecification requestSpec = builder.build(); 
	    Response response = given().
	    		authentication().
	    		preemptive().
	    		basic("","") .
	    		spec(requestSpec).
	    		when().delete(mainResourcesUrl); 
	    
	    
		Assert.assertEquals(response.statusCode(), 500);
    }
}
