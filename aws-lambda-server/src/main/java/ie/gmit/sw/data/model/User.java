package ie.gmit.sw.data.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ie.gmit.sw.data.utily.DBObject;
/**
 * Model object for User Entity
 * @author eoinb
 *
 */
public class User extends DBObject  {
	private String firstName;
	private String lastName;
	private String email;
	private String userName;
	private String phoneNumber;
	private Integer number;
	private String password;

	
	public User() {
	//defualt constuctor
	}
	
	public User(String json) {
        Gson gson = new Gson();
        User request = gson.fromJson(json, User.class);
        this.email = request.getEmail();
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
        this.userName = request.getUserName();
        this.number= request.getNumber();
        this.phoneNumber= request.getPhoneNumber();
        this.password= request.getPassword();
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getNumber() {
		return number;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    

}
