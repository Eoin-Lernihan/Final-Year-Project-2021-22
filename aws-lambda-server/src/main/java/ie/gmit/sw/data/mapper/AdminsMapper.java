package ie.gmit.sw.data.mapper;

import java.util.List;

import org.bson.Document;

import ie.gmit.sw.data.model.Admins;
import ie.gmit.sw.data.utily.DBObject;
import ie.gmit.sw.data.utily.DBObjectMapper;

public class AdminsMapper implements DBObjectMapper {
	public void populateEnity(List<DBObject> adminList, Document returnData) {
		Admins admin = new Admins();
		admin.setName(returnData.getString("companyUserName"));
		admin.setCompanyName(returnData.getString("companyName"));
		admin.setEmail(returnData.getString("companyEmail"));
		admin.setNumber(returnData.getString("companyNumber"));
		adminList.add(admin);
	}
}
