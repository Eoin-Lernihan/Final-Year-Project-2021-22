package ie.gmit.sw.data.utily;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * 
 * @author eoinb
 *
 */
public abstract class  DBObject {
	/**
	 * used to ensure that the body response has a json structure with escaped quotes 
	 */
	public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
