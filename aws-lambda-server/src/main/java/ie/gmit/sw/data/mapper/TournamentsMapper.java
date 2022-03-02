package ie.gmit.sw.data.mapper;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import ie.gmit.sw.data.model.Admin;
import ie.gmit.sw.data.model.Tournaments;
import ie.gmit.sw.data.model.User;
import ie.gmit.sw.data.utily.DBObject;
import ie.gmit.sw.data.utily.DBObjectMapper;

public class TournamentsMapper implements DBObjectMapper {
	public void populateEnity(List<DBObject> tournamentsList, Document returnData) {
		Tournaments tournament = new Tournaments();
		tournament.setOwner(returnData.getString("owner"));
		tournament.setGame(returnData.getString("game"));
		tournament.setGameMode(returnData.getString("gameMode"));
		tournament.setMaxPeople(returnData.getString("maxPeople"));
		tournament.setCurrentPeople(returnData.getString("currentPeople"));
		tournamentsList.add(tournament);
	}

	
	@Override
	public Document formater(Tournaments reqTournaments) {
		Document a;
		a = new Document("_id", new ObjectId());
	a.append("owner", reqTournaments.getOwner());
	a.append("game", reqTournaments.getGame())
	.append("gameMode", reqTournaments.getGameMode())
	.append("maxPeople", reqTournaments.getMaxPeople())
	.append("currentPeople", reqTournaments.getCurrentPeople());
	return a;
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
