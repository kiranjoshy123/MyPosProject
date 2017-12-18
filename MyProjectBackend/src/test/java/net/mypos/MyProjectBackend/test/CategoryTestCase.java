package net.mypos.MyProjectBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.mypos.MyProjectBackend.dao.CatergoryDAO;
import net.mypos.MyProjectBackend.dto.Category;

public class CategoryTestCase {
	
	private static AnnotationConfigApplicationContext context;
	private static CatergoryDAO categoryDAO;
	
	private Category category;
	
	
	@BeforeClass
	public static void init() {
		
		context = new AnnotationConfigApplicationContext();
		context.scan("net.myPos.MyProjectBackend");
		context.refresh();
		
		categoryDAO = (CatergoryDAO)context.getBean("categoryDAO");
	}
	
	
	private void testAddCategory(String name, String description, String ImageURL) {
		category = new Category();
		category.setName(name);
		category.setDescription(description);
		category.setImageURL(ImageURL);
		
		assertEquals("Something went wrong while inserting a new Category!", true, categoryDAO.add(category));
	}
	
	
	private void testGetCategory(int id, String categoryName) {
		category = categoryDAO.get(id);
		assertEquals("Something went wrong while fetching a Category from the table!", categoryName, category.getName());
	}
	
	
	private void testUpdateCategory(int id, String newCategoryName) {
		category = categoryDAO.get(id);
		category.setName(newCategoryName);
		assertEquals("Something went wrong while updating a Category in the table!", true, categoryDAO.udpate(category));
	}
	
	
	private void testDeleteCategory(int id) {
		category = categoryDAO.get(id);
		assertEquals("Something went wrong while deleting a Category in the table!", true, categoryDAO.delete(category));
	}
	
	
	private void testListCategory(int listCountExpected) {
		assertEquals("Something went wrong while fetching the list of Categories from the table!", listCountExpected, categoryDAO.list().size());
	}
	
	@Test
	public void testCRUDCategory() {
		
		/*testAddCategory("Laptop","This is some description for laptop!","CAT_1.png");
		testAddCategory("Television","This is some description for TV!","CAT_2.png");
		
		testGetCategory(1,"Laptop");
		testUpdateCategory(1,"Laptop-Test");
		testDeleteCategory(1);
		testListCategory(1);*/
	}

}
