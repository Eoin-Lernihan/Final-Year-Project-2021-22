package ie.gmit.sw;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class DBConnection {
	public MongoClient getDBConection() {
		ConnectionString connectionString = new ConnectionString("mongodb+srv://user1:bV7hIKFPvWxZqlB8@serverlessinstance0.dgmiv.mongodb.net/ServerlessInstance0?retryWrites=true&w=majority&authSource=admin");
		MongoClientSettings settings = MongoClientSettings.builder()
		        .applyConnectionString(connectionString)
		        .serverApi(ServerApi.builder()
		            .version(ServerApiVersion.V1)
		            .build())
		        .build();
		MongoClient mongoClient = MongoClients.create(settings);
		return mongoClient;
	}
	
	
	public void closeDB(MongoClient mongoClient) {
		mongoClient.close();
	}
}
