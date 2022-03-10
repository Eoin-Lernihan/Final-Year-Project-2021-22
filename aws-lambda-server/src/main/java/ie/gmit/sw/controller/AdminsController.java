package ie.gmit.sw.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;

import ie.gmit.sw.data.dao.AdminDao;
import ie.gmit.sw.data.dao.AllObjectsGet;
import ie.gmit.sw.data.mapper.AdminsMapper;
import ie.gmit.sw.data.mapper.UserMapper;
import ie.gmit.sw.data.model.Admin;
import ie.gmit.sw.data.model.User;

public class AdminsController extends BaseController implements RequestStreamHandler {
	private static final String PATH_OR_QUERY_PARAM = "adminId";
	AllObjectsGet adminDao = new AdminDao();
	

	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		AdminsController adminsController = new AdminsController();
		OutputStream output = System.out;
		List<Object> all = adminsController.getAll();
		JSONObject responseJson = null;
		adminsController.createJsonResponse(output, all, responseJson);
	}

		/**
		 * Gets all user in the database and tranforms them by putting in a google json
		 * format
		 * 
		 * @return
		 */
		public void getAlAdmin(InputStream input, OutputStream output, Context context) throws IOException {
			JSONObject responseJson = null;
			JSONObject event = extractInputData(input, responseJson);
			List<Object> all  = new ArrayList<>();
			if(event != null) {
				
				Map<String, String> filters = new HashMap<>();
				// change here username andpassword
				String filterUsername = extractPramsFormPathOrQuarry( "username", event);
				String filterPassword = extractPramsFormPathOrQuarry( "password", event);
				if (filterPassword==null || filterUsername==null) {
					System.out.println("No username or Password");
					all = getAll();
				} else {
					System.out.println("username ="+filterUsername);
					System.out.println("Password ="+filterPassword);
					//MongoDB
					filters.put(AdminsMapper.COMPANY_USERNAME, filterUsername);
					filters.put(AdminsMapper.COMPANY_PASSWORD, filterPassword);
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
		public void getAAdmin(InputStream input, OutputStream output, Context context) throws IOException {
			JSONObject responseJson = null;
			findOneValueinDAO(input, output, responseJson, PATH_OR_QUERY_PARAM, AdminsMapper.COMPANY_USERNAME);
		}

		/**
		 * Adds a new user to the database
		 * 
		 * @param input
		 * @param context
		 */
		public void addsNewAdmin(InputStream input, OutputStream output, Context context) throws IOException {

			JSONObject responseJson = null;
			JSONObject event = extractInputData(input, responseJson);

			Admin request = extractAdminFromInput(event, responseJson);
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
		public void updateExistingAdmin(InputStream input, OutputStream output, Context context) throws IOException {

			JSONObject responseJson = null;

			JSONObject event = extractInputData(input, responseJson);
			System.out.println("Ful event" +event);
			Admin request = extractAdminFromInput(event, responseJson);	 
			Map<String, String> filters = extractFilters(event, responseJson,PATH_OR_QUERY_PARAM, UserMapper.USERNAME);

			//no errors
			if  (responseJson==null) {
				adminDao.updateOne(request,filters);
				responseJson = new JSONObject();
				responseJson.put("statusCode",  201);	    	
		    }

			createOutPutStream(output, responseJson);
		}


		private Admin extractAdminFromInput( JSONObject event, JSONObject responseJson) {
			String body = setupExtractInputPayload(event, responseJson);
		    Gson gson = new Gson();
		    Admin request = gson.fromJson(body, Admin.class);
			return request;
		}

		
		protected List<Object> getOne(Map<String, String> filters) {
			return adminDao.getOne(filters);
		}
		protected List<Object> getAll() {
			return adminDao.getAll();
		}


		@Override
		public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
			// TODO Auto-generated method stub

		}

		@Override
		protected String getResources() {
			return "admins";
		}



}
