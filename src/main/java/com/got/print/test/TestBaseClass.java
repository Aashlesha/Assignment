package com.got.print.test;

import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.boot.test.web.client.TestRestTemplate;

public class TestBaseClass {

	protected String baseURL;
	protected TestRestTemplate template;
	
	// The field MUST be marked to true if the JUNITS run against another deployment and not local JUNIT spun deployment
	protected boolean customHostEnabled = false;
	
	@Before
	public void setUp() throws Exception {
		String hostPort = "localhost:8090";
		customHostEnabled = true;
		baseURL = "http://" + hostPort ;
		template = new TestRestTemplate();
	}
	
	@BeforeClass
	public static  void setup(){
		System.setProperty("spring.cloud.consul.config.enabled", "false");
		System.setProperty("spring.profiles.active", "secured");
	}
}