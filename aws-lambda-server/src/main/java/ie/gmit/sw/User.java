package ie.gmit.sw;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class User extends DBObject  {
	private String firstName;
	private String lastName;
	private String email;
	
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
	public User() {
	//defualt constuctor
	}
	
	public User(String json) {
        Gson gson = new Gson();
        User request = gson.fromJson(json, User.class);
        this.email = request.getEmail();
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
	}

    

}
