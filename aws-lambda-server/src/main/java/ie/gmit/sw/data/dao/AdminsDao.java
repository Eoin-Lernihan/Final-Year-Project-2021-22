package ie.gmit.sw.data.dao;

import java.util.ArrayList;
import java.util.List;

import ie.gmit.sw.data.mapper.Mapper;
import ie.gmit.sw.data.mapper.UserMapper;
import ie.gmit.sw.data.utily.DBObject;
import ie.gmit.sw.data.utily.DBObjectMapper;

public class AdminsDao {
	 private DBObjectMapper adminMap = new UserMapper();
	 private String finder = null;
	 private Mapper mapper = new Mapper();
	public List<DBObject> getAllAdmin() {
		String collectionName = "admins";
		List<DBObject> adminList = new ArrayList<>();
       System.out.println("welcome to lambda function yeh.!!!");
       mapper.rowsMapper(collectionName, adminList, adminMap, finder);  
       
		return adminList;
	}




}
