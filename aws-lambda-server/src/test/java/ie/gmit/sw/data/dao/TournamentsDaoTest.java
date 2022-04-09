package ie.gmit.sw.data.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import ie.gmit.sw.data.model.Tournament;

class TournamentsDaoTest {

	@Test
	void testAll() {
		TournamentsDao tournamentsDao = new TournamentsDao();
		List<Tournament> all = tournamentsDao.getAll().stream().map(a -> (Tournament) a ).collect(Collectors.toList());
		all.forEach(a -> System.out.println("getAll Tournaments"+a.toString()));
		assertTrue(all.size()>1); 
	}

	@Test
	void testgetOne() {
		TournamentsDao tournamentsDao = new TournamentsDao();
		List<Tournament> all = tournamentsDao.getAll().stream().map(a -> (Tournament) a ).collect(Collectors.toList());
		all.forEach(a -> System.out.println("getAll Tournaments"+a.toString()));
		Tournament expected  =  all.get(0);
		Map<String , String> filters = new HashMap<>();
		String searchNumber = expected.getNumber().toString();
		filters.put("number", searchNumber);

		tournamentsDao.getOne(filters).forEach(t -> System.out.println("getOne Tourament" + t.toString()));

		List<Tournament> actual = (List<Tournament>) tournamentsDao.getOne(filters).stream().map(a -> (Tournament) a ).collect(Collectors.toList());

		actual.forEach(u -> System.out.println("getOne Tournaments"+u.toString()));
		assertEquals(1, actual.size()); 
		assertEquals(expected.getNumber(), actual.get(0).getNumber());	

	}

	
	@Test
	void testInGame () {
		TournamentsDao tournamentsDao = new TournamentsDao();
		Map<String , String> filters = new HashMap<>();
		filters = new HashMap<>();
		filters.put("userName", "u");
		filters.put("inGame", "1");
		List<Tournament> inGames = tournamentsDao.getOne(filters).stream().map(a -> (Tournament) a ).collect(Collectors.toList());
		assertTrue(inGames.size()>1);



	}

	@Test
	void testNotInGame () {
		TournamentsDao tournamentsDao = new TournamentsDao();
		Map<String , String> filters = new HashMap<>();
		filters = new HashMap<>();
		filters.put("userName", "u");
		filters.put("inGame", "0");
		List<Tournament> inGames = tournamentsDao.getOne(filters).stream().map(a -> (Tournament) a ).collect(Collectors.toList());
		assertTrue(inGames.size()>1);

	}

	
	@Test
	void testUpdateOne () {
		TournamentsDao tournamentsDao = new TournamentsDao();
		List<Tournament> all = tournamentsDao.getAll().stream().map(a -> (Tournament) a ).collect(Collectors.toList());
		all.forEach(a -> System.out.println("getAll Tournaments"+a.toString()));
		Tournament expected  =  all.get(0);
		Map<String , String> filters = new HashMap<>();
		String searchNumber = expected.getNumber().toString();
		filters.put("number", searchNumber);
		expected.setDescription("description changed");
		tournamentsDao.updateOne(expected,filters);

		
		List<Tournament> actual = (List<Tournament>) tournamentsDao.getOne(filters).stream().map(a -> (Tournament) a ).collect(Collectors.toList());
		actual.forEach(u -> System.out.println("getOne Tournaments"+u.toString()));
		assertEquals(1, actual.size()); 
		assertEquals(expected.getDescription(), actual.get(0).getDescription());
		assertEquals(expected.getNumber(), actual.get(0).getNumber());

	}

	
	
	
	@Test
	void testCreateAndDeleteOne () {
		TournamentsDao tournamentsDao = new TournamentsDao();
		Integer seedNumber = getRandomNumber();
		createTournament(seedNumber, true, tournamentsDao);
		Map<String , String> filters = new HashMap<>();
		filters.put("number", seedNumber.toString());
		
		List<Tournament> actual = (List<Tournament>) tournamentsDao.getOne(filters).stream().map(a -> (Tournament) a ).collect(Collectors.toList());

		actual.forEach(u -> System.out.println("getOne Tournaments"+u.toString()));
		assertEquals(1, actual.size()); 

		assertEquals(seedNumber, actual.get(0).getNumber(), "Tournament failed to insesrt");

		tournamentsDao.deleteOne(seedNumber);
		
		actual = tournamentsDao.getOne(filters).stream().map(a -> (Tournament) a ).collect(Collectors.toList());
		assertEquals(actual.size(), 0); 

	}

	
	private static int getRandomNumber() {
		int min= 1000;
		int max= 1000000;
	    return (int) ((Math.random() * (max - min)) + min);
	}


	private static Tournament createTournament(Integer seedNumber, Boolean publicValue, TournamentsDao tournamentsDao) {
		Tournament tournament = new Tournament();

		tournament.setNumber(seedNumber);
		tournament.setOwner("owner" + seedNumber.toString());
		tournament.setGame("game" + seedNumber.toString());
		tournament.setGameMode("gameMode" + seedNumber.toString());
		tournament.setDescription("description" + seedNumber.toString());
		tournament.setMaxPlayers(12);
		List<String> players = new ArrayList<>();
		tournament.setPlayers(players);
		tournament.setTime(convertToDateViaInstant(LocalDate.now().plusMonths(1)));
		tournament.setDuration(60);
		tournament.setNumRounds(3);
		tournament.setPublic(publicValue);
		tournamentsDao.addOne(tournament);
		return tournament;
	}

	private static Date convertToDateViaInstant(LocalDate dateToConvert) {
		return java.util.Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}



	
}
