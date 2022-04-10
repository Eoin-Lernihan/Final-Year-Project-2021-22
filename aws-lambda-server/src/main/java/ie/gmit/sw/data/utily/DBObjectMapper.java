package ie.gmit.sw.data.utily;

import java.util.List;

import org.bson.Document;

import ie.gmit.sw.data.model.Admin;
import ie.gmit.sw.data.model.Tournament;
import ie.gmit.sw.data.model.User;
/**
 * Interface for Communicating with the the Mapping layer
 * @author eoinb
 *
 */
public interface DBObjectMapper {

	public void populateEnity(List<DBObject> userlist, Document returnData);
	/**
	 * User mapper
	 * @param reqUser
	 * @return
	 */
	Document formater(User reqUser);
	/**
	 * Admin mapper
	 * @param reqAdmin
	 * @return
	 */
	Document formater(Admin reqAdmin);
	/**
	 * Tournament mapper
	 * @param reqUser
	 * @return
	 */
	Document formater(Tournament reqUser);
	
}