package net.mypos.MyProjectBackend.dao;

import java.util.List;

import net.mypos.MyProjectBackend.dto.Category;

public interface CatergoryDAO {

	
	
	List<Category> list();
	Category get(int id);
}
