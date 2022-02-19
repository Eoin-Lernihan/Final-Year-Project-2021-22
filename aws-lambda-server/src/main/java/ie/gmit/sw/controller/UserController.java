package ie.gmit.sw.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;

import ie.gmit.sw.data.dao.AllObjectsGet;
import ie.gmit.sw.data.dao.UserDao;
import ie.gmit.sw.data.model.User;

/**
 * {@link} https://www.baeldung.com/aws-lambda-api-gateway
 * 
 * @author eoinb
 *
 */
public class UserController implements RequestStreamHandler
{
	private AllObjectsGet userDao = new UserDao();

	/**
	 * Gets all user in the database and tranforms them by putting in a google json
	 * format
	 * 
	 * @return
	 */
	public void getAllUsers(InputStream input, OutputStream output, Context context) throws IOException {

		List<Object> all = getAll();
		JSONObject responseJson = null;
		createJsonResponse(output, all, responseJson);
	}

	/**
	 * Gets a user in the database and
	 * 
	 * @return
	 */
	public void getAUserUsers(InputStream input, OutputStream output, Context context) throws IOException {
		JSONObject responseJson = null;
		String collectionName  = "username";
		findOneValueinDAO(input, output, responseJson, collectionName);
	}

	/**
	 * Adds a new user to the database
	 * 
	 * @param input
	 * @param context
	 */
	public void addsNewUser(InputStream input, OutputStream output, Context context) throws IOException {

		JSONObject responseJson = null;
		User request = extractUserFromInput(input, responseJson);
	        
		userDao.addOne(request);
		responseJson = new JSONObject();
		responseJson.put("statusCode",  201);
	}
	
	/**
	 * Adds a new user to the database
	 * 
	 * @param input
	 * @param context
	 */
	public void updateExistingUser(InputStream input, OutputStream output, Context context) throws IOException {

		JSONObject responseJson = null;
		User request = extractUserFromInput(input, responseJson);
	        
		userDao.updateOne(request);
		responseJson = new JSONObject();
		responseJson.put("statusCode",  201);
	}

	private User extractUserFromInput(InputStream input, JSONObject responseJson) {
		String body = setupExtractInputPayload(input, responseJson);
	    Gson gson = new Gson();
        User request = gson.fromJson(body, User.class);
		return request;
	}

	private void findOneValueinDAO(InputStream input, OutputStream output, JSONObject responseJson,
			String collectionName) throws UnsupportedEncodingException, IOException {
		String collectionFillter = setupExtractInputDataQueryParam(input, collectionName, responseJson);
		List<Object> all = getOne(collectionFillter);
		Object  response = null;
		if (all.isEmpty()) {
			responseJson.put("statusCode", 400);
			responseJson.put("exception", "No " + collectionName +  " found for " +collectionFillter );	
		} else {
			response = all.get(0);
		}
		
		createJsonResponse(output, response, responseJson);
	}
	
	private String setupExtractInputPayload(InputStream input, JSONObject responseJson) {

		JSONObject event = extractInputData(input, responseJson);
		if (responseJson != null) {
			return null;
		}
		return (String) event.get("body");
	}

	private String setupExtractInputDataQueryParam(InputStream input, String key, JSONObject responseJson) {
		JSONObject event = extractInputData(input, responseJson);
		if (responseJson != null) {
			return null;
		}
		String value = null;
		if (event.get("pathParameters") != null) {
			JSONObject pps = (JSONObject) event.get("pathParameters");
			if (pps.get(key) != null) {
				value = (String) pps.get(key);
			}
		} else if (event.get("queryStringParameters") != null) {
			JSONObject qps = (JSONObject) event.get("queryStringParameters");
			if (qps.get(key) != null) {
				value = (String) qps.get(key);
			}
		}
		if (value==null) {
			responseJson = new JSONObject();
			responseJson.put("statusCode", 400);
			responseJson.put("exception", "No value for key in URL " + key );
		}
		return value;

	}


	private JSONObject extractInputData(InputStream input, JSONObject responseJson) {
		JSONObject event = null;
		JSONParser parser = new JSONParser();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		try {
			event = (JSONObject) parser.parse(reader);
		} catch (IOException | ParseException ex) {
			// setup response to retur n failure
			if (responseJson == null) {
				responseJson = new JSONObject();
			}
			responseJson.put("statusCode", 400);
			responseJson.put("exception", ex);
		}
		return event;
	}

	private void createJsonResponse(OutputStream output, Object all, JSONObject responseJson)
			throws UnsupportedEncodingException, IOException {
		responseJson = getResposeAsJson(all, responseJson);
		OutputStreamWriter writer = new OutputStreamWriter(output, "UTF-8");
		writer.write(responseJson.toString());
		writer.close();
	}

	/**
	 * Gets all user in the database and returns them to
	 * 
	 * @param responseJson
	 * @return
	 */
	private JSONObject getResposeAsJson(Object response, JSONObject responseJson) {
		if (responseJson != null) {
			return responseJson;
		}
		responseJson = new JSONObject();
		JSONObject responseBody = new JSONObject();
		responseBody.put("users", new Gson().toJson(response));
		responseJson.put("body", responseBody);
		responseJson.put("statusCode", 200);
		return responseJson;
	}

	public List<Object> getAll() {
		return userDao.getAll();
	}
	

	private List<Object> getOne(String username) {
		return userDao.getOne(username);
	}

	
	
	
	
//	/**
//	 * Used to test geting a single user back, without uploading a to lambda Mainly
//	 * used for local testing
//	 * 
//	 * @param args
//	 */
//	public void handleRequestGetTester(String input) {
//		System.out.println(input);
//		String userName = input;
//		List<User> allUser = userDao.getAUser(userName);
//		// return allUser;
//	}


	
	
	/**
	 * Mainly used for local testing
	 * 
	 * @param args
	 */
	// testing
	public static void main(String[] args) {
		UserController hello = new UserController();
		// JSONObject a = hello.extractedToResponse();
		// hello.handleRequestGetTester("Funnyname");
		// hello.extractedToResponse();
		// hello.getsUserGSon(
		// System.out.println(a);
	}


	/**
	 * test event for aws lambda { "body": "{\"id\": 1, \"name\": \"John Doe\"}" }
	 * 
	 */



	@Override
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		// TODO Auto-generated method stub

	}

}
