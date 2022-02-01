package ie.gmit.sw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;

public class UserDao {
	 private DBObjectMapper usermap = new UserMapper();
	 private DBConnection connection = new DBConnection();
	 private UserMapper usesrmap = new UserMapper();

	 private Mapper mapper = new Mapper();
	public List<User> getAllUser() {
		String collectionName = "users";
		List<DBObject> userlist = new ArrayList<>();
        System.out.println("welcome to lambda function yeh.!!!");
        mapper.rowsMapper(collectionName, userlist, usermap);  
        List<User> users= userlist.stream().map(user -> (User) user).collect(Collectors.toList() );
		return  users;
	}
	
	public void addUser(String firstName, String lastName, String email, String userName, String number) {
		MongoClient mongoClient = connection.getDBConection();
		
		MongoDatabase database = mongoClient.getDatabase("Names");
		
		
		MongoCollection<Document> col = database.getCollection("user");
		
		InsertOneResult a = col.insertOne(usesrmap.formater(userName, email, firstName, lastName, number));
		
		System.out.println("Checker " + a);
	}
	
	





}
