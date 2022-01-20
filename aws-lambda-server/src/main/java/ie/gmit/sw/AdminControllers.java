package ie.gmit.sw;

import org.bson.Document;

import com.amazonaws.services.lambda.runtime.Context;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class AdminControllers {
	public Object handleRequest(Object o, Context context) {
		//checks to see if the lambda
        System.out.println("welcome to lambda function yeh.!!!");
        String returnData = "";
        try {
        	ConnectionString connectionString = new ConnectionString("mongodb+srv://user1:bV7hIKFPvWxZqlB8@serverlessinstance0.dgmiv.mongodb.net/ServerlessInstance0?retryWrites=true&w=majority&authSource=admin");
		MongoClientSettings settings = MongoClientSettings.builder()
        	        .applyConnectionString(connectionString)
        	        .serverApi(ServerApi.builder()
        	            .version(ServerApiVersion.V1)
        	            .build())
        	        .build();
        	MongoClient mongoClient = MongoClients.create(settings);
        	MongoDatabase database = mongoClient.getDatabase("Names");
        	MongoCollection<Document> col = database.getCollection("admins");
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
             //closes mongodb
            mongoClient.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnData;
	}
}
