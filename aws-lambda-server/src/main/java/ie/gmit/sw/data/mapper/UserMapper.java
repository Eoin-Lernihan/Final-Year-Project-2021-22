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
		userlist.add(user1);
	}

	@Override
	public Document formater(User reqUser) {
		Document a;
		if (reqUser.getNumber() == null) {
			a = new Document("_id", new ObjectId());
		} else {
			a = new Document("_id", reqUser.getNumber());
		}

		a.append("userName", reqUser.getUserName()).append("email", reqUser.getEmail())
				.append("firstName", reqUser.getFirstName()).append("lastName", reqUser.getLastName());
		return a;
	}

}
