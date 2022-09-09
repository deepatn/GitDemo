package stepDefinations;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Resources.APIResources;
import Resources.TestDataBuild;
import Resources.Utils;

import static io.restassured.RestAssured.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

public class StepDefination extends Utils
{
	RequestSpecification request;
	ResponseSpecification resSpec;
	Response response;
	static String place_id;
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String string, String string2, String string3) throws IOException {
	   
		
		AddPlace place = TestDataBuild.addPlacePayload(string,string2, string3);
		request = given().log().all().spec(RequestSpec()).body(place);
	}

	@When("user call {string} with {string} http request")
	public void user_call_with_post_http_request(String string, String string2) 
	{
		APIResources resource = APIResources.valueOf(string);
		resSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
		if(string2.equals("Post"))
		{
		response = request.when().post(resource.getResource()).then().spec(resSpec).log().all().extract().response();
		}
		if(string2.equals("Get"))
		{
			response = request.when().get(resource.getResource());
		}
	}
	@Then("the API call is success with status code {int}")
	public void the_API_call_is_success_with_status_code(Integer int1) 
	{
		assertEquals(response.getStatusCode(), 200);
		System.out.println("Status code success is 200");
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String string, String string2) 
	{
		assertEquals(getJsonPath(response, string),string2);
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException 
	{
	    place_id = getJsonPath(response, "place_id");
	    request = given().log().all().spec(RequestSpec()).queryParam("place_id", place_id);
	    user_call_with_post_http_request(resource, "Get");
	    String actname = getJsonPath(response, "name");
	    assertEquals(actname, expectedName);
	}
	
	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
		request = given().spec(RequestSpec()).body(TestDataBuild.deletepayload(place_id));
	}

}
