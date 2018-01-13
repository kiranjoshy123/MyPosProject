package net.mypos.MyProjectBackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.mypos.MyProjectBackend.dao.UserinfoDAO;
import net.mypos.MyProjectBackend.dto.Admin;
import net.mypos.MyProjectBackend.dto.Cart;
import net.mypos.MyProjectBackend.dto.Customer;
import net.mypos.MyProjectBackend.dto.Staff;
import net.mypos.MyProjectBackend.dto.Supplier;
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
	public boolean add(Userinfo user, String role1) {
		try {
			
			String role = user.getRole();
			if(role.equals("STAFF"))
			{
				Staff staff = new Staff();
				staff.setEnabled(user.getEnabled());
				staff.setEmail(user.getEmail());
				staff.setAddress(user.getAddress());
				staff.setContactNumber(user.getContactNumber());
				staff.setUserinfo(user);
				
				// add user to the userinfo table.
				sessFactory.getCurrentSession().persist(user);
				sessFactory.getCurrentSession().persist(staff);
				return true;
			}
			else if(role.equals("ADMIN"))
			{
				Admin admin = new Admin();
				admin.setEnabled(user.getEnabled());
				admin.setEmail(user.getEmail());
				admin.setAddress(user.getAddress());
				admin.setContactNumber(user.getContactNumber());
				admin.setUserinfo(user);
				
				// add user to the userinfo table.
				sessFactory.getCurrentSession().persist(user);
				sessFactory.getCurrentSession().persist(admin);
				return true;
			}
			else if(role.equals("CUSTOMER"))
			{
				Customer customer = new Customer();
				customer.setEnabled(user.getEnabled());
				customer.setEmail(user.getEmail());
				customer.setAddress(user.getAddress());
				customer.setContactNumber(user.getContactNumber());
				customer.setUserinfo(user);
				
				// add user to the userinfo table.
				sessFactory.getCurrentSession().persist(user);
				sessFactory.getCurrentSession().persist(customer);
				return true;
			}
			else if(role.equals("SUPPLIER"))
			{
				Supplier supplier = new Supplier();
				supplier.setEnabled(user.getEnabled());
				supplier.setEmail(user.getEmail());
				supplier.setAddress(user.getAddress());
				supplier.setContactNumber(user.getContactNumber());
				supplier.setUserinfo(user);
				
				// add user to the userinfo table.
				sessFactory.getCurrentSession().persist(user);
				sessFactory.getCurrentSession().persist(supplier);
				return true;
			}
			
			return false;
			
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
