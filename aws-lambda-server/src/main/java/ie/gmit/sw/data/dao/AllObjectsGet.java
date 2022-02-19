package ie.gmit.sw.data.dao;

import java.util.List;

import ie.gmit.sw.data.model.User;

public interface AllObjectsGet {

	List<Object> getAll();

	void updateOne(User request);

	void addOne(User request);

	List<Object> getOne(String name);

}
