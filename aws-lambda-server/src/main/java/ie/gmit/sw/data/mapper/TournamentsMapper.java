package ie.gmit.sw.data.mapper;

import java.util.List;

import org.bson.Document;

import ie.gmit.sw.data.model.Tournaments;
import ie.gmit.sw.data.utily.DBObject;
import ie.gmit.sw.data.utily.DBObjectMapper;

public class TournamentsMapper implements DBObjectMapper {
	public void populateEnity(List<DBObject> tournamentsList, Document returnData) {
		Tournaments tournament = new Tournaments();
		tournament.setOwner(returnData.getString("owner"));
		tournament.setGame(returnData.getString("Game"));
		tournament.setGameMode(returnData.getString("GameMode"));
		tournament.setMaxPeople(returnData.getString("MaxPeople"));
		tournament.setCurrentPeople(returnData.getString("CurrentPeople"));
		tournamentsList.add(tournament);
	}
}
