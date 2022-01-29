package ie.gmit.sw;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;

public class AdminsController {
	 AdminsDao adminDao = new AdminsDao();
	 
	 //testing
	public static void main(String[] args){
		AdminsController adminsController = new AdminsController();
		adminsController.handleRequest(null, null);
		
	}
	
	//testing
	public String myHandler(Map<String,Object> input, Context context) 
	{
		System.out.println(input);
       return "Hello World";
   }
	
	public List<DBObject> handleRequest(Object o, Context context) {
	  List<DBObject> allAdmin = adminDao.getAllAdmin();
   	return allAdmin;
	}
	
	public JSONObject extractedToResponse() {
		JSONObject responseJson = new JSONObject();
		JSONObject responseBody = new JSONObject();
	//	List<User> allUser = new ArrayList<>();
		//User user = new User();
		//user.setEmail("email");
		//user.setFirstName("first");
		//user.setLastName("lastNam");
		//allUser.add(user);
		  List<DBObject> allAdmin = adminDao.getAllAdmin();

		responseBody.put("users", new Gson().toJson(allAdmin));
		responseJson.put("body", responseBody);
		responseJson.put("statusCode", 200);
		return responseJson;
	}



	



}
