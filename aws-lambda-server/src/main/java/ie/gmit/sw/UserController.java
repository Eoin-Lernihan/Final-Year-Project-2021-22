package ie.gmit.sw;

import java.util.Map;

import org.bson.Document;

import com.amazonaws.services.lambda.runtime.Context;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
 public class UserController 
{	
	 private DBConnection connection = new DBConnection();

	public static void main(String[] args){

		UserController hello = new UserController();
		hello.handleRequest(null, null);
		
	}
	
	
	public String myHandler(Map<String,Object> input, Context context) 
	{
		System.out.println(input);
        return "Hello World";
    }
	
	public User handleRequest(Object o, Context context) {
		//checks to see if the lambda
        System.out.println("welcome to lambda function yeh.!!!");
        String returnData = "";
        try {
        	//Connects to my mongodb server
        	//
        	MongoClient mongoClient = connection.getDBConection();
        	
        	MongoDatabase database = mongoClient.getDatabase("Names");
        	
        	
        	MongoCollection<Document> col = database.getCollection("users");
        	FindIterable<Document> fi = col.find();
        	MongoCursor<Document> cursor = fi.iterator(); 
             try {
            	 //return first one
                 if ( cursor.hasNext()) {               
                     returnData = cursor.next().toJson();
                 }
             } finally {
                 cursor.close();
             }
         
            connection.closeDB(mongoClient);

        } catch (Exception e) {
            e.printStackTrace();
        }  
        User user1 = new User();
        user1.setFirstName("firstName");
        user1.setLastName("lasr");
        user1.setEmail("email");
		return user1 ;
	}


	



	
}
