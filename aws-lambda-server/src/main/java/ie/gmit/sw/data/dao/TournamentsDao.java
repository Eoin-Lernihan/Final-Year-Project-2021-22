package ie.gmit.sw.data.dao;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;

import ie.gmit.sw.data.mapper.TournamentsMapper;
import ie.gmit.sw.data.model.Tournament;
import ie.gmit.sw.data.utily.DBObject;
import ie.gmit.sw.data.utily.DBObjectMapper;

/**
 * https://stackoverflow.com/questions/44878605/mongodb-basicdbobject-vs-document-in-java
 * https://github.com/mongodb-developer/java-quick-start/blob/master/src/main/java/com/mongodb/quickstart/Update.java
 * Specific Methods for orchestraing
 * interaction with DB for Tournament
 * @author eoinb
 *
 */
public class TournamentsDao extends BaseDao implements DaoCommonInterface {
	private static final String TOURNAMENTS_TABLE_NAME = "Games";
	private DBObjectMapper tournamentMap = new TournamentsMapper();
	/**
	 * gets all form tournaments
	 */
	public List<Object> getAll() {
		List<Object> tournamentLists = getRowsForFilter(null);
		return tournamentLists;
	}

	@Override
	/**
	 * Returns a list of Tournaments from the database normally used for getting one,  
	 *
	 * @param filters map
	 */
	public List<Object> getOne(Map<String, String> filters) {

		Bson filter = createMongoDBFilter(filters, true);
		List<Object> tournament = new ArrayList<>();
		tournament = getRowsForFilter(filter);
		return tournament;
	}

	@Override
	/**
	 * adds a new object to the database
	 * @param request1
	 */
	public void addOne(Object request1) {
		Tournament request = (Tournament) request1;
		MongoCollection<Document> collection = getCollection(TOURNAMENTS_TABLE_NAME);
		collection.insertOne(tournamentMap.formater(request));
	}

	@Override
	/**
	 * Updates an existing tournament in the database
	 */
	public void updateOne(Object request1, Map<String, String> filters) {
		Tournament request = (Tournament) request1;
		MongoCollection<Document> collection = getCollection(TOURNAMENTS_TABLE_NAME);

		Bson filter = createMongoDBFilter(filters, true);

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
	/**
	 * 
	 * @param filter
	 * @return
	 */
	private List<Object> getRowsForFilter(Bson filter) {
		MongoCollection<Document> collection = getCollection(TOURNAMENTS_TABLE_NAME);
		List<DBObject> adminlist = new ArrayList<>();
		getRows(collection, adminlist, tournamentMap, filter);
		List<Object> admins = adminlist.stream().map(t -> (Tournament) t).collect(Collectors.toList());
		return admins;
	}

	@Override
	/**
	 * @params number
	 */
	public void deleteOne(Integer number) {
		MongoCollection<Document> collection = getCollection(TOURNAMENTS_TABLE_NAME);

		Bson filter = eq("number", number);
		deleteOneInMongoDB(collection, filter);

	}

}
