package net.mypos.MyProjectBackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.mypos.MyProjectBackend.dao.UserinfoDAO;
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
	public Userinfo get(int userID) {
		try {
			return sessFactory.getCurrentSession().get(Userinfo.class,Integer.valueOf(userID));
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean add(Userinfo user) {
		try {
			// add category to the database table.
			sessFactory.getCurrentSession().persist(user);
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

}
