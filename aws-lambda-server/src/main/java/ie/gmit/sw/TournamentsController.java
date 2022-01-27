package ie.gmit.sw;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;

public class TournamentsController {
	 TournamentsDao tournamentDao = new TournamentsDao();
	 
	 //testing
	public static void main(String[] args){
		TournamentsController tournamentsController = new TournamentsController();
		tournamentsController.handleRequest(null, null);
		
	}
	
	//testing
	public String myHandler(Map<String,Object> input, Context context) 
	{
		System.out.println(input);
      return "Hello World";
  }
	
	public List<DBObject> handleRequest(Object o, Context context) {
		
  	return tournamentDao.getAlltournament();
	}



	


}
