package ie.gmit.sw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import static com.mongodb.client.model.Filters.*;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class UserDao {
	 private DBObjectMapper usermap = new UserMapper();
	 private DBConnection connection = new DBConnection();
	 private UserMapper usesrmap = new UserMapper();
	 private String finder = null;
	 private Mapper mapper = new Mapper();

	 public List<User> getAllUser() {
		String collectionName = "users";
		List<DBObject> userlist = new ArrayList<>();
        System.out.println("welcome to lambda function yeh.!!!");
        mapper.rowsMapper(collectionName, userlist, usermap, finder);  
        List<User> users= userlist.stream().map(user -> (User) user).collect(Collectors.toList() );
		return  users;
	}
	
	public List<User> getAUser(String name) {
		finder = name;
		String collectionName = "users";
		List<DBObject> userlist = new ArrayList<>();
        System.out.println("welcome to lambda function yeh.!!!");
        mapper.rowsMapper(collectionName, userlist, usermap, finder);  
        List<User> users= userlist.stream().map(user -> (User) user).collect(Collectors.toList() );
		return  users;
	}
	
	public void addUser(String firstName, String lastName, String email, String userName, String number) {
		MongoClient mongoClient = connection.getDBConection();
		
		MongoDatabase database = mongoClient.getDatabase("Names");
		
		MongoCollection<Document> collection = database.getCollection("user");
		
		InsertOneResult a = collection.insertOne(usesrmap.formater(userName, email, firstName, lastName, number));
		
		System.out.println("Checker " + a);
	}
	
	public void updateUser(String firstName, String lastName, String email, String userName, String number, String newUserName, String newNumber) {
		MongoClient mongoClient = connection.getDBConection();	
		MongoDatabase database = mongoClient.getDatabase("Names");
		MongoCollection<Document> collection = database.getCollection("user");
		collection.updateOne(eq("userName", firstName),combine(set("userName", newUserName), set("number", newNumber)));

		
	}





}
