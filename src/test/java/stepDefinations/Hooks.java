package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;





public class Hooks 
{
	@Before(value="@DeletePlace")
	public void beforeScenario() throws IOException 
	{
		
		StepDefination sd = new StepDefination();
		if(StepDefination.place_id == null)
		{
		sd.add_place_payload_with("Deepa", "Kannada", "Bengaluru");
		sd.user_call_with_post_http_request("AddPlaceAPI", "Post");
		sd.verify_place_Id_created_maps_to_using("Deepa", "GetPlaceAPI");
		}
	}
}
