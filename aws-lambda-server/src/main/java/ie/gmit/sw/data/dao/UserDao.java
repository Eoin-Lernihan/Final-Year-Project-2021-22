package ie.gmit.sw.data.dao;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;

import ie.gmit.sw.controller.UserController;
import ie.gmit.sw.data.mapper.UserMapper;
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
public class UserDao extends BaseDao implements AllObjectsGet {
	private static final String USER_TABLE_NAME = "users";
	private DBObjectMapper usermap = new UserMapper();

	@Override
	public List<Object> getAll() {

		List<Object> users = getRowsForFilter(null);
		return users;
	}


	@Override
	public List<Object> getOne(String name) {

		Bson filter = eq("userName", name);
		List<Object> users = getRowsForFilter(filter);
		return users;
	}

	@Override
	public void addOne(Object request1) {
		User request = (User) request1;
		MongoCollection<Document> collection = getCollection(USER_TABLE_NAME);
		request.setNumber(new Random().nextInt(1000000) + 1);
		InsertOneResult a = collection.insertOne(usermap.formater(request));
	}


	
	@Override
	public void updateOne(Object request1) {
		User request = (User) request1;
		MongoCollection<Document> collection = getCollection(USER_TABLE_NAME);

		Bson filter = eq("number", request.getNumber());
		Bson updateOperation1 = set("userName", request.getUserName());
		Bson updateOperation2 = set("firstName", request.getFirstName());
		Bson updateOperation3 = set("emailName", request.getEmail());
		Bson updateOperation4 = set("lastName", request.getLastName());
		Bson updateOperation5 = set("phoneName", request.getPhoneNumber());
		Bson updates = combine(updateOperation1, updateOperation2, updateOperation3, updateOperation4,updateOperation5);
		updateOneInMongoDB(collection, filter, updates);

	}

	
	private List<Object> getRowsForFilter(Bson filter) {
		MongoCollection<Document> collection = getCollection(USER_TABLE_NAME);
		List<DBObject> userlist = new ArrayList<>();
		getRows(collection, userlist, usermap, filter);
		List<Object> users = userlist.stream().map(user -> (User) user).collect(Collectors.toList());
		return users;
	}

	
	/**
	 * Mainly used for local testing
	 * 
	 * @param args
	 */
	// testing
	public static void main(String[] args) {
		AllObjectsGet hello = new UserDao();
		hello.getAll().forEach(u -> System.out.println("getAll User"+u.toString()));
		hello.getOne("GamingDude").forEach(u -> System.out.println("getOne User"+u.toString()));
		User user = new User();
		user.setFirstName("first");
		user.setLastName("last");
		user.setEmail("email");
		user.setPhoneNumber("086-444444");
		user.setUserName("username");
		hello.addOne(user);
		List<Object> one = hello.getOne("username");
		List<User> users = one.stream().map(u -> (User) u ).collect(Collectors.toList());
		users.forEach(u -> System.out.println("getOne User"+u.toString()));
		//needto tests update
		User user1 = users.get(0);
		user1.setUserName("username1");
		hello.updateOne(user1);
		users = hello.getOne("username1").stream().map(u -> (User) u ).collect(Collectors.toList());
		users.forEach(u -> System.out.println("After Update getOne User"+u.toString()));
		hello.deleteOne(user1.getNumber());
		users = hello.getOne("username1").stream().map(u -> (User) u ).collect(Collectors.toList());
		users.forEach(u -> System.out.println("After delete getOne User"+u.toString()));
		


	}


	@Override
	public void deleteOne(Integer number) {
			MongoCollection<Document> collection = getCollection(USER_TABLE_NAME);

			Bson filter = eq("number", number);
			deleteOneInMongoDB(collection, filter );

	}
		
}
