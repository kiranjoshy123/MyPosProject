package net.mypos.MyProjectBackend.dao;

import java.util.List;

import net.mypos.MyProjectBackend.dto.Product;

public interface ProductDAO {
	
	List<Product> list();
	
	Product get(int productID);
	Product get(String code);
	boolean add(Product product);
	boolean update(Product product);
	boolean delete(Product product);
	
	// Business methods.
	List<Product> listActiveProducts();
	List<Product> listActiveProductsByCategory(int categoryID);
	List<Product> getLatestActiveProducts(int count);

}
