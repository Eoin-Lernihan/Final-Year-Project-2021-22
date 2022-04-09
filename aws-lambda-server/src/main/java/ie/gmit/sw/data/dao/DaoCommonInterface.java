package ie.gmit.sw.data.dao;

import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;

import com.mongodb.client.MongoCollection;

import ie.gmit.sw.data.model.User;

public interface DaoCommonInterface {


	List<Object> getAll();

	void updateOne(Object request, Map<String, String> filters);

	void addOne(Object request);

	List<Object> getOne(Map<String, String> key);

	void deleteOne(Integer integer);

	void deleteOne(String key);


}
