package net.mypos.MyProject.Service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mypos.MyProject.Model.UserModel;
import net.mypos.MyProjectBackend.dao.CartLineDAO;
import net.mypos.MyProjectBackend.dao.ProductDAO;
import net.mypos.MyProjectBackend.dto.Cart;
import net.mypos.MyProjectBackend.dto.CartLine;
import net.mypos.MyProjectBackend.dto.Product;

@Service("cartService")
public class CartService {

	@Autowired
	private CartLineDAO cartLineDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private HttpSession session;
	
	// Return the cart of logged-in user.
	private Cart getCart() {
		return ((UserModel)session.getAttribute("userModel")).getCart();
	}
	
	public List<CartLine> getCartLines(){
		return cartLineDAO.list(this.getCart().getId());
	}

	public void addCartLine(int productId) {
		Cart cart = getCart();
		CartLine cartline = cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		if(cartline == null) {
			cartline = new CartLine();
			
			Product product = productDAO.get(productId);
			cartline.setCartId(cart.getId());
			cartline.setProduct(product);
			cartline.setByingPrice(product.getUnit_price());
			cartline.setProductCount(1);
			cartline.setTotal(product.getUnit_price());
			cartline.setAvailable(true);
			
			cartLineDAO.add(cartline);
			
			cart.setCartLines(cart.getCartLines()+1);
			cart.setGrandTotal(cart.getGrandTotal()+cartline.getTotal());
			cartLineDAO.updateCart(cart);
		}
	}

	public void deleteCartLine(int cartLineId) {
		CartLine cartline = cartLineDAO.get(cartLineId);
		if(cartline != null) {
			// Update the Cart first.
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - cartline.getTotal());
			cart.setCartLines(cart.getCartLines() - 1);
			cartLineDAO.updateCart(cart);
			
			cartLineDAO.delete(cartline);
		}
		
	}
}
