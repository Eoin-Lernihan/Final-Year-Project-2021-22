package ie.gmit.sw.data.model;

import java.util.List;

import com.google.gson.Gson;

import ie.gmit.sw.data.utily.DBObject;

public class Admin extends DBObject{
	private Integer number;
	private String username;
	private String name;
	private String email;
	private String phoneNumber;
	private List<String> gamesRunning;
	private String password;
	

	public Admin() {
	//defualt constuctor
	}
	
	public Admin(String json) {
        Gson gson = new Gson();
        Admin request = gson.fromJson(json, Admin.class);
        this.username = request.getUsername();
        this.name = request.getName();
        this.email = request.getEmail();
        this.phoneNumber = request.getPhoneNumber();
        this.password = request.getPassword();
        this.setGamesRunning(request.getGamesRunning());
        
	}
	
	
	public String getPassword() {
		return this.password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<String> getGamesRunning() {
		return gamesRunning;
	}

	public void setGamesRunning(List<String> gamesRunning) {
		this.gamesRunning = gamesRunning;
	}

	public void setPassword(String password) {
		this.password= password;
		
	}

}

	