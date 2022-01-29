package ie.gmit.sw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserDao {
	 private DBObjectMapper usermap = new UserMapper();

	 private Mapper mapper = new Mapper();
	public List<User> getAllUser() {
		String collectionName = "users";
		List<DBObject> userlist = new ArrayList<>();
        System.out.println("welcome to lambda function yeh.!!!");
        mapper.rowsMapper(collectionName, userlist, usermap);  
        List<User> users= userlist.stream().map(user -> (User) user).collect(Collectors.toList() );
		return  users;
	}





}
