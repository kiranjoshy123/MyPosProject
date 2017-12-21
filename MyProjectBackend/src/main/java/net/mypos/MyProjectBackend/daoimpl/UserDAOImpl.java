package net.mypos.MyProjectBackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.mypos.MyProjectBackend.dao.UserDAO;
import net.mypos.MyProjectBackend.dto.Cart;
import net.mypos.MyProjectBackend.dto.User;


@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessFactory;
	
	@Override
	public List<User> list() {
		return sessFactory.getCurrentSession()
				.createQuery("FROM USER_DETAIL", User.class)
					.getResultList();
	}
	
	@Override
	public boolean addUser(User user) {
		try {
			sessFactory.getCurrentSession().persist(user);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean addCart(Cart cart) {
		try {
			sessFactory.getCurrentSession().persist(cart);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateCart(Cart cart) {
		try {
			sessFactory.getCurrentSession().update(cart);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public User getByEmail(String email) {
		try {
			String selQuery = "FROM User WHERE email = :email";
			return sessFactory.
					getCurrentSession()
						.createQuery(selQuery, User.class)
							.setParameter("email", email)
								.getSingleResult();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
