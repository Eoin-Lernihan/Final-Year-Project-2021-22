package ie.gmit.sw;

import java.util.List;

import org.bson.Document;

public class AdminsMapper implements DBObjectMapper {
	public void populateEnity(List<DBObject> adminList, Document returnData) {
		Admins admin = new Admins();
		admin.setFirstName(returnData.getString("firstName"));
		admin.setLastName(returnData.getString("lastName"));
		admin.setEmail(returnData.getString("email"));
		adminList.add(admin);
	}
}
