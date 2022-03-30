package ie.gmit.sw.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;

import ie.gmit.sw.data.dao.AdminDao;
import ie.gmit.sw.data.dao.AllObjectsGet;
import ie.gmit.sw.data.dao.TournamentsDao;
import ie.gmit.sw.data.mapper.TournamentsMapper;
import ie.gmit.sw.data.mapper.UserMapper;
import ie.gmit.sw.data.model.Tournament;
import ie.gmit.sw.data.model.User;

public class TournamentsController extends BaseController implements RequestStreamHandler {
	private static final String PATH_OR_QUERY_PARAM = "touramentId";
	AllObjectsGet tournamentsDao = new TournamentsDao();

		/**
		 * Gets all user in the database and tranforms them by putting in a google json
		 * format
		 * 
		 * @return
		 */
		public void getAllTournaments(InputStream input, OutputStream output, Context context) throws IOException {
			List<Object> all = getAll();
			JSONObject responseJson = null;
			createJsonResponse(output, all, responseJson);
		}
//userName=''&inGame=''
		public void getAllTournamentsWithParams(InputStream input, OutputStream output, Context context) throws IOException {
			JSONObject responseJson = null;
			JSONObject event = extractInputData(input, responseJson);
			Map<String, String> filters = extractFilters(event, responseJson, "userName", TournamentsMapper.PLAYERS);
			Map<String, String> filters2 = extractFilters(event, responseJson, "inGame", "included");
			filters.putAll(filters2);
			 
//			Map<String, String> filters = getQueryAndPathParams(input, responseJson, queryParameterName, dbFieldName);
			List<Object> all = getAll();
			JSONObject responseJson = null;
			createJsonResponse(output, all, responseJson);
		}
		
		/**
		 * Gets a user in the database and
		 * 
		 * @return
		 */
		public void getATournaments(InputStream input, OutputStream output, Context context) throws IOException {
			JSONObject responseJson = null;
			//Need for path paramater on API gateway 
			findOneValueinDAO(input, output, responseJson, PATH_OR_QUERY_PARAM, TournamentsMapper.NUMBER);
		}

		/**
		 * Adds a new user to the database
		 * 
		 * @param input
		 * @param context
		 */
		public void addsNewTournaments(InputStream input, OutputStream output, Context context) throws IOException {

			JSONObject responseJson = null;
			JSONObject event = extractInputData(input, responseJson);

			Tournament request = extractTournmantFromInput(event, responseJson);
			//no errors
			if  (responseJson==null) {
				tournamentsDao.addOne(request);
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
		public void updateExistingTournament(InputStream input, OutputStream output, Context context) throws IOException {

			JSONObject responseJson = null;
			JSONObject event = extractInputData(input, responseJson);
			Tournament request = extractTournmantFromInput(event, responseJson);	        
			Map<String, String> filters = extractFilters(event, responseJson, PATH_OR_QUERY_PARAM, TournamentsMapper.NUMBER); 
			//no errors
			if  (responseJson==null) {
				tournamentsDao.updateOne(request,filters);
				responseJson = new JSONObject();
				responseJson.put("statusCode",  201);	    	
		    }

			createOutPutStream(output, responseJson);
		}


		private Tournament extractTournmantFromInput(JSONObject event, JSONObject responseJson) {
			String body = setupExtractInputPayload(event, responseJson);
		    Gson gson = new Gson();
		    Tournament request = gson.fromJson(body, Tournament.class);
			return request;
		}

		
		protected List<Object> getOne(Map<String, String> filters) {
			return tournamentsDao.getOne(filters);
		}
		protected List<Object> getAll() {
			return tournamentsDao.getAll();
		}


		@Override
		public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
			// TODO Auto-generated method stub

		}

		@Override
		protected String getResources() {
			return "tournaments";

		}

		@Override
		protected void deleteOne(Map<String, String> key) {
			// TODO Auto-generated method stub
			
		}



}
