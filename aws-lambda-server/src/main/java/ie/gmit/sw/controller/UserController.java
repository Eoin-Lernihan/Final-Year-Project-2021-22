package ie.gmit.sw.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
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
	 /**
	  * Mainly used for local testing
	  * @param args
	  */
	//testing
	public static void main(String[] args){
		UserController hello = new UserController();
		//JSONObject a = hello.extractedToResponse();
		//hello.handleRequestGetTester("Funnyname");
		//hello.extractedToResponse();
		//hello.getsUserGSon(
		//System.out.println(a);
	}
	
	/**
	 * Gets all user in the database and tranforms them by putting in a google json format 
	 * @return
	 */

	public void getsUserGson(InputStream input, OutputStream output, Context context) throws IOException {
		List<Object> all = getAll();
		setupExtractInputData(input);
	    createJsonResponse(output, all);
	}

	private void setupExtractInputData(InputStream input) {
		JSONParser parser = new JSONParser();
	    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	}

	private void createJsonResponse(OutputStream output, Object all)
			throws UnsupportedEncodingException, IOException {
		JSONObject responseJson = getResposeAsJson(all);
		OutputStreamWriter writer = new OutputStreamWriter(output, "UTF-8");
	    writer.write(responseJson.toString());
	    writer.close();
	}
	
	 /**
	  * Gets a Single User fomt the database without into going into a json string
	  * @param input
	  * @param context
	  */
	public void handleRequestGetSingle(Map<String,Object> input, Context context) {
		System.out.println(input);
		String userName =(String)input.getOrDefault("userName", "");
	    User user = userDao.getAUser(userName).get(0);
	    return ;
	}
	 /**
	  * Used to test geting a single user back, without uploading a to lambda
	  * Mainly used for local testing
	  * @param args
	  */
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

	/**
	 * Gets all user in the database and returns them to
	 * @return
	 */
	private JSONObject getResposeAsJson(Object response) {
		JSONObject responseJson = new JSONObject();
		JSONObject responseBody = new JSONObject();
		responseBody.put("users", new Gson().toJson(response));
		responseJson.put("body", responseBody);
		responseJson.put("statusCode", 200);
		return responseJson;
	}
	
	public List<Object> getAll() {
		return userDao.getAll();
	}
	

	
	/**
	 * Adds a new user to the database
	 * @param input
	 * @param context
	 */
	public void addsNewUser(Map<String,Object> input, Context context) {
		setterForPostPut(input);
		userDao.addUser(firstName, lastName, email, userName, number);  
	}

	/**
	 * This is the PUT/update method which allows someone to update an existing user.
	 * @param input
	 * @param context
	 */
	public void updateExistingUser(Map<String,Object> input, Context context) {
		 setterForPostPut(input);
		String newUserName = (String)input.getOrDefault("newUserName", "");
		String newNumber = (String)input.getOrDefault("newNumber", "");
		userDao.updateUser(firstName, lastName, email, userName, number, newUserName ,newNumber);
	}

	/**
	 * Is the base setter for the update and adding method	
	 * @param input
	 */
	private void setterForPostPut(Map<String, Object> input) {
		firstName =(String)input.getOrDefault("firstName", "");
		 lastName =(String)input.getOrDefault("lastName", "");
		 email =(String)input.getOrDefault("email", "");
		 userName =(String)input.getOrDefault("userName", "");
		 number = (String)input.getOrDefault("number", "");
	}
	

	@Override
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		// TODO Auto-generated method stub
		
	}
	

 

	



	
}
