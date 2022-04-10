package ie.gmit.sw.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;

import ie.gmit.sw.data.dao.DaoCommonInterface;
import ie.gmit.sw.data.dao.UserDao;
import ie.gmit.sw.data.mapper.UserMapper;
import ie.gmit.sw.data.model.User;

/**
 * {@link} https://www.baeldung.com/aws-lambda-api-gateway
 * 
 * @author eoinb
 *
 */
public class UserController extends BaseController implements RequestStreamHandler
{
	private static final String PATH_OR_QUERY_PARAM = "username";
	DaoCommonInterface userDao = new UserDao();

	/**
	 * Gets all user in the database and tranforms them by putting in a google json
	 * format
	 * 
	 * @return
	 */
	public void getAlUsers(InputStream input, OutputStream output, Context context) throws IOException {
		JSONObject responseJson = null;
		JSONObject event = extractInputData(input, responseJson);
		List<Object> all  = new ArrayList<>();
		if(event != null) {
			
			Map<String, String> filters = new HashMap<>();
			System.out.println("This is the event "+ event.toJSONString());
			String filterUsername = extractPramsFormPathOrQuarry( PATH_OR_QUERY_PARAM, event);
			String filterPassword = extractPramsFormPathOrQuarry( "password", event);
			if (filterPassword==null || filterUsername==null) {
				System.out.println("No username or Password");
				all = getAll();
			} else {
				System.out.println("username ="+filterUsername);
				System.out.println("Password ="+filterPassword);
				filters.put(UserMapper.USERNAME, filterUsername);
				filters.put(UserMapper.PASSWORD, filterPassword);
				all = getOne(filters);
			}
		}
		createJsonResponse(output, all, responseJson);

	}

	/**
	 * Gets a user in the database and
	 * 
	 * @return
	 */
	public void getAUserUsers(InputStream input, OutputStream output, Context context) throws IOException {
		JSONObject responseJson = null;
		findOneValueinDAO(input, output, responseJson, PATH_OR_QUERY_PARAM, UserMapper.USERNAME);
	}
	/**
	 * Deletes a user form the database
	 * @param input
	 * @param output
	 * @param context
	 * @throws IOException
	 */
	public void deleteAUser(InputStream input, OutputStream output, Context context) throws IOException {
		JSONObject responseJson = null;
		deleteOneInDao(input, output, responseJson, PATH_OR_QUERY_PARAM, UserMapper.USERNAME);
	}

	/**
	 * Adds a new user to the database
	 * 
	 * @param input
	 * @param context
	 */
	public void addsNewUser(InputStream input, OutputStream output, Context context) throws IOException {

		JSONObject responseJson = null;
		
		JSONObject event = extractInputData(input, responseJson);
		User request = extractUserFromInput(event, responseJson);
		//no errors
		if  (responseJson==null) {
			userDao.addOne(request);
			responseJson = new JSONObject();
			Headers headers = new Headers();
			responseJson.put("headers", headers);
			responseJson.put("statusCode",  201);	    	
	    }
		createOutPutStream(output, responseJson);
	}
	
	/**
	 * Adds a new user to the database
	 * 
	 * @param input
	 * @param context
	 */
	public void updateExistingUser(InputStream input, OutputStream output, Context context) throws IOException {

		JSONObject responseJson = null;
		JSONObject event = extractInputData(input, responseJson);
		User request = extractUserFromInput(event, responseJson);	

		Map<String, String> filters = extractFilters(event, responseJson,PATH_OR_QUERY_PARAM, UserMapper.USERNAME);

		//no errors
		if  (responseJson==null) {
			userDao.updateOne(request, filters);
			responseJson = new JSONObject();
			responseJson.put("statusCode",  201);	    	
	    }

		createOutPutStream(output, responseJson);
	}

	/**
	 * 		 * extracts the request body and creates a user object
	 * @param event
	 * @param responseJson
	 * @return
	 */
	private User extractUserFromInput(JSONObject event, JSONObject responseJson) {
		String body = setupExtractInputPayload(event, responseJson);
	    Gson gson = new Gson();
        User request = gson.fromJson(body, User.class);
		return request;
	}

	
	protected List<Object> getOne(Map<String, String> key) {
		return userDao.getOne(key);
	}
	
	protected void deleteOne(Map<String, String> mapKeys) {
		String username = mapKeys.get("username");
		userDao.deleteOne(username);
	}
	
	protected List<Object> getAll() {
		return userDao.getAll();
	}


	@Override
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getResources() {		// TODO Auto-generated method stub
		return "users";
	}


}
