package ie.gmit.sw.data.dao;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ie.gmit.sw.data.utily.DBConnection;

public class BaseDao {

	private DBConnection connection = new DBConnection();

	public BaseDao() {
		super();
	}

	protected void updateCollection(MongoCollection<Document> collection, BasicDBObject query, BasicDBObject updateDocument) {
		BasicDBObject updateObject = new BasicDBObject();
		updateObject.put("$set", updateDocument);
	
		collection.updateOne(query, updateObject);
	}

	protected MongoCollection<Document> getCollection(String collectionName) {
		MongoClient mongoClient = connection.getDBConection();	
		MongoDatabase database = mongoClient.getDatabase("Names");
		MongoCollection<Document> collection = database.getCollection(collectionName);
		return collection;
	}

}