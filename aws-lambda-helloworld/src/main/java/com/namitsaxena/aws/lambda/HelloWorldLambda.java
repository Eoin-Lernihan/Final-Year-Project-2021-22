package com.namitsaxena.aws.lambda;

import java.util.Map;

import org.bson.Document;

import com.amazonaws.services.lambda.runtime.Context;
import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


/**
 * Note:
 * Example: http://docs.aws.amazon.com/lambda/latest/dg/get-started-step4-optional.html doesn't work (gives - deserialization error with Integer
 * 	com.fasterxml.jackson.databind.JsonMappingException: Can not deserialize instance of java.lang.Integer out of START_OBJECT token
 * 
 * Solution: http://stackoverflow.com/questions/35545642/error-executing-hello-world-for-aws-lambda-in-java
 * http://docs.aws.amazon.com/AWSToolkitEclipse/latest/ug/lambda-tutorial.html
 * 
 * @author namit
 *
 */
public class HelloWorldLambda 
{	
	public static void main(String[] args){

		HelloWorldLambda hello = new HelloWorldLambda();
		hello.handleRequest(null, null);
		
	}
	
	
	public String myHandler(Map<String,Object> input, Context context) 
	{
		System.out.println(input);
        return "Hello World";
    }
	
	public Object handleRequest(Object o, Context context) {

        System.out.println("welcome to lambda function yeh.!!!");
        try {
        	
        	
        	ConnectionString connectionString = new ConnectionString("mongodb+srv://user1:bV7hIKFPvWxZqlB8@serverlessinstance0.dgmiv.mongodb.net/ServerlessInstance0?retryWrites=true&w=majority&authSource=admin");

			MongoClientSettings settings = MongoClientSettings.builder()
        	        .applyConnectionString(connectionString)
        	        .serverApi(ServerApi.builder()
        	            .version(ServerApiVersion.V1)
        	            .build())
        	        .build();
        	MongoClient mongoClient = MongoClients.create(settings);
        	MongoDatabase database = mongoClient.getDatabase("test");
            System.out.println("welcome to lambda function yeh.!!!");

        	MongoCollection<Document> col = database.getCollection("Names");

            System.out.println("data count" + col.countDocuments());

            BasicDBObject setValue = new BasicDBObject();
            BasicDBObject query = new BasicDBObject();
            query.put("contacts.mobileNo", "7090909090");
            setValue.put("contacts.$.userId", "userId12121212");
            BasicDBObject set = new BasicDBObject("$set", setValue);
            mongoClient.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
}
