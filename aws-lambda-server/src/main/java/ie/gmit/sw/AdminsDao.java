package ie.gmit.sw;

import java.util.ArrayList;
import java.util.List;

public class AdminsDao {
	 private DBObjectMapper adminMap = new UserMapper();

	 private Mapper mapper = new Mapper();
	public List<DBObject> getAllAdmin() {
		String collectionName = "users";
		List<DBObject> userlist = new ArrayList<>();
       System.out.println("welcome to lambda function yeh.!!!");
       mapper.rowsMapper(collectionName, userlist, adminMap);  
       
		return userlist;
	}




}
