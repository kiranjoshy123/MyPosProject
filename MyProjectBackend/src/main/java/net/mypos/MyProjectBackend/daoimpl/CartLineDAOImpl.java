package net.mypos.MyProjectBackend.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.mypos.MyProjectBackend.dao.CartLineDAO;
import net.mypos.MyProjectBackend.dto.Cart;
import net.mypos.MyProjectBackend.dto.CartLine;
import net.mypos.MyProjectBackend.dto.Category;

@Repository("cartLineDAO")
@Transactional
public class CartLineDAOImpl implements CartLineDAO {

	@Autowired
	private SessionFactory sessFactory;
	
	@Override
	public CartLine get(int id) {
		try {
				return sessFactory.
						getCurrentSession().
							get(CartLine.class,Integer.valueOf(id));
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean add(CartLine cartline) {
		try {
			// add cart line to the database table.
			sessFactory.getCurrentSession().persist(cartline);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(CartLine cartline) {
		try {
			// update category line to the database table.
			sessFactory.getCurrentSession().update(cartline);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(CartLine cartline) {
		return false;
	}

	@Override
	public List<CartLine> list(int cartId) {
		String selectCartLine = "FROM CartLine WHERE cartId =:cartId";
		return sessFactory.getCurrentSession()
					.createQuery(selectCartLine, CartLine.class)
						.setParameter("cartId", cartId)
							.getResultList();
	}

	@Override
	public List<CartLine> listAvailable(int cartId) {
		String selectCartLine = "FROM CartLine WHERE cartId =:cartId AND available = :available";
		return sessFactory.getCurrentSession()
					.createQuery(selectCartLine, CartLine.class)
						.setParameter("cartId", cartId)
							.setParameter("available", true)
								.getResultList();
	}

	@Override
	public CartLine getByCartAndProduct(int cartId, int productId) {
		try {
				String selectCartLine = "FROM CartLine WHERE cartId =:cartId AND product.id = :productId";
				return sessFactory.getCurrentSession()
							.createQuery(selectCartLine, CartLine.class)
								.setParameter("cartId", cartId)
									.setParameter("productId", productId)
										.getSingleResult();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	// Related to cart
	@Override
	public boolean updateCart(Cart cart) {
		try {
			// update category line to the database table.
			sessFactory.getCurrentSession().update(cart);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
