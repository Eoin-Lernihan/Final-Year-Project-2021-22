package ie.gmit.sw;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class  DBObject {
	public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
