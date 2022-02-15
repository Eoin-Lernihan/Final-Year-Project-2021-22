package ie.gmit.sw.data.mapper;

import static com.mongodb.client.model.Filters.eq;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import ie.gmit.sw.data.utily.DBConnection;
import ie.gmit.sw.data.utily.DBObject;
import ie.gmit.sw.data.utily.DBObjectMapper;

public class Mapper {
	public void rowsMapper(String collectionName, List<DBObject> userlist, DBObjectMapper usermap, String fillter) {
		
		Document returnData;
        try {
        	//Connects to my mongodb server
       	  DBConnection connection = new DBConnection();

        	MongoClient mongoClient = connection.getDBConection();
        	
        	MongoDatabase database = mongoClient.getDatabase("Names");
        	
        	
			MongoCollection<Document> col = database.getCollection(collectionName);
			if(fillter == null ) {
				getMongoDB(userlist, usermap, col);
			}
			else {
				Document doc = getWithFillter(col, fillter);
				
			}
         
            connection.closeDB(mongoClient);

        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	private void getMongoDB(List<DBObject> userlist, DBObjectMapper usermap, MongoCollection<Document> col) {
		Document returnData;
		FindIterable<Document> fi = col.find();   	
		MongoCursor<Document> cursor = fi.iterator(); 
		 try {
			 //return first one
			 while(cursor.hasNext()) {               
		         returnData = cursor.next();
		         usermap.populateEnity(userlist, returnData);
		      
		     }
		 } finally {
		     cursor.close();
		 }
	}

	private Document getWithFillter(MongoCollection<Document> col, String fillter) {
		Document doc = col.find(eq("userName", "Funnyname")).first();        	
		System.out.println("This a check " + doc.toString());
		return doc;
	}
	

}
