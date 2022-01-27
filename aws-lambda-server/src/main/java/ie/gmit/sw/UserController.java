package ie.gmit.sw;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
 public class UserController 
{	
	 UserDao userDao = new UserDao();
	 
	 //testing
	public static void main(String[] args){
		UserController hello = new UserController();
		hello.handleRequest(null, null);
		
	}
	
	//testing
	public String myHandler(Map<String,Object> input, Context context) 
	{
		System.out.println(input);
        return "Hello World";
    }
	
	public List<DBObject> handleRequest(Object o, Context context) {
		
    	return userDao.getAllUser();
	}



	



	
}
