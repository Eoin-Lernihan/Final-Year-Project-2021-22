package ie.gmit.sw.data.mapper;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import ie.gmit.sw.data.model.Admin;
import ie.gmit.sw.data.model.Tournament;
import ie.gmit.sw.data.model.User;
import ie.gmit.sw.data.utily.DBObject;
import ie.gmit.sw.data.utily.DBObjectMapper;

public class AdminsMapper implements DBObjectMapper {
	public static final String NUMBER = "number";
	public static final String GAMES_RUNNING = "gamesRunning";
	public static final String COMPANY_PHONE_NUMBER = "phoneNumber";
	public static final String COMPANY_NAME = "name";
	public static final String COMPANY_EMAIL = "mail";
	public static final String COMPANY_USERNAME = "username";
	public static final String COMPANY_PASSWORD = "password";
	public static final String COMPANY_GAMES_RUNNING = "gamesRunning";

	public void populateEnity(List<DBObject> adminList, Document returnData) {
		Admin admin = new Admin();
		admin.setUsername(returnData.getString(COMPANY_USERNAME));
		admin.setName(returnData.getString(COMPANY_NAME));
		admin.setEmail(returnData.getString(COMPANY_EMAIL));
		admin.setPhoneNumber(returnData.getString(COMPANY_PHONE_NUMBER));
		admin.setPassword(returnData.getString(COMPANY_PASSWORD));
		admin.setGamesRunning(returnData.getList(GAMES_RUNNING, String.class));
		admin.setNumber(returnData.getInteger(NUMBER));
		adminList.add(admin);
	}

	@Override
	
	public Document formater(Admin reqAdmin) {
		Document a;
		a = new Document("_id", new ObjectId());
	a.append(NUMBER, reqAdmin.getNumber());
	a.append(COMPANY_USERNAME, reqAdmin.getUsername())
	.append(COMPANY_EMAIL, reqAdmin.getEmail())
	.append(COMPANY_NAME, reqAdmin.getName())
	.append(COMPANY_PHONE_NUMBER, reqAdmin.getPhoneNumber())
	.append(COMPANY_PASSWORD, reqAdmin.getPassword())
	.append(GAMES_RUNNING, reqAdmin.getGamesRunning());
	return a;
	}

	@Override
	public Document formater(User reqUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document formater(Tournament reqUser) {
		// TODO Auto-generated method stub
		return null;
	}
	


	
}
