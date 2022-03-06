package ie.gmit.sw.data.dao;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;

import ie.gmit.sw.controller.UserController;
import ie.gmit.sw.data.mapper.AdminsMapper;
import ie.gmit.sw.data.mapper.UserMapper;
import ie.gmit.sw.data.model.Admin;
import ie.gmit.sw.data.model.Tournament;
import ie.gmit.sw.data.model.User;
import ie.gmit.sw.data.utily.DBObject;
import ie.gmit.sw.data.utily.DBObjectMapper;

/**
 * https://stackoverflow.com/questions/44878605/mongodb-basicdbobject-vs-document-in-java
 * https://github.com/mongodb-developer/java-quick-start/blob/master/src/main/java/com/mongodb/quickstart/Update.java
 * 
 * @author eoinb
 *
 */
public class AdminDao extends BaseDao implements AllObjectsGet {
	private static final String ADMIN_TABLE_NAME = "admins";
	private DBObjectMapper adminMap = new AdminsMapper();

	@Override
	public List<Object> getAll() {

		List<Object> admins = getRowsForFilter(null);
		return admins;
	}


	@Override
	public List<Object> getOne(String name) {

		Bson filter = eq("companyUserName", name);
		List<Object> admins = getRowsForFilter(filter);
		return admins;
	}

	@Override
	public void addOne(Object request1) {
		Admin request = (Admin) request1;
		MongoCollection<Document> collection = getCollection(ADMIN_TABLE_NAME);

		InsertOneResult a = collection.insertOne(adminMap.formater(request));
	}


	
	@Override
	public void updateOne(Object request1) {
		Admin request = (Admin) request1;
		MongoCollection<Document> collection = getCollection(ADMIN_TABLE_NAME);

		Bson filter = eq("number", request.getNumber());
		Bson updateOperation1 = set("companyUserName", request.getCompanyUserName());
		Bson updateOperation2 = set("companyName", request.getCompanyName());
		Bson updateOperation3 = set("companyEmail", request.getCompanyEmail());
		Bson updateOperation4 = set("companyNumber", request.getCompanyNumber());
		Bson updateOperation5 = set("gamesRunning", request.getGamesRunning());
		Bson updates = combine(updateOperation1, updateOperation2, updateOperation3, updateOperation4,updateOperation5);
		updateOneInMongoDB(collection, filter, updates);

	}

	
	private List<Object> getRowsForFilter(Bson filter) {
		MongoCollection<Document> collection = getCollection(ADMIN_TABLE_NAME);
		List<DBObject> adminlist = new ArrayList<>();
		getRows(collection, adminlist, adminMap, filter);
		List<Object> admins = adminlist.stream().map(admin -> (Admin) admin).collect(Collectors.toList());
		return admins;
	}

	
	/**
	 * Mainly used for local testing
	 * 
	 * @param args
	 */
	// testing
	public static void main(String[] args) {
		Integer seedNumber = getRandomNumber();
		AdminDao adminDao = new AdminDao();
		
//		createAdmin(seedNumber, adminDao);
//		createAdmin(seedNumber+1, adminDao);
//		createAdmin(seedNumber+2, adminDao);
//		createAdmin(seedNumber+3, adminDao);
//		
		
		List<Admin> all = adminDao.getAll().stream().map(a -> (Admin) a ).collect(Collectors.toList());
		all.forEach(a -> System.out.println("getAll Admins"+a.toString()));
		Admin first  =  all.get(0);
		String searchCompanyName = first.getCompanyUserName(); 
		adminDao.getOne(searchCompanyName).forEach(u -> System.out.println("getOne Admins"+u.toString()));
	
		List<Object> one = adminDao.getOne(searchCompanyName);
		List<Admin> admins = one.stream().map(a -> (Admin) a ).collect(Collectors.toList());
		admins.forEach(u -> System.out.println("getOne admins"+u.toString()));
		//needto tests update
		Admin admins1 = admins.get(0);
		admins1.setCompanyName("companyName2");
		adminDao.updateOne(admins1);
		admins = adminDao.getOne("CompanyUserName"+seedNumber.toString()).stream().map(a -> (Admin) a ).collect(Collectors.toList());
		admins.forEach(u -> System.out.println("After Update getOne Admin"+u.toString()));
	//	adminDao.deleteOne(admins1.getNumber());
	//	admins = adminDao.getOne("companyName2").stream().map(a -> (Admin) a ).collect(Collectors.toList());
		admins.forEach(a -> System.out.println("After delete getOne Admin"+a.toString()));
		


	}


	@Override
	public void deleteOne(Integer number) {
			MongoCollection<Document> collection = getCollection(ADMIN_TABLE_NAME);

			Bson filter = eq("number", number);
			deleteOneInMongoDB(collection, filter );

	}
	
	private static Admin  createAdmin( Integer seedNumber, AdminDao adminDao) {
		Admin admin = new Admin();

		admin.setNumber(seedNumber );
		admin.setCompanyEmail("email"+seedNumber.toString());
		admin.setCompanyName("name"+seedNumber.toString());
		admin.setCompanyUserName("CompanyUserName"+seedNumber.toString());
		admin.setCompanyNumber("description"+seedNumber.toString());
		List<String> gamesruning = new ArrayList<>();
		admin.setGamesRunning(gamesruning);
		adminDao.addOne(admin);
		return admin;
	}
	
	private static int getRandomNumber() {
		int min= 1000;
		int max= 1000000;
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
}
