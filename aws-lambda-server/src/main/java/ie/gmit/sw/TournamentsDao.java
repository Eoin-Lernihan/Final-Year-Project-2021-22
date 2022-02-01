package ie.gmit.sw;

import java.util.ArrayList;
import java.util.List;

public class TournamentsDao {
	 private DBObjectMapper tournamentmap = new TournamentsMapper();
	 private String finder = null;
	 private Mapper mapper = new Mapper();
	public List<DBObject> getAlltournament() {
		String collectionName = "Games";
		List<DBObject> tournamentList = new ArrayList<>();
        System.out.println("welcome to lambda function yeh.!!!");
        mapper.rowsMapper(collectionName, tournamentList, tournamentmap, finder);  
        
		return tournamentList;
	}
}
