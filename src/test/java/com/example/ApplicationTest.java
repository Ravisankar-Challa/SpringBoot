package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static com.example.util.Constants.APPLICATION_JSON;
import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTest {
    
    @LocalServerPort
    private int port;
    
    @Before
    public void setup() {
        RestAssured.port = port;
    }

	@Test
	public void contextLoads() {
	    given().
	            contentType(APPLICATION_JSON).
	            body("{\"test\":\"kiwi\"}").
	    when().
	            post("/hello").
	    then().
	            assertThat().
	            body(matchesJsonSchemaInClasspath("test.json"));
	}

}
