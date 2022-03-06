package ie.gmit.sw.data.mapper;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import ie.gmit.sw.data.model.Admin;
import ie.gmit.sw.data.model.Tournament;
import ie.gmit.sw.data.model.User;
import ie.gmit.sw.data.utily.DBObject;
import ie.gmit.sw.data.utily.DBObjectMapper;

public class TournamentsMapper implements DBObjectMapper {

	public void populateEnity(List<DBObject> tournamentsList, Document returnData) {
	
		Tournament tournament = new Tournament();
		tournament.setOwner(returnData.getString("owner"));
		tournament.setGame(returnData.getString("game"));
		tournament.setGameMode(returnData.getString("gameMode"));
		tournament.setDescription(returnData.getString("description"));
		tournament.setMaxPlayers(returnData.getInteger("maxPlayers", 12));
		tournament.setPlayers(returnData.getList("players", String.class));
		tournament.setTime(returnData.getDate("time"));
		tournament.setDuration(returnData.getInteger("duration", 60));
		tournament.setNumber(returnData.getInteger("number"));
		tournament.setNumRounds(returnData.getInteger("numRounds"));
		tournament.setPublic(returnData.getBoolean("public", false));
		tournamentsList.add(tournament);
	}
    
	
	
	@Override
	public Document formater(Tournament reqTournaments) {
		Document a;
		a = new Document("_id", new ObjectId());
	a.append("owner", reqTournaments.getOwner());
	a.append("game", reqTournaments.getGame())
	.append("gameMode", reqTournaments.getGameMode())
	.append("description", reqTournaments.getDescription())
	.append("maxPlayers", reqTournaments.getMaxPlayers())
	.append("players", reqTournaments.getPlayers())
	.append("number", reqTournaments.getNumber())
	.append("time", reqTournaments.getTime())
	.append("duration", reqTournaments.getDuration())
	.append("public", reqTournaments.getIsPublic())
	.append("numRounds", reqTournaments.getNumRounds());
	
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
