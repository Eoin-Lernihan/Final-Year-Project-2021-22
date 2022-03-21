package ie.gmit.sw.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class BaseController {

	public BaseController() {
		super();
	}
	
	protected Map<String, String> extractFilters(JSONObject event,  JSONObject responseJson, String queryParameterName, String dbFieldName) {
		String collectionFillter = setupExtractInputDataQueryParam(event, queryParameterName, responseJson);
		Map<String, String> filters = new HashMap<>();
		if (dbFieldName==null) {
			filters.put(queryParameterName, collectionFillter);					
		} else {
			filters.put(dbFieldName, collectionFillter);
			
		}
		filters.put(queryParameterName, collectionFillter);
		return filters;
	}

	protected void findOneValueinDAO(InputStream input, OutputStream output, JSONObject responseJson, String queryParameterName, String dbFieldName)
		throws UnsupportedEncodingException, IOException {
		JSONObject event = extractInputData(input, responseJson);

		Map<String, String> filters = extractFilters(event, responseJson, queryParameterName, dbFieldName);
		List<Object> all = getOne(filters);
		Object  response = null;
		if (all.isEmpty()) {
			if (responseJson == null) {
				responseJson = new JSONObject();
			}
			responseJson.put("statusCode", 400);
			Headers headers = new Headers();
			responseJson.put("headers", headers);
			responseJson.put("exception", "No " + queryParameterName +  " found for " +filters );	
		} else {
			response = all.get(0);
		}
		
		createJsonResponse(output, response, responseJson);
	}

	
	protected String setupExtractInputPayload(JSONObject event, JSONObject responseJson) {
	
		return (String) event.get("body");
	}

	private String setupExtractInputDataQueryParam( JSONObject event, String key, JSONObject responseJson ) {
		if (responseJson != null) {
			return null;
		}
		return extractPramsFormPathOrQuarry(key, event);
	
	}

	protected String extractPramsFormPathOrQuarry(String key, JSONObject event) {
		JSONObject responseJson;
		String value = null;
		System.out.println();
		System.out.println(event);
		
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
			Headers headers = new Headers();			
			responseJson.put("headers", headers);
			responseJson.put("statusCode", 400);
			responseJson.put("exception", "No value for key in URL " + key );
		}
		return value;
	}

	protected JSONObject extractInputData(InputStream input, JSONObject responseJson) {
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
			Headers headers = new Headers();			
			responseJson.put("headers", headers);
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
		responseBody.put(getResources(), response);
		//required for apigatweway
		Headers headers = new Headers();			
		responseJson.put("headers", headers);
		responseJson.put("body", responseBody.toString());
		responseJson.put("statusCode", 200);
		return responseJson;
	}

	protected  abstract String getResources();

	private Gson createGson() {
		return new GsonBuilder().disableHtmlEscaping().create();
	}

	
	protected abstract List<Object> getAll(); 

	protected abstract List<Object>  getOne(Map<String, String> key); 


}