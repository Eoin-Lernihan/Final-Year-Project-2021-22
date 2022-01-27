package ie.gmit.sw;

import java.util.List;

import org.bson.Document;

public class UserMapper implements DBObjectMapper {
	public void populateEnity(List<DBObject> userlist, Document returnData) {
		User user1 = new User();
		 user1.setFirstName(returnData.getString("firstName"));
		 user1.setLastName(returnData.getString("lastName"));
		 user1.setEmail(returnData.getString("email"));
		 userlist.add(user1);
	}
}
