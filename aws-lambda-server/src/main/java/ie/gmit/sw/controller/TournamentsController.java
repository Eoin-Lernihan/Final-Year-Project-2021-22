package ie.gmit.sw.controller;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;

import ie.gmit.sw.data.dao.TournamentsDao;
import ie.gmit.sw.data.utily.DBObject;

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
		  List<DBObject> allTourment = tournamentDao.getAlltournament();
  	return allTourment;
	}



	


}
