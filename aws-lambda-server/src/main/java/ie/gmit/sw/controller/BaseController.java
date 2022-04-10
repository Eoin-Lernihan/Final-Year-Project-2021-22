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

import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * Common Methods for 
 	1. Extracting query and path params from input"
 	2. Interfacing with Dao"
 	3. Constructing Response code, header etc
 * @author eoinb
 *
 */
public abstract class BaseController implements RequestStreamHandler {

	public BaseController() {
		super();
	}
	/**
	 * extracts a filter from the event defines by the queryParameterName and insert into the a filter map with a key of dbFieldName
	 * @param event
	 * @param responseJson
	 * @param queryParameterName
	 * @param dbFieldName
	 * @return
	 */
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
	/**
	 * Using the event in the input it extracts the filters and query the Database , returns the appropirate row (or error)  in responseJson
	 * @param input
	 * @param output
	 * @param responseJson
	 * @param queryParameterName
	 * @param dbFieldName
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	protected void findOneValueinDAO(InputStream input, OutputStream output, JSONObject responseJson, String queryParameterName, String dbFieldName)
		throws UnsupportedEncodingException, IOException {
		Map<String, String> filters = getQueryAndPathParams(input, responseJson, queryParameterName, dbFieldName);
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
	/**
	 * deletes a row as specified in the input by extract the query/path parameter , return any erros in theresponseJson 
	 * @param input
	 * @param output
	 * @param responseJson
	 * @param queryParameterName
	 * @param dbFieldName
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	protected void deleteOneInDao(InputStream input, OutputStream output, JSONObject responseJson, String queryParameterName, String dbFieldName)
			throws UnsupportedEncodingException, IOException {
			Map<String, String> filters = getQueryAndPathParams(input, responseJson, queryParameterName, dbFieldName);
			deleteOne(filters);
			Object  response = null;
			
			createJsonResponse(output, response, responseJson);
		}
	/**
	 * General method for extracting a query/path parameters from the input stream, any errors return in responseJson
	 * @param input 
	 * @param responseJson
	 * @param queryParameterName
	 * @param dbFieldName
	 * @return
	 */
	protected Map<String, String> getQueryAndPathParams(InputStream input, JSONObject responseJson,
			String queryParameterName, String dbFieldName) {
		JSONObject event = extractInputData(input, responseJson);

		Map<String, String> filters = extractFilters(event, responseJson, queryParameterName, dbFieldName);
		
		return filters;
	}

	/**
	 * Gernal method for extract the body from the input request as a string
	 * @param event
	 * @param responseJson
	 * @return
	 */
	protected String setupExtractInputPayload(JSONObject event, JSONObject responseJson) {
	
		return (String) event.get("body");
	}
	/**
	 * Extracts from the Input stream a specific value for a  query/path Param
	 * @param event
	 * @param key
	 * @param responseJson
	 * @return
	 */
	private String setupExtractInputDataQueryParam( JSONObject event, String key, JSONObject responseJson ) {
		if (responseJson != null) {
			return null;
		}
		return extractPramsFormPathOrQuarry(key, event);
	
	}
	/**
	 * 	 Extracts from the Input stream a specific value for a  query/path Param
	 * @param key
	 * @param event
	 * @return
	 */
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
	/**
	 * convert the input stream into a Json object
	 * @param input
	 * @param responseJson
	 * @return
	 */
	protected JSONObject extractInputData(InputStream input, JSONObject responseJson) {
		JSONObject event = null;
		JSONParser parser = new JSONParser();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		try {
			event = (JSONObject) parser.parse(reader);
		} catch (IOException | ParseException ex) {
			// setup response to return failure
			if (responseJson == null) {
				responseJson = new JSONObject();
			}
			Headers headers = new Headers();			
			responseJson.put("headers", headers);
			responseJson.put("statusCode", 400);
			responseJson.put("exception", ex);
		}
		System.out.println("event " + event);
		return event;
	}
	/**
	 * creates a Json response object given the return data and writes it to the output Stream.
	 * @param output
	 * @param all
	 * @param responseJson
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	protected void createJsonResponse(OutputStream output, Object all, JSONObject responseJson)
			throws UnsupportedEncodingException, IOException {
				responseJson = getResposeAsJson(all, responseJson);
				createOutPutStream(output, responseJson);
			}
	/**
	 * creates a Json response object given the return data and writes it to the output Stream. 
	 * @param output
	 * @param responseJson
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
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
			responseJson.put("headers", new Headers());
			return responseJson;
		}
		responseJson = new JSONObject();
		JSONObject responseBody = new JSONObject();
		responseBody.put(getResources(), response);
		//required for apigatweway
		successResponseProxy(responseJson, responseBody);
		return responseJson;
	}
	
	
	/**
	 * Creates a success response object 
	 * @param responseJson
	 * @param responseBody
	 */
	private void successResponseProxy(JSONObject responseJson, JSONObject responseBody) {
		responseJson.put("headers", new Headers());
		responseJson.put("body", responseBody.toString());
		responseJson.put("statusCode", 200);
	}

	protected  abstract String getResources();

	
	protected abstract List<Object> getAll(); 

	protected abstract List<Object>  getOne(Map<String, String> key); 
	
	protected abstract void  deleteOne(Map<String, String> key); 

}