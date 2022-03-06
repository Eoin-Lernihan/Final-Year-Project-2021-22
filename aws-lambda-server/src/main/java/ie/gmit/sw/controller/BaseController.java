package ie.gmit.sw.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;

public abstract class BaseController {

	public BaseController() {
		super();
	}

	protected void findOneValueinDAO(InputStream input, OutputStream output, JSONObject responseJson, String queryParameterName)
			throws UnsupportedEncodingException, IOException {
				String collectionFillter = setupExtractInputDataQueryParam(input, queryParameterName, responseJson);
				List<Object> all = getOne(collectionFillter);
				Object  response = null;
				if (all.isEmpty()) {
					responseJson.put("statusCode", 400);
					responseJson.put("exception", "No " + queryParameterName +  " found for " +collectionFillter );	
				} else {
					response = all.get(0);
				}
				
				createJsonResponse(output, response, responseJson);
			}

	protected String setupExtractInputPayload(InputStream input, JSONObject responseJson) {
	
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

	protected void createJsonResponse(OutputStream output, Object all, JSONObject responseJson)
			throws UnsupportedEncodingException, IOException {
				responseJson = getResposeAsJson(all, responseJson);
				createOutPutStream(output, responseJson);
			}

	protected void createOutPutStream(OutputStream output, JSONObject responseJson)
			throws UnsupportedEncodingException, IOException {
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

	
	protected abstract List<Object> getAll(); 

	protected abstract List<Object>  getOne(String key); 


}