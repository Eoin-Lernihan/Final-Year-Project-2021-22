package ie.gmit.sw;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
	 private DBObjectMapper usermap = new UserMapper();

	 private Mapper mapper = new Mapper();
	public List<DBObject> getAllUser() {
		String collectionName = "users";
		List<DBObject> userlist = new ArrayList<>();
        System.out.println("welcome to lambda function yeh.!!!");
        mapper.rowsMapper(collectionName, userlist, usermap);  
        
		return userlist;
	}





}
