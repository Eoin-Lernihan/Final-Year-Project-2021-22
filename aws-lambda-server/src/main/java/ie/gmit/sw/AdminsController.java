package ie.gmit.sw;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;

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
		
   	return adminDao.getAllAdmin();
	}



	



}
