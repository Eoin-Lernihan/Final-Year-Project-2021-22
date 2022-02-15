package ie.gmit.sw.data.utily;

import java.util.List;

import org.bson.Document;

public interface DBObjectMapper {

	public void populateEnity(List<DBObject> userlist, Document returnData);
	
}