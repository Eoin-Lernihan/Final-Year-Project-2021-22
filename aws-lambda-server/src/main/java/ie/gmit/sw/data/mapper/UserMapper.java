package ie.gmit.sw.data.mapper;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import ie.gmit.sw.data.model.Admin;
import ie.gmit.sw.data.model.Tournament;
import ie.gmit.sw.data.model.User;
import ie.gmit.sw.data.utily.DBObject;
import ie.gmit.sw.data.utily.DBObjectMapper;

public class UserMapper implements DBObjectMapper {
	
	public void populateEnity(List<DBObject> userlist, Document returnData) {
		User user1 = new User();
		user1.setFirstName(returnData.getString("firstName"));
		user1.setLastName(returnData.getString("lastName"));
		user1.setEmail(returnData.getString("email"));
		user1.setUserName(returnData.getString("userName"));
		user1.setNumber(returnData.getInteger("number"));
		user1.setPhoneNumber(returnData.getString("phoneNumber"));
		userlist.add(user1);
	}

	@Override
	public Document formater(User reqUser) {
		Document a;
			a = new Document("_id", new ObjectId());
		a.append("number", reqUser.getNumber());
		a.append("userName", reqUser.getUserName())
		.append("email", reqUser.getEmail())
		.append("firstName", reqUser.getFirstName())
		.append("phoneNumber", reqUser.getPhoneNumber())
		.append("lastName", reqUser.getLastName());
		return a;
	}

	@Override
	public Document formater(Admin reqAdmin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document formater(Tournament reqUser) {
		// TODO Auto-generated method stub
		return null;
	}

}
