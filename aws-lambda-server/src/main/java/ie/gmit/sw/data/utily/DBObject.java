package ie.gmit.sw.data.utily;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class  DBObject {
	public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
