package ie.gmit.sw.data.mapper;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import ie.gmit.sw.data.model.Admin;
import ie.gmit.sw.data.model.Tournament;
import ie.gmit.sw.data.model.User;
import ie.gmit.sw.data.utily.DBObject;
import ie.gmit.sw.data.utily.DBObjectMapper;
/**
 * Maps user
 * @author eoinb
 *
 */
public class UserMapper implements DBObjectMapper {
	
	public static final String LAST_NAME = "lastName";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String FIRST_NAME = "firstName";
	public static final String EMAIL = "email";
	public static final String USERNAME = "username";
	public static final String NUMBER = "number";
	public static final String PASSWORD = "password";

	public void populateEnity(List<DBObject> userlist, Document returnData) {
		User user1 = new User();
		user1.setFirstName(returnData.getString(FIRST_NAME));
		user1.setLastName(returnData.getString(LAST_NAME));
		user1.setEmail(returnData.getString(EMAIL));
		user1.setUserName(returnData.getString(USERNAME));
		user1.setNumber(returnData.getInteger(NUMBER));
		user1.setPhoneNumber(returnData.getString(PHONE_NUMBER));
		user1.setPassword(returnData.getString(PASSWORD));
		userlist.add(user1);
	}

	@Override
	public Document formater(User reqUser) {
		Document a;
			a = new Document("_id", new ObjectId());
		a.append(NUMBER, reqUser.getNumber());
		a.append(USERNAME, reqUser.getUserName())
		.append(EMAIL, reqUser.getEmail())
		.append(FIRST_NAME, reqUser.getFirstName())
		.append(PHONE_NUMBER, reqUser.getPhoneNumber())
		.append(PASSWORD, reqUser.getPassword())
		.append(LAST_NAME, reqUser.getLastName());
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
