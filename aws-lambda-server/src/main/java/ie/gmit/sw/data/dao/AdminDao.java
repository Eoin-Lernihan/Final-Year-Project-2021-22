package ie.gmit.sw.data.dao;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;

import ie.gmit.sw.data.mapper.AdminsMapper;
import ie.gmit.sw.data.model.Admin;
import ie.gmit.sw.data.utily.DBObject;
import ie.gmit.sw.data.utily.DBObjectMapper;

/**
 * Specific Methods for orchestraing
 *  interaction with DB for Admin
 * https://stackoverflow.com/questions/44878605/mongodb-basicdbobject-vs-document-in-java
 * https://github.com/mongodb-developer/java-quick-start/blob/master/src/main/java/com/mongodb/quickstart/Update.java
 * 
 * @author eoinb
 *
 */
public class AdminDao extends BaseDao implements DaoCommonInterface {
	private static final String ADMIN_TABLE_NAME = "admins";
	private DBObjectMapper adminMap = new AdminsMapper();
	
	@Override
	/**
	 * method to get all User rows in the Database 
	 */
	public List<Object> getAll() {

		List<Object> admins = getRowsForFilter(null);
		return admins;
	}

	
	@Override
	/**
	 * Returns a list of Admin from the database normally used for getting one,  
	 */
	public List<Object> getOne(Map<String, String> filters) {

		Bson filter = createMongoDBFilter(filters, false);
		List<Object> admins = getRowsForFilter(filter);
		return admins;
	}

	@Override
	/**
	 * Adds one Admin to the database
	 */
	public void addOne(Object request1) {
		Admin request = (Admin) request1;
		MongoCollection<Document> collection = getCollection(ADMIN_TABLE_NAME);

		collection.insertOne(adminMap.formater(request));
	}



	@Override
	/**
	 * Updates an existing Admin the database 
	 */
	public void updateOne(Object request1, Map<String, String> filters) {
		Admin request = (Admin) request1;
		MongoCollection<Document> collection = getCollection(ADMIN_TABLE_NAME);

		Bson filter = createMongoDBFilter(filters, false);
		
		List<Bson> listOfUpdateOps = new ArrayList<>(); 
		listOfUpdateOps.add(set(AdminsMapper.COMPANY_USERNAME, request.getUsername()));
		listOfUpdateOps.add(set(AdminsMapper.COMPANY_NAME, request.getName()));
		listOfUpdateOps.add(set(AdminsMapper.COMPANY_EMAIL, request.getEmail()));
		listOfUpdateOps.add(set(AdminsMapper.COMPANY_PHONE_NUMBER, request.getPhoneNumber()));
		listOfUpdateOps.add(set(AdminsMapper.COMPANY_PASSWORD, request.getPassword()));
		listOfUpdateOps.add(set(AdminsMapper.COMPANY_GAMES_RUNNING, request.getGamesRunning()));
		Bson updates = combine(listOfUpdateOps);
		updateOneInMongoDB(collection, filter, updates);

	}

	/**
	 * gets all Rows For Filter
	 * @param filter
	 * @return
	 */
	private List<Object> getRowsForFilter(Bson filter) {
		MongoCollection<Document> collection = getCollection(ADMIN_TABLE_NAME);
		List<DBObject> adminlist = new ArrayList<>();
		getRows(collection, adminlist, adminMap, filter);
		List<Object> admins = adminlist.stream().map(admin -> (Admin) admin).collect(Collectors.toList());
		return admins;
	}

	


	@Override
	/**
	 * Deletes one Admin from the Database
	 * @param number
	 */
	public void deleteOne(Integer number) {
			MongoCollection<Document> collection = getCollection(ADMIN_TABLE_NAME);

			Bson filter = eq("number", number);
			deleteOneInMongoDB(collection, filter );

	}
	
	



	
}
