package net.mypos.MyProjectBackend.dao;

import java.util.List;

import net.mypos.MyProjectBackend.dto.Subcategory;

public interface SubcategoryDAO {
	Subcategory get(int id);
	boolean add(Subcategory subcategory);
	boolean udpate(Subcategory subcategory);
	boolean delete(Subcategory subcategory);
	
	
	// Business methods.
	List<Subcategory> listActiveSubcategories();
	List<Subcategory> listActiveSubcategoriesByCategory(int categoryID);
	List<Subcategory> getLatestActiveSubcategories(int count);
}
