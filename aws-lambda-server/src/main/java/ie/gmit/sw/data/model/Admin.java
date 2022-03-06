package ie.gmit.sw.data.model;

import java.util.List;

import com.google.gson.Gson;

import ie.gmit.sw.data.utily.DBObject;

public class Admin extends DBObject{
	private Integer number;
	private String companyUserName;
	private String companyName;
	private String companyEmail;
	private String companyNumber;
	private List<String> gamesRunning;
	

	public Admin() {
	//defualt constuctor
	}
	
	public Admin(String json) {
        Gson gson = new Gson();
        Admin request = gson.fromJson(json, Admin.class);
        this.companyUserName = request.getCompanyUserName();
        this.companyName = request.getCompanyName();
        this.companyEmail = request.getCompanyEmail();
        this.companyNumber = request.getCompanyNumber();
        this.setGamesRunning(request.getGamesRunning());
        
	}
	
	
	public String getCompanyUserName() {
		return companyUserName;
	}

	public void setCompanyUserName(String companyUserName) {
		this.companyUserName = companyUserName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getCompanyNumber() {
		return companyNumber;
	}

	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
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

}

	