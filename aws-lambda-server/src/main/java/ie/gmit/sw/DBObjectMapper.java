package ie.gmit.sw;

import java.util.List;

import org.bson.Document;

public interface DBObjectMapper {

	public void populateEnity(List<DBObject> userlist, Document returnData);
}