package Resources;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils 
{
	static RequestSpecification request;
	public static RequestSpecification RequestSpec() throws IOException
	{
		if(request == null)
		{
		PrintStream stream = new PrintStream(new FileOutputStream("logging.txt"));
		request = new RequestSpecBuilder().addFilter(RequestLoggingFilter.logRequestTo(stream)).
		addFilter(ResponseLoggingFilter.logResponseTo(stream)).setBaseUri(getGlobalValue()).setContentType(ContentType.JSON).addQueryParam("key", "qaclick123").build();
		return request;
		}
		return request;
	}
	
	public static String getGlobalValue() throws FileNotFoundException, IOException 
	{
		Properties p = new Properties();
		p.load(new FileReader("F:\\Eclipse\\APIFramework\\src\\test\\java\\Resources\\global.properties"));
		String baseurl = p.getProperty("baseURL");
		return baseurl;
	}
	
	public static String getJsonPath(Response response, String key)
	{
		String res = response.asString();
		JsonPath jp = new JsonPath(res);
		return jp.get(key).toString();
	}
}