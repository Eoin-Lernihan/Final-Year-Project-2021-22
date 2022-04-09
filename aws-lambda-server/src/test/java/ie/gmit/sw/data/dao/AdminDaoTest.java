package ie.gmit.sw.data.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import ie.gmit.sw.data.model.Admin;

class AdminDaoTest {

	@Test
	void testAll() {
		AdminDao adminDao = new AdminDao();
		List<Admin> all = adminDao.getAll().stream().map(a -> (Admin) a ).collect(Collectors.toList());
		all.forEach(a -> System.out.println("getAll Admins"+a.toString()));
		assertTrue(all.size()>1); 
	}

	@Test
	void testgetOne() {
		AdminDao adminDao = new AdminDao();
		List<Admin> all = adminDao.getAll().stream().map(a -> (Admin) a ).collect(Collectors.toList());
		all.forEach(a -> System.out.println("getAll Admins"+a.toString()));
		Admin expected  =  all.get(0);
		Map<String , String> filters = new HashMap<>();
		filters.put("username", expected.getUsername());
		List<Admin> actual = (List<Admin>) adminDao.getOne(filters).stream().map(a -> (Admin) a ).collect(Collectors.toList());

		actual.forEach(u -> System.out.println("getOne Admins"+u.toString()));
		assertEquals(1, actual.size()); 
		assertEquals(expected.getUsername(), actual.get(0).getUsername());
	

	}

	@Test
	void testUpdateOne () {
		AdminDao adminDao = new AdminDao();
		List<Admin> all = adminDao.getAll().stream().map(a -> (Admin) a ).collect(Collectors.toList());
		all.forEach(a -> System.out.println("getAll Admins"+a.toString()));
		Admin expected  =  all.get(0);
		Map<String , String> filters = new HashMap<>();
		filters.put("username", expected.getUsername());
		expected.setName("companyName2");
		adminDao.updateOne(expected,filters);

		
		List<Admin> actual = (List<Admin>) adminDao.getOne(filters).stream().map(a -> (Admin) a ).collect(Collectors.toList());

		actual.forEach(u -> System.out.println("getOne Admins"+u.toString()));
		assertEquals(1, actual.size()); 
		assertEquals(expected.getUsername(), actual.get(0).getUsername());
		assertEquals("companyName2", actual.get(0).getName());

	}
	
	@Test
	void testCreateAndDeleteOne () {
		AdminDao adminDao = new AdminDao();
		Admin admin = new Admin();

		Integer seedNumber = getRandomNumber();
		admin.setNumber(seedNumber  );
		admin.setEmail("email"+seedNumber.toString());
		admin.setName("name"+seedNumber.toString());
		admin.setUsername("CompanyUserName"+seedNumber.toString());
		admin.setPhoneNumber("description"+seedNumber.toString());
		admin.setPassword("password");

		List<String> gamesruning = new ArrayList<>();
		admin.setGamesRunning(gamesruning);
		adminDao.addOne(admin);

		
		Map<String , String> filters = new HashMap<>();
		filters.put("username", "CompanyUserName"+seedNumber.toString());
		
		List<Admin> actual = (List<Admin>) adminDao.getOne(filters).stream().map(a -> (Admin) a ).collect(Collectors.toList());

		actual.forEach(u -> System.out.println("getOne Admins"+u.toString()));
		assertEquals(1, actual.size()); 

		assertEquals("CompanyUserName"+seedNumber.toString(), actual.get(0).getUsername(), "Admin failed to insesrt");

		adminDao.deleteOne(seedNumber);
		
		actual = adminDao.getOne(filters).stream().map(a -> (Admin) a ).collect(Collectors.toList());
		assertTrue(actual.size()==0); 

	}
	
	private static int getRandomNumber() {
		int min= 1000;
		int max= 1000000;
	    return (int) ((Math.random() * (max - min)) + min);
	}


}
