package ie.gmit.sw.data.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;

import ie.gmit.sw.data.mapper.Mapper;
import ie.gmit.sw.data.mapper.UserMapper;
import ie.gmit.sw.data.model.User;
import ie.gmit.sw.data.utily.DBObject;
import ie.gmit.sw.data.utily.DBObjectMapper;

public class UserDao extends BaseDao implements AllObjectsGet {
	 private DBObjectMapper usermap = new UserMapper();
	 private DBObjectMapper usesrmap = new UserMapper();
	 private String finder = null;
	 private Mapper mapper = new Mapper();

	 @Override
	public List<Object> getAll() {
		String collectionName = "users";
		List<DBObject> userlist = new ArrayList<>();
        mapper.rowsMapper(collectionName, userlist, usermap, finder);  
        List<Object> users= userlist.stream().map(user -> (User) user).collect(Collectors.toList() );
		return  users;
	}
	
	@Override
	public List<Object> getOne(String name) {
		finder = name;
		String collectionName = "users";
		List<DBObject> userlist = new ArrayList<>();
        mapper.rowsMapper(collectionName, userlist, usermap, finder);  
        List<Object> users= userlist.stream().map(user -> (User) user).collect(Collectors.toList() );        
		return  users;
	}
	

	@Override
	public void addOne(User request) {
		MongoCollection<Document> collection = getCollection("user");		
		InsertOneResult a = collection.insertOne(usesrmap.formater(request));
	}


	@Override
	public void updateOne(User request) {
		MongoCollection<Document> collection = getCollection("user");
		BasicDBObject query = new BasicDBObject();
		// id here 
		query.put("_id", request.getNumber());

		BasicDBObject updateDocument = new BasicDBObject();
		updateDocument.put("_id", request.getNumber());
		updateDocument.put("userName", request.getUserName());
		updateDocument.put("firstName", request.getFirstName());
		updateDocument.put("emailName", request.getEmail());
		updateDocument.put("lastName", request.getLastName());

		updateCollection(collection, query, updateDocument);		
		
	}

}
