package ie.gmit.sw.data.dao;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import ie.gmit.sw.data.utily.DBConnection;
import ie.gmit.sw.data.utily.DBObject;
import ie.gmit.sw.data.utily.DBObjectMapper;

public class BaseDao {

	JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();

	private DBConnection connection = new DBConnection();

	private MongoClient mongoClient = connection.getDBConection();

	public BaseDao() {
		super();
	}

	protected void updateCollection(MongoCollection<Document> collection, BasicDBObject query,
			BasicDBObject updateDocument) {
		BasicDBObject updateObject = new BasicDBObject();
		updateObject.put("$set", updateDocument);

		collection.updateOne(query, updateObject);
	}

	protected MongoCollection<Document> getCollection(String collectionName) {
		MongoDatabase database = mongoClient.getDatabase("Names");
		MongoCollection<Document> collection = database.getCollection(collectionName);
		return collection;
	}

	protected void closeConnection() {
		connection.closeDB(mongoClient);
	}

	public void updateOneInMongoDB(MongoCollection<Document> collection, Bson filter, Bson updates) {
		UpdateResult updateResult = collection.updateOne(filter, updates);
		System.out.println(updateResult);
	}

	public void deleteOneInMongoDB(MongoCollection<Document> collection, Bson filter) {
		DeleteResult deleteResult = collection.deleteOne(filter);
		System.out.println(deleteResult);
	}

	public void getRows(MongoCollection<Document> collection, List<DBObject> userlist, DBObjectMapper usermap,
			Bson filter) {

		if (filter == null) {
			getMongoDB(userlist, usermap, collection);
		} else {
			getWithFillter(userlist, usermap,collection, filter);
		}
	}

	private void getMongoDB(List<DBObject> userlist, DBObjectMapper usermap, MongoCollection<Document> col) {
		Document returnData;
		FindIterable<Document> fi = col.find();
		MongoCursor<Document> cursor = fi.iterator();
		try {
			// return first one
			while (cursor.hasNext()) {
				returnData = cursor.next();
				usermap.populateEnity(userlist, returnData);

			}
		} finally {
			cursor.close();
		}
	}

	private void getWithFillter(List<DBObject> userlist, DBObjectMapper usermap, MongoCollection<Document> col, Bson filter) {
		Document doc = col.find(filter).first();
		System.out.println("This a check " + doc);
		if (doc!=null) {
			usermap.populateEnity(userlist, doc);			
		}

	}
	/**
	 * https://www.java67.com/2019/12/how-to-convert-map-to-list-in-java-8.html
	 */
	protected Bson createMongoDBFilter(Map<String, String> filters) {
		Collection<Bson> listOfFilters = filters.entrySet().stream().map(entry -> {
			Bson filtereq = null;
			if (entry.getKey().equalsIgnoreCase("number")) {
				filtereq = eq("number", Integer.valueOf(entry.getValue()));
			} else {
				filtereq = eq(entry.getKey(),entry.getValue());				
			}
			return filtereq;
		}).collect(Collectors.toCollection(ArrayList::new));
		if (listOfFilters.size()==1) {
			return listOfFilters.iterator().next();
		}
		Bson filter = and(listOfFilters);
		return filter;
	}

	public void deleteOne(String key) {
		// TODO Auto-generated method stub
		
	}


}