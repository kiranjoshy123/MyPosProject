package net.mypos.MyProjectBackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.mypos.MyProjectBackend.dao.UserinfoDAO;
import net.mypos.MyProjectBackend.dto.Cart;
import net.mypos.MyProjectBackend.dto.Userinfo;

@Repository("userinfoDAO")
@Transactional
public class UserinfoDAOImpl implements UserinfoDAO {

	@Autowired
	private SessionFactory sessFactory;
	
	@Override
	public List<Userinfo> list() {
		return sessFactory.getCurrentSession()
				.createQuery("FROM Userinfo", Userinfo.class)
					.getResultList();
	}
	
	@Override
	public List<Userinfo> getActiveUserslist(){
		return sessFactory.getCurrentSession()
				.createQuery("FROM Userinfo WHERE ENABLED = TRUE", Userinfo.class)
					.getResultList();
	}

	@Override
	public Userinfo get(int userID) {
		try {
			return sessFactory.getCurrentSession().get(Userinfo.class,Integer.valueOf(userID));
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Userinfo getbyEmail(String email) {
		try {
			String selQuery = "FROM Userinfo WHERE email = :email";
			return sessFactory.
						getCurrentSession().
							createQuery(selQuery, Userinfo.class).
								setParameter("email", email).
									getSingleResult();
								
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean add(Userinfo user, Cart cart) {
		try {
			// Map the cart and user.
			cart.setUser(user);
			// add user to the userinfo table.
			sessFactory.getCurrentSession().persist(user);
			sessFactory.getCurrentSession().persist(cart);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Userinfo user) {
		try {
			// updating a single category.
			sessFactory.getCurrentSession().update(user);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Userinfo user) {
		// TODO Auto-generated method stub
		return false;
	}

	// Method to get the respective cart for a user.
	@Override
	public Cart getCart(int userID) {
		try {
			String selQuery = "FROM Cart WHERE user.id = :userID";
			return sessFactory.
						getCurrentSession().
							createQuery(selQuery, Cart.class).
								setParameter("userID", userID).
									getSingleResult();
								
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
