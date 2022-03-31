package ie.gmit.sw.data.dao;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.io.Console;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;

import ie.gmit.sw.data.mapper.TournamentsMapper;
import ie.gmit.sw.data.model.Tournament;
import ie.gmit.sw.data.utily.DBObject;
import ie.gmit.sw.data.utily.DBObjectMapper;

/**
 * https://stackoverflow.com/questions/44878605/mongodb-basicdbobject-vs-document-in-java
 * https://github.com/mongodb-developer/java-quick-start/blob/master/src/main/java/com/mongodb/quickstart/Update.java
 * 
 * @author eoinb
 *
 */
public class TournamentsDao extends BaseDao implements AllObjectsGet {
	private static final String TOURNAMENTS_TABLE_NAME = "Games";
	private DBObjectMapper tournamentMap = new TournamentsMapper();

	public List<Object> getAll() {
		List<Object> tournamentLists = getRowsForFilter(null);
		return tournamentLists;
	}

	@Override
	public List<Object> getOne(Map<String, String> filters) {

		Bson filter = createMongoDBFilter(filters);
		List<Object> tournament = new ArrayList<>();
		tournament = getRowsForFilter(filter);
		return tournament;
	}

	@Override
	public void addOne(Object request1) {
		Tournament request = (Tournament) request1;
		MongoCollection<Document> collection = getCollection(TOURNAMENTS_TABLE_NAME);
		InsertOneResult a = collection.insertOne(tournamentMap.formater(request));
	}

	@Override
	public void updateOne(Object request1, Map<String, String> filters) {
		Tournament request = (Tournament) request1;
		MongoCollection<Document> collection = getCollection(TOURNAMENTS_TABLE_NAME);

		Bson filter = createMongoDBFilter(filters);

		Bson updateOperation1 = set("owner", request.getOwner());
		Bson updateOperation2 = set("gameType", request.getGame());
		Bson updateOperation3 = set("gameMode", request.getGameMode());
		Bson updateOperation4 = set("description", request.getDescription());
		Bson updateOperation5 = set("maxPlayer", request.getMaxPlayers());
		Bson updateOperation6 = set("players", request.getPlayers());
		Bson updateOperation8 = set("time", request.getTime());
		Bson updateOperation9 = set("duration", request.getDuration());
		Bson updateOperation10 = set("public", request.getIsPublic());
		Bson updateOperation11 = set("numRounds", request.getNumRounds());

		Bson updates = combine(updateOperation1, updateOperation2, updateOperation3, updateOperation4, updateOperation5,
				updateOperation6, updateOperation8, updateOperation9, updateOperation10, updateOperation11);
		updateOneInMongoDB(collection, filter, updates);

	}

	private List<Object> getRowsForFilter(Bson filter) {
		MongoCollection<Document> collection = getCollection(TOURNAMENTS_TABLE_NAME);
		List<DBObject> adminlist = new ArrayList<>();
		getRows(collection, adminlist, tournamentMap, filter);
		List<Object> admins = adminlist.stream().map(t -> (Tournament) t).collect(Collectors.toList());
		return admins;
	}

	/**
	 * Mainly used for local testing
	 * 
	 * @param args
	 */
	// testing
	public static void main(String[] args) {
		TournamentsDao tournamentsDao = new TournamentsDao();

		Integer seedNumber = getRandomNumber();
		Boolean publicValue = false;

//		createTournament(seedNumber, publicValue,  tournamentsDao);
		// createTournament(seedNumber+1, !publicValue, tournamentsDao);
		// createTournament(seedNumber+2, publicValue, tournamentsDao);
//		createTournament(seedNumber+3, !publicValue,  tournamentsDao);

		List<Tournament> all = tournamentsDao.getAll().stream().map(t -> (Tournament) t).collect(Collectors.toList());

		all.forEach(u -> System.out.println("getAll Tourament" + u.toString()));
		String searchNumber = all.get(0).getNumber().toString();
		Map<String, String> filters = new HashMap<>();
		filters.put("number", searchNumber);

		tournamentsDao.getOne(filters).forEach(t -> System.out.println("getOne Tourament" + t.toString()));

		List<Object> one = tournamentsDao.getOne(filters);

		List<Tournament> tournamentsList = one.stream().map(t -> (Tournament) t).collect(Collectors.toList());
		tournamentsList.forEach(t -> System.out.println("getOne tournament" + t.toString()));

		Tournament tournament1 = tournamentsList.get(0);
		tournament1.setDescription("changed");
		filters.put("number", tournament1.getNumber().toString());

		tournamentsDao.updateOne(tournament1, filters);

		filters = new HashMap<>();
		filters.put("number", searchNumber);
		tournamentsList = tournamentsDao.getOne(filters).stream().map(t -> (Tournament) t).collect(Collectors.toList());
		tournamentsList.forEach(t -> System.out.println("After Update getOne Admin" + t.toString()));
		tournamentsDao.deleteOne(tournament1.getNumber());
		tournamentsList.forEach(t -> System.out.println("After delete getOne Admin" + t.toString()));
		filters = new HashMap<>();
		System.out.println("before fillter get");
		filters.put("userName", "u");
		filters.put("inGame", "1");
		tournamentsDao.getOne(filters).forEach(t -> System.out.println("getOne Tourament" + t.toString()));
		System.out.println("after fillter get");
		System.out.println("before fillter get");
		filters.put("userName", "u");
		filters.put("inGame", "0");
		tournamentsDao.getOne(filters).forEach(t -> System.out.println("getOne Tourament" + t.toString()));
		System.out.println("after fillter get");

		

	}

	private static Tournament createTournament(Integer seedNumber, Boolean publicValue, TournamentsDao tournamentDao) {
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
		tournamentDao.addOne(tournament);
		return tournament;
	}

	private static Date convertToDateViaInstant(LocalDate dateToConvert) {
		return java.util.Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	private static int getRandomNumber() {
		int min = 1000;
		int max = 1000000;
		return (int) ((Math.random() * (max - min)) + min);
	}

	@Override
	public void deleteOne(Integer number) {
		MongoCollection<Document> collection = getCollection(TOURNAMENTS_TABLE_NAME);

		Bson filter = eq("number", number);
		deleteOneInMongoDB(collection, filter);

	}

}
