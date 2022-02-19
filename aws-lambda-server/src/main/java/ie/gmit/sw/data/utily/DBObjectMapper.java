package ie.gmit.sw.data.utily;

import java.util.List;

import org.bson.Document;

import ie.gmit.sw.data.model.User;

public interface DBObjectMapper {

	public void populateEnity(List<DBObject> userlist, Document returnData);

	Document formater(User reqUser);
	
}