package ie.gmit.sw.data.mapper;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import ie.gmit.sw.data.model.User;
import ie.gmit.sw.data.utily.DBObject;
import ie.gmit.sw.data.utily.DBObjectMapper;

public class UserMapper implements DBObjectMapper {
	public void populateEnity(List<DBObject> userlist, Document returnData) {
		User user1 = new User();
		 user1.setFirstName(returnData.getString("firstName"));
		 user1.setLastName(returnData.getString("lastName"));
		 user1.setEmail(returnData.getString("email"));
		userlist.add(user1 );
	}
	
	public static Document formater(String userName, String email, String firstName, String lastName, String number) {
    	return new Document("_id", new ObjectId())
	  	.append("userName", userName)
	  	.append("email", email)
	  	.append("firstName", firstName)
	  	.append("lastName", lastName)
	  	.append("number", number);

    } 
}
