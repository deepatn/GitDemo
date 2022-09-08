package Resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild 
{
	public static AddPlace addPlacePayload(String name, String language, String address) 
	{
		AddPlace place = new AddPlace();
		place.setAccuracy(50);
		place.setName(name);
		place.setPhone_number("(+91) 983 893 3937");
		place.setAddress(address);
		List<String> list = new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		place.setLocation(location);
		return place;
	}
	
	public static String deletepayload(String place_id)
	{
		return "{\r\n"
				+ "    \"place_id\":\""+place_id+"\"\r\n"
				+ "}\r\n"
				+ "";
	}
}
