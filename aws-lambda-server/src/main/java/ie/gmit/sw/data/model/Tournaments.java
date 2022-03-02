package ie.gmit.sw.data.model;

import com.google.gson.Gson;

import ie.gmit.sw.data.utily.DBObject;

public class Tournaments extends  DBObject {
	private String owner;
	private String game;
	private String gameMode;
	private String maxPeople;
	private String currentPeople;
	private Integer number;
	

	public Tournaments() {
		//defualt constuctor
		}
	
	public Tournaments(String json) {
        Gson gson = new Gson();
        Admin request = gson.fromJson(json, Admin.class);
        this.owner = request.getCompanyUserName();
        this.game = request.getCompanyName();
        this.gameMode = request.getCompanyEmail();
        this.maxPeople = request.getCompanyNumber();
        this.currentPeople= request.getGamesRunning();
        
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
	public String getMaxPeople() {
		return maxPeople;
	}
	public void setMaxPeople(String maxPeople) {
		this.maxPeople = maxPeople;
	}
	public String getCurrentPeople() {
		return currentPeople;
	}
	public void setCurrentPeople(String currentPeople) {
		this.currentPeople = currentPeople;
	}
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}
