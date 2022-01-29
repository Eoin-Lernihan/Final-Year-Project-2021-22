package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;
 public class UserController implements RequestStreamHandler
 //RequestHandler <Map<String,Object>, List <User>>
{	
	 UserDao userDao = new UserDao();
	
	 //testing
	public static void main(String[] args){
		UserController hello = new UserController();
		JSONObject a = hello.extractedToResponse();
		System.out.println(a);
	}
	
	//testing
	public String myHandler(Map<String,Object> input, Context context) 
	{
		System.out.println(input);
        return "Hello World";
    }
	
//	public List<User> handleRequest(Map<String,Object> input, Context context) {
//		System.out.println(input);
//    	List<User> allUser = new ArrayList<>();
//    	User user = new User();
//    	user.setEmail("email");
//    	user.setFirstName("first");
//    	user.setLastName("lastNam");
//		return allUser;
//	}
	
	
/**
 * test event for aws lambda
{
   "body": "{\"id\": 1, \"name\": \"John Doe\"}"
}

 */
	@Override
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		JSONParser parser = new JSONParser();
	    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	    JSONObject responseJson = extractedToResponse();
		
		
		OutputStreamWriter writer = new OutputStreamWriter(output, "UTF-8");
	    writer.write(responseJson.toString());
	    writer.close();
	}

	public JSONObject extractedToResponse() {
		JSONObject responseJson = new JSONObject();
		JSONObject responseBody = new JSONObject();
		List<User> allUser = new ArrayList<>();
		User user = new User();
		user.setEmail("email");
		user.setFirstName("first");
		user.setLastName("lastNam");
		allUser.add(user);
		responseBody.put("users", new Gson().toJson(allUser));
		responseJson.put("body", responseBody);
		responseJson.put("statusCode", 200);
		return responseJson;
	}
	
	 
	 
	
	//Orignal version for getting users
	//public List<User> handleRequest(Map<String,Object> input, Context context) {
		//System.out.println(input);
    //	List<User> allUser = userDao.getAllUser();
	//	return allUser;
//	}



	



	
}
