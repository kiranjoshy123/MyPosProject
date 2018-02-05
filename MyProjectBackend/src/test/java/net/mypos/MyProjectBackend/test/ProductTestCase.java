package net.mypos.MyProjectBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.mypos.MyProjectBackend.dao.ProductDAO;
import net.mypos.MyProjectBackend.dto.Product;

public class ProductTestCase {

	private static AnnotationConfigApplicationContext context;
	private static ProductDAO productDAO;
	private Product product;
	
	@BeforeClass
	public static void init() {
		
		context = new AnnotationConfigApplicationContext();
		context.scan("net.myPos.MyProjectBackend");
		context.refresh();
		
		productDAO = (ProductDAO)context.getBean("productDAO");
	}
	
	private void testAddProduct(String name, String brand, String description, double unitPrice, int subcategory_id, int supplier_id) {
		product = new Product();
		product.setName(name);
		product.setBrand(brand);
		product.setDescription(description);
		product.setUnit_price(unitPrice);
		product.setIs_active(true);
		product.setSubcategory_id(subcategory_id);
		product.setSupplier_id(supplier_id);
		
		assertEquals("Something went wrong while inserting a new product!", true, productDAO.add(product));
	}
	
	
	private void testGetProduct(int id, String productName) {
		product = productDAO.get(id);
		assertEquals("Something went wrong while fetching a product from the table!", productName, product.getName());
	}
	
	
	private void testUpdateProduct(int id, String newProductName) {
		product = productDAO.get(id);
		product.setName(newProductName);
		assertEquals("Something went wrong while updating a product in the table!", true, productDAO.update(product));
	}
	
	
	private void testDeleteProduct(int id) {
		product = productDAO.get(id);
		assertEquals("Something went wrong while deleting a product in the table!", true, productDAO.delete(product));
	}
	
	
	private void testListProduct(int listCountExpected) {
		assertEquals("Something went wrong while fetching the list of Products from the table!", listCountExpected, productDAO.list().size());
	}
	
	private void testListActiveProducts(int count) {
		assertEquals("Something went wrong while fetching the list of Products from the table!", count, productDAO.listActiveProducts().size());
	}
	
	private void testListActiveProductsByCategory(int categoryID, int count) {
		assertEquals("Something went wrong while fetching the list of Products from the table!", count, productDAO.listActiveProductsByCategory(categoryID).size());
	}
	
	private void testGetLatestActiveProducts(int maxCount, int expectedCount) {
		assertEquals("Something went wrong while fetching the list of Products from the table!", expectedCount, productDAO.getLatestActiveProducts(maxCount).size());
	}
	
	@Test
	public void testCRUDProduct() {
		/*testAddProduct("Oppo Selfie S53", "Oppo", "This is some description for oppo mobile phone!", 25000, 3, 3);
		//testAddProduct("Redmi Note 3", "Xiomi", "This is some description for Redmi Note 3 mobile phone!", 12000, 3, 3);
		
		testGetProduct(2, "Samsung s7");
		testUpdateProduct(2, "Samsung Galaxy S7");
		testDeleteProduct(2);
		testListProduct(6);
		
		testListActiveProducts(5);
		testListActiveProductsByCategory(3,3);
		testGetLatestActiveProducts(3,3);*/
		
	}
}
