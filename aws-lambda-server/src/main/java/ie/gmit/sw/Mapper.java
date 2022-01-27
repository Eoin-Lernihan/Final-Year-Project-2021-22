package ie.gmit.sw;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Mapper {
	 private DBConnection connection = new DBConnection();
	public void rowsMapper(String collectionName, List<DBObject> userlist, DBObjectMapper usermap) {
		Document returnData;
        try {
        	//Connects to my mongodb server
        	MongoClient mongoClient = connection.getDBConection();
        	
        	MongoDatabase database = mongoClient.getDatabase("Names");
        	
        	
			MongoCollection<Document> col = database.getCollection(collectionName);
        	
        	FindIterable<Document> fi = col.find();
        	MongoCursor<Document> cursor = fi.iterator(); 
             try {
            	 //return first one
                 if ( cursor.hasNext()) {               
                     returnData = cursor.next();
                     usermap.populateEnity(userlist, returnData);
                 }
             } finally {
                 cursor.close();
             }
         
            connection.closeDB(mongoClient);

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
