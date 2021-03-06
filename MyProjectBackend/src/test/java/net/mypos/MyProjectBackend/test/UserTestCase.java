package net.mypos.MyProjectBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.mypos.MyProjectBackend.dao.UserinfoDAO;
import net.mypos.MyProjectBackend.dto.Cart;
import net.mypos.MyProjectBackend.dto.Userinfo;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;
	private static UserinfoDAO userinfoDAO;
	
	private Userinfo userinfo;
	private Cart cart;
	
	@BeforeClass
	public static void init() {
		
		context = new AnnotationConfigApplicationContext();
		context.scan("net.myPos.MyProjectBackend");
		context.refresh();
		
		userinfoDAO = (UserinfoDAO)context.getBean("userinfoDAO");
	}
	
	@Test
	public void testAddUser() {
		/*userinfo = new Userinfo();
		userinfo.setFirstName("SachinStaff");
		userinfo.setLastName("Tendulkar");
		userinfo.setRole("STAFF");
		userinfo.setPassword("admin");
		userinfo.setEnabled(true);
		userinfo.setEmail("st@gmail.com");
		userinfo.setUserName("SachinTendulkar");
		userinfo.setAddress("This is Address");
		userinfo.setContactNumber("9753453422");
		
		assertEquals("Failed to add a new user!", true, userinfoDAO.add(userinfo, userinfo.getRole()));
		*/
	}
	
	/*@Test 
	public void testGetUser(){
		user = userDAO.get(4);
		assertEquals("Failed to get the existing user!", "Sachin", user.getFirst_name());
	}*/
	
	/*@Test
	public void testListUser() {
		assertEquals("Something went wrong while fetching the list of Products from the table!", 4, userinfoDAO.list().size());
	}*/
}
