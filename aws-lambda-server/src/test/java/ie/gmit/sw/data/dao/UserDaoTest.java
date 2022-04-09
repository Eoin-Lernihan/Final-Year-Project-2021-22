package ie.gmit.sw.data.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import ie.gmit.sw.data.model.User;

class UserDaoTest {

	@Test
	void testAll() {
		UserDao userDao = new UserDao();
		List<User> all = userDao.getAll().stream().map(a -> (User) a ).collect(Collectors.toList());
		all.forEach(a -> System.out.println("getAll Users"+a.toString()));
		assertTrue(all.size()>1); 
	}

	@Test
	void testgetOne() {
		UserDao userDao = new UserDao();
		List<User> all = userDao.getAll().stream().map(a -> (User) a ).collect(Collectors.toList());
		all.forEach(a -> System.out.println("getAll Users"+a.toString()));
		User expected  =  all.get(0);
		Map<String , String> filters = new HashMap<>();
		filters.put("username", expected.getUserName());
		List<User> actual = (List<User>) userDao.getOne(filters).stream().map(a -> (User) a ).collect(Collectors.toList());

		actual.forEach(u -> System.out.println("getOne Users"+u.toString()));
		assertEquals(1, actual.size()); 
		assertEquals(expected.getUserName(), actual.get(0).getUserName());
	

	}

	@Test
	void testUpdateOne () {
		UserDao userDao = new UserDao();
		List<User> all = userDao.getAll().stream().map(a -> (User) a ).collect(Collectors.toList());
		all.forEach(a -> System.out.println("getAll Users"+a.toString()));
		User expected  =  all.get(0);
		Map<String , String> filters = new HashMap<>();
		filters.put("username", expected.getUserName());
		expected.setFirstName("UpdateNamed");
		userDao.updateOne(expected,filters);

		
		List<User> actual = (List<User>) userDao.getOne(filters).stream().map(a -> (User) a ).collect(Collectors.toList());

		actual.forEach(u -> System.out.println("getOne Users"+u.toString()));
		assertEquals(1, actual.size()); 
		assertEquals(expected.getUserName(), actual.get(0).getUserName());
		assertEquals("UpdateNamed", actual.get(0).getFirstName());

	}
	
	@Test
	void testCreateAndDeleteOne () {
		UserDao userDao = new UserDao();

		Integer seedNumber = getRandomNumber();
		User user = new User();
		user.setFirstName("first"+seedNumber);
		user.setLastName("last"+seedNumber);
		user.setEmail("email"+seedNumber);
		user.setPhoneNumber("086-444444"+seedNumber);
		user.setUserName("username"+seedNumber);
		user.setPassword("passwordX"+seedNumber);

		userDao.addOne(user);

		
		Map<String , String> filters = new HashMap<>();
		filters.put("username", user.getUserName());
		
		List<User> actual = (List<User>) userDao.getOne(filters).stream().map(a -> (User) a ).collect(Collectors.toList());

		actual.forEach(u -> System.out.println("getOne Users"+u.toString()));
		assertEquals(1, actual.size()); 

		assertEquals("username"+seedNumber.toString(), actual.get(0).getUserName(), "User failed to insesrt");

		userDao.deleteOne("username"+seedNumber.toString());
		
		actual = userDao.getOne(filters).stream().map(a -> (User) a ).collect(Collectors.toList());
		assertEquals(0, actual.size()); 

	}

	
	private static int getRandomNumber() {
		int min= 1000;
		int max= 1000000;
	    return (int) ((Math.random() * (max - min)) + min);
	}


}
