package ie.gmit.sw;

public class Tournaments implements DBObject {
	private String owner;
	private String game;
	private String gameMode;
	private String maxPeople;
	private String currentPeople;

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

}
