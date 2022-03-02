package ie.gmit.sw.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.json.simple.JSONObject;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;

import ie.gmit.sw.data.dao.AdminDao;
import ie.gmit.sw.data.dao.AllObjectsGet;
import ie.gmit.sw.data.model.User;

public class TournamentsController extends BaseController implements RequestStreamHandler {
	AllObjectsGet adminDao = new AdminDao();

		/**
		 * Gets all user in the database and tranforms them by putting in a google json
		 * format
		 * 
		 * @return
		 */
		public void getAlUsers(InputStream input, OutputStream output, Context context) throws IOException {
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
		public void addsNewAdmin(InputStream input, OutputStream output, Context context) throws IOException {

			JSONObject responseJson = null;
			User request = extractUserFromInput(input, responseJson);
			//no errors
			if  (responseJson==null) {
				adminDao.addOne(request);
				responseJson = new JSONObject();
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
			User request = extractUserFromInput(input, responseJson);	        
			//no errors
			if  (responseJson==null) {
				adminDao.updateOne(request);
				responseJson = new JSONObject();
				responseJson.put("statusCode",  201);	    	
		    }

			createOutPutStream(output, responseJson);
		}


		private User extractUserFromInput(InputStream input, JSONObject responseJson) {
			String body = setupExtractInputPayload(input, responseJson);
		    Gson gson = new Gson();
	        User request = gson.fromJson(body, User.class);
			return request;
		}

		
		protected List<Object> getOne(String username) {
			return adminDao.getOne(username);
		}
		protected List<Object> getAll() {
			return adminDao.getAll();
		}


		@Override
		public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
			// TODO Auto-generated method stub

		}

}
