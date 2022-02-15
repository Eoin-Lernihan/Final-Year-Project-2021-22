package ie.gmit.sw.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;

import ie.gmit.sw.data.dao.UserDao;
import ie.gmit.sw.data.model.User;
 public class UserController implements RequestStreamHandler
 //RequestHandler <Map<String,Object>, List <User>>
{	
	 private UserDao userDao = new UserDao();
	 private String firstName;
	 private String lastName;
	 private String email;
	 private String userName;
	 private String number;
	 //testing
	public static void main(String[] args){
		UserController hello = new UserController();
		//JSONObject a = hello.extractedToResponse();
		hello.handleRequestGetTester("Funnyname");
		hello.extractedToResponse();
		//System.out.println(a);
	}
	
	//testing
	public String myHandler(Map<String,Object> input, Context context) 
	{
		System.out.println(input);
        return "Hello World";
    }
	public List<User> handleRequestAWS(Map<String,Object> input, Context context) {
		System.out.println(input);
		//List<User> allUser = new ArrayList<>();
		//User user = new User();
		//user.setEmail("email");
		//user.setFirstName("first");
		//user.setLastName("lastNam");
		//allUser.add(user);
	    List<User> allUser = userDao.getAllUser();
		return allUser;
	}
	
	public void handleRequestGetSingle(Map<String,Object> input, Context context) {
		System.out.println(input);
		String userName =(String)input.getOrDefault("userName", "");
	    List<User> allUser = userDao.getAUser(userName);
		//return allUser;
	}
	public void handleRequestGetTester(String input) {
		System.out.println(input);
		String userName = input;
	    List<User> allUser = userDao.getAUser(userName);
		//return allUser;
	}
	
	
/**
 * test event for aws lambda
{
   "body": "{\"id\": 1, \"name\": \"John Doe\"}"
}

 */
	
	public void handleRequestGSon(InputStream input, OutputStream output, Context context) throws IOException {
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
		//List<User> allUser = new ArrayList<>();
		//User user = new User();
		//user.setEmail("email");
		//user.setFirstName("first");
		//user.setLastName("lastNam");
		//allUser.add(user);
	    List<User> allUser = userDao.getAllUser();
		responseBody.put("users", new Gson().toJson(allUser));
		responseJson.put("body", responseBody);
		responseJson.put("statusCode", 200);
		return responseJson;
	}
	

	@Override
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		// TODO Auto-generated method stub
		
	}
	

	 
	public void handleRequestsParm(Map<String,Object> input, Context context) {
		 setterForPostPut(input);
		userDao.addUser(firstName, lastName, email, userName, number);  
	}


	public void handleRequestPut(Map<String,Object> input, Context context) {
		 setterForPostPut(input);
		String newUserName = (String)input.getOrDefault("newUserName", "");
		String newNumber = (String)input.getOrDefault("newNumber", "");
		userDao.updateUser(firstName, lastName, email, userName, number, newUserName ,newNumber);
	}

	
	private void setterForPostPut(Map<String, Object> input) {
		firstName =(String)input.getOrDefault("firstName", "");
		 lastName =(String)input.getOrDefault("lastName", "");
		 email =(String)input.getOrDefault("email", "");
		 userName =(String)input.getOrDefault("userName", "");
		 number = (String)input.getOrDefault("number", "");
	}
	
	//Orignal version for getting users
	//public List<User> handleRequest(Map<String,Object> input, Context context){
	//System.out.println(input);
    //List<User> allUser = userDao.getAllUser();
	//return allUser;
	//}

 

	



	
}
