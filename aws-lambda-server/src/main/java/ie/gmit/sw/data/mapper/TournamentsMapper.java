package ie.gmit.sw.data.mapper;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import ie.gmit.sw.data.model.Admin;
import ie.gmit.sw.data.model.Tournament;
import ie.gmit.sw.data.model.User;
import ie.gmit.sw.data.utily.DBObject;
import ie.gmit.sw.data.utily.DBObjectMapper;

/**
 * Maps Tournamnets
 * 
 * @author eoinb
 *
 */
public class TournamentsMapper implements DBObjectMapper {

	public static final String PUBLIC = "public";
	public static final String NUM_ROUNDS = "numRounds";
	public static final String NUMBER = "number";
	public static final String DURATION = "duration";
	public static final String TIME = "time";
	public static final String PLAYERS = "players";
	public static final String MAX_PLAYERS = "maxPlayers";
	public static final String DESCRIPTION = "description";
	public static final String GAME_MODE = "gameMode";
	public static final String GAME = "game";
	public static final String OWNER = "owner";

	/**
	 * 
	 */
	public void populateEnity(List<DBObject> tournamentsList, Document returnData) {

		Tournament tournament = new Tournament();
		tournament.setOwner(returnData.getString(OWNER));
		tournament.setGame(returnData.getString(GAME));
		tournament.setGameMode(returnData.getString(GAME_MODE));
		tournament.setDescription(returnData.getString(DESCRIPTION));
		tournament.setMaxPlayers(returnData.getInteger(MAX_PLAYERS, 12));
		tournament.setPlayers(returnData.getList(PLAYERS, String.class));
		tournament.setTime(returnData.getDate(TIME));
		tournament.setDuration(returnData.getInteger(DURATION, 60));
		tournament.setNumber(returnData.getInteger(NUMBER));
		tournament.setNumRounds(returnData.getInteger(NUM_ROUNDS));
		tournament.setPublic(returnData.getBoolean(PUBLIC, false));
		tournamentsList.add(tournament);
	}

	@Override
	/**
	 * 
	 */
	public Document formater(Tournament reqTournaments) {
		Document newDocument;
		newDocument = new Document("_id", new ObjectId());
		newDocument.append(OWNER, reqTournaments.getOwner());
		newDocument.append(GAME, reqTournaments.getGame()).append(GAME_MODE, reqTournaments.getGameMode())
				.append(DESCRIPTION, reqTournaments.getDescription())
				.append(MAX_PLAYERS, reqTournaments.getMaxPlayers()).append(PLAYERS, reqTournaments.getPlayers())
				.append(NUMBER, reqTournaments.getNumber()).append(TIME, reqTournaments.getTime())
				.append(DURATION, reqTournaments.getDuration()).append(PUBLIC, reqTournaments.getIsPublic())
				.append(NUM_ROUNDS, reqTournaments.getNumRounds());

		return newDocument;
	}

	@Override
	public Document formater(Admin reqAdmin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document formater(User reqUser) {
		// TODO Auto-generated method stub
		return null;
	}
}
