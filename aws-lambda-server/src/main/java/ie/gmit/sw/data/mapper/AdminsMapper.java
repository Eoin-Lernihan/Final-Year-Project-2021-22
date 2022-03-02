package ie.gmit.sw.data.mapper;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import ie.gmit.sw.data.model.Admin;
import ie.gmit.sw.data.model.Tournaments;
import ie.gmit.sw.data.model.User;
import ie.gmit.sw.data.utily.DBObject;
import ie.gmit.sw.data.utily.DBObjectMapper;

public class AdminsMapper implements DBObjectMapper {
	public void populateEnity(List<DBObject> adminList, Document returnData) {
		Admin admin = new Admin();
		admin.setCompanyUserName(returnData.getString("companyUserName"));
		admin.setCompanyName(returnData.getString("companyName"));
		admin.setCompanyEmail(returnData.getString("companyEmail"));
		admin.setCompanyNumber(returnData.getString("companyNumber"));
		admin.setGamesRunning(returnData.getString("gamesRunning"));
		admin.setNumber(returnData.getInteger("number"));
		adminList.add(admin);
	}

	@Override
	
	public Document formater(Admin reqAdmin) {
		Document a;
		a = new Document("_id", new ObjectId());
	a.append("number", reqAdmin.getNumber());
	a.append("companyUserName", reqAdmin.getCompanyUserName())
	.append("companyEmail", reqAdmin.getCompanyEmail())
	.append("companyName", reqAdmin.getCompanyName())
	.append("companyNumber", reqAdmin.getCompanyNumber())
	.append("gamesRunning", reqAdmin.getGamesRunning());
	return a;
	}

	@Override
	public Document formater(User reqUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document formater(Tournaments reqUser) {
		// TODO Auto-generated method stub
		return null;
	}
	


	
}
