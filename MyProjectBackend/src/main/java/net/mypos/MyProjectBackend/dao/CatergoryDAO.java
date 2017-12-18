package net.mypos.MyProjectBackend.dao;

import java.util.List;

import net.mypos.MyProjectBackend.dto.Category;

public interface CatergoryDAO {

	Category get(int id);
	boolean add(Category category);
	boolean udpate(Category category);
	boolean delete(Category category);
	
	
	List<Category> list();
	
}
