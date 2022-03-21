package ie.gmit.sw.controller;

import com.google.gson.annotations.SerializedName;

import ie.gmit.sw.data.utily.DBObject;

public class Headers extends DBObject {
	 //headers: {
    // "Access-Control-Allow-Headers" : "Content-Type",
    //"Access-Control-Allow-Origin": "https://www.example.com",
    //"Access-Control-Allow-Methods": "OPTIONS,POST,GET"
	//}
	 @SerializedName("Access-Control-Allow-Headers") String accessControlAllowHeader = "Content-Type";
	 @SerializedName("Access-Control-Allow-Origin") String accessControlAllowOrigin = "*";
	 @SerializedName("Access-Control-Allow-Methods") String accessControlAllowMethods = "OPTIONS,POST,GET,PUT,DELETE";
	public String getAccessControlAllowHeader() {
		return accessControlAllowHeader;
	}
	public void setAccessControlAllowHeader(String accessControlAllowHeader) {
		this.accessControlAllowHeader = accessControlAllowHeader;
	}
	public String getAccessControlAllowOrigin() {
		return accessControlAllowOrigin;
	}
	public void setAccessControlAllowOrigin(String accessControlAllowOrigin) {
		this.accessControlAllowOrigin = accessControlAllowOrigin;
	}
	public String getAccessControlAllowMethods() {
		return accessControlAllowMethods;
	}
	public void setAccessControlAllowMethods(String accessControlAllowMethods) {
		this.accessControlAllowMethods = accessControlAllowMethods;
	}
	 
}
