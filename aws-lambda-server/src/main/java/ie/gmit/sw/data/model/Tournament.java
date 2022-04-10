package ie.gmit.sw.data.model;

import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import ie.gmit.sw.data.utily.DBObject;
/**
 * Model object for TournamentEntity
 * @author eoinb
 *
 */
public class Tournament extends  DBObject {
	private String owner;
	private String game;
	private String gameMode;
	private Integer maxPlayers;
	private List<String> players;
	private Integer number;
	private String description;
	private Date time;
	private Integer duration;
	private boolean isPublic;
	private Integer numRounds;

	public Tournament() {
	}
	
	public Tournament(String json) {
        Gson gson = new Gson();
        Tournament request = gson.fromJson(json, Tournament.class);
        this.owner = request.getOwner();
        this.game = request.getGame();
        this.gameMode = request.getGameMode();
        this.maxPlayers = request.getMaxPlayers();
        this.players = request.getPlayers();
        this.number = request.getNumber();
        this.description = request.getDescription();
        this.time = request.getTime();
        this.duration = request.getDuration();
        this.isPublic = request.getIsPublic();
        this.numRounds = request.getNumRounds();
        
	}
	
	
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getGameMode() {
		return gameMode;
	}

	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}
	

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public boolean getIsPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public Integer getNumRounds() {
		return numRounds;
	}

	public void setNumRounds(Integer numRounds) {
		this.numRounds = numRounds;
	}

	public Integer getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(Integer maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public List<String> getPlayers() {
		return players;
	}

	public void setPlayers(List<String> players) {
		this.players = players;
	}

	

}
