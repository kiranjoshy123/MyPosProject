package net.mypos.MyProjectBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.mypos.MyProjectBackend.dao.UserDAO;
import net.mypos.MyProjectBackend.dto.Cart;
import net.mypos.MyProjectBackend.dto.User;


public class UserTestCase {

	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO = null;
	private User user = null;
	private Cart cart = null;
	
	@BeforeClass
	public static void init() {
		
		context = new AnnotationConfigApplicationContext();
		context.scan("net.myPos.MyProjectBackend");
		context.refresh();
		
		userDAO = (UserDAO)context.getBean("userDAO");
	}
	
	
	@Test
	public void testAdd() {
		user = new User();
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setUserName("HrithikRoshan");
		user.setContactNumber("7953453422");
		user.setRole("USER");
		user.setPassword("123456");
		user.setAddress("101 B Jadoo Society, Krish Nagar, Mumbai, Maharashtra");
		
		assertEquals("Failed to add user!", true,userDAO.addUser(user));
		
		if(user.getRole().equals("USER"))
		{
			cart = new Cart();
			cart.setGrandTotal(2000);
			cart.setCartLines(2);

			assertEquals("Failed to add cart!", true, userDAO.addCart(cart));
		}
	}
}