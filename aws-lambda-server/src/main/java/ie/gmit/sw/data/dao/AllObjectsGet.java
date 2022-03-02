package ie.gmit.sw.data.dao;

import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;

import com.mongodb.client.MongoCollection;

import ie.gmit.sw.data.model.User;

public interface AllObjectsGet {


	List<Object> getAll();

	void updateOne(Object request);

	void addOne(Object request);

	List<Object> getOne(String name);

	void deleteOne(Integer integer);

}
