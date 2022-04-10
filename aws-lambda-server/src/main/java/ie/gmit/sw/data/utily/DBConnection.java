package ie.gmit.sw.data.utily;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
/**
 * Creates Connection to MongoDB
 * @author eoinb
 *
 */
public class DBConnection {
	/**
	 * 
	 * @return
	 */
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
	
	/**
	 * Close concetion with mongodb
	 * @param mongoClient
	 */
	public void closeDB(MongoClient mongoClient) {
		mongoClient.close();
	}
}
