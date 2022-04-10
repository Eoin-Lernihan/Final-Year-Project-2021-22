package ie.gmit.sw.data.dao;

import static com.mongodb.client.model.Filters.*;
import static org.hamcrest.CoreMatchers.not;

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

import ie.gmit.sw.data.mapper.TournamentsMapper;
import ie.gmit.sw.data.utily.DBConnection;
import ie.gmit.sw.data.utily.DBObject;
import ie.gmit.sw.data.utily.DBObjectMapper;
/**
 *Common Methods for 
 * 1. Has static connection to Mongodb for lambda performance
 * 1. Constructing mongodb filters
 * 2. Looping through cursor of retruend data"
 * 3. Handles the Mongodb interfacing for delete, updates and inserts
}
 * @author eoinb
 *
 */
public class BaseDao {

	JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();

	private DBConnection connection = new DBConnection();

	private MongoClient mongoClient = connection.getDBConection();

	public BaseDao() {
		super();
	}
/**
 * 
 * @param collection
 * @param query
 * @param updateDocument
 */
	protected void updateCollection(MongoCollection<Document> collection, BasicDBObject query,
			BasicDBObject updateDocument) {
		BasicDBObject updateObject = new BasicDBObject();
		updateObject.put("$set", updateDocument);

		collection.updateOne(query, updateObject);
	}
/**
 * 
 * @param collectionName
 * @return
 */
	protected MongoCollection<Document> getCollection(String collectionName) {
		MongoDatabase database = mongoClient.getDatabase("Names");
		MongoCollection<Document> collection = database.getCollection(collectionName);
		return collection;
	}
/**
 * Closes the Connection
 */
	protected void closeConnection() {
		connection.closeDB(mongoClient);
	}
/**
 * Updates one in the mongo database
 * @param collection
 * @param filter
 * @param updates
 */
	public void updateOneInMongoDB(MongoCollection<Document> collection, Bson filter, Bson updates) {
		UpdateResult updateResult = collection.updateOne(filter, updates);
		System.out.println(updateResult);
	}
/**
 * Deletes one in the mongo database
 * @param collection
 * @param filter
 */
	public void deleteOneInMongoDB(MongoCollection<Document> collection, Bson filter) {
		DeleteResult deleteResult = collection.deleteOne(filter);
		System.out.println(deleteResult);
	}
/**
 * 
 * @param collection
 * @param userlist
 * @param usermap
 * @param filter
 */
	public void getRows(MongoCollection<Document> collection, List<DBObject> userlist, DBObjectMapper usermap,
			Bson filter) {

		if (filter == null) {
			getMongoDB(userlist, usermap, collection);
		} else {
			getWithFillter(userlist, usermap, collection, filter);
		}
	}
/**
 * gets mongo database
 * @param userlist
 * @param usermap
 * @param col
 */
	private void getMongoDB(List<DBObject> userlist, DBObjectMapper usermap, MongoCollection<Document> col) {
		Document returnData;
		FindIterable<Document> fi = col.find();
		extractObjectFormMongoCollection(userlist, usermap, fi);
	}
/**
 * 
 * @param userlist
 * @param usermap
 * @param fi
 */
	private void extractObjectFormMongoCollection(List<DBObject> userlist, DBObjectMapper usermap,
			FindIterable<Document> fi) {
		Document returnData;
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
/**
 * Get an object with fillters
 * @param userlist
 * @param usermap
 * @param col
 * @param filter
 */
	private void getWithFillter(List<DBObject> userlist, DBObjectMapper usermap, MongoCollection<Document> col,
			Bson filter) {
		FindIterable<Document> finder = col.find(filter);
		extractObjectFormMongoCollection(userlist, usermap, finder);
		//Document doc = finder.first();
		//System.out.println("This a check " + doc);
		//if (doc != null) {
			//usermap.populateEnity(userlist, doc);
		//}

	}

	/**
	 * https://www.java67.com/2019/12/how-to-convert-map-to-list-in-java-8.html
	 * 1 means inculed other numbers means not included
	 * @param isATourament 
	 */
	protected Bson createMongoDBFilter(Map<String, String> filters, boolean isATourament) {

		boolean excludeTouramentWherePlayerIsIn = true;
		if (filters.containsKey("inGame")) {
			if (filters.get("inGame").equals("1")) {
				excludeTouramentWherePlayerIsIn = false;
			}
			filters.remove("inGame");
		}
		if (filters.containsKey("touramentId")) {
			filters.remove("touramentId");
		}
		/*
		 * To prevent caching on the browser
		 */
		if (filters.containsKey("dateAt")) {
			filters.remove("dateAt");
		}
		
		//for stream as variable need to ne finalk
		final boolean  excludePlayer = excludeTouramentWherePlayerIsIn;

		Collection<Bson> listOfFilters = filters.entrySet().stream().map(entry -> {
			Bson filtereq = null;
			if (entry.getKey().equalsIgnoreCase("number")) {
				filtereq = eq("number", Integer.valueOf(entry.getValue()));
			} else if ( (entry.getKey().equalsIgnoreCase("userName")) && (isATourament) ) {
				if (excludePlayer) {
					filtereq = ne("players", entry.getValue()) ;
				} else {
					filtereq = eq("players", entry.getValue());
				}
			} else {
				filtereq = eq(entry.getKey(), entry.getValue());
			}
			return filtereq;
		}).collect(Collectors.toCollection(ArrayList::new));

		if (listOfFilters.size() == 1) {
			return listOfFilters.iterator().next();
		}
		Bson filter = and(listOfFilters);
		return filter;
	}

	public void deleteOne(String key) {
		// TODO Auto-generated method stub

	}

}