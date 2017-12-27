package net.mypos.MyProjectBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.mypos.MyProjectBackend.dao.CartLineDAO;
import net.mypos.MyProjectBackend.dao.ProductDAO;
import net.mypos.MyProjectBackend.dao.UserinfoDAO;
import net.mypos.MyProjectBackend.dto.Cart;
import net.mypos.MyProjectBackend.dto.CartLine;
import net.mypos.MyProjectBackend.dto.Product;
import net.mypos.MyProjectBackend.dto.Userinfo;

public class CartLineTestCase {

	private static AnnotationConfigApplicationContext context;
	
	private static CartLineDAO cartLineDAO = null;
	private static ProductDAO productDAO = null;
	private static UserinfoDAO userDAO = null;
	
	private Product product = null;
	private Userinfo user = null;
	private Cart cart = null;
	private CartLine cartline = null;
	
	
	@BeforeClass
	public static void init() {
		
		context = new AnnotationConfigApplicationContext();
		context.scan("net.myPos.MyProjectBackend");
		context.refresh();
		
		cartLineDAO = (CartLineDAO)context.getBean("cartLineDAO");
		productDAO = (ProductDAO)context.getBean("productDAO");
		userDAO = (UserinfoDAO)context.getBean("userinfoDAO");
	}
	
	@Test
	public void testAddNewCartLine() {
		user = userDAO.getbyEmail("rj@gmail.com");
		cart = userDAO.getCart(user.getId());
		
		product = productDAO.get(4);
		cartline = new CartLine();
		cartline.setByingPrice(product.getUnit_price());
		cartline.setProductCount(cartline.getProductCount() + 1);
		cartline.setTotal(cartline.getProductCount()*product.getUnit_price());
		cartline.setAvailable(true);
		cartline.setCartId(cart.getId());
		cartline.setProduct(product);
		
		assertEquals("Failed to add the cartline!!", true, cartLineDAO.add(cartline));
		
		// update the cart.
		//cart.setGrandTotal(cart.getGrandTotal() + cartline.getTotal());
		//cart.setCartLines(cart.getCartLines() + 1);
		//assertEquals("Failed to update the cart", true, cartLineDAO.updateCart(cart));
	}
}
