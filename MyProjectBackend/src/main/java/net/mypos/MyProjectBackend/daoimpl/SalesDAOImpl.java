package net.mypos.MyProjectBackend.daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.mypos.MyProjectBackend.dao.SalesDAO;
import net.mypos.MyProjectBackend.dto.Sales;

@Repository("salesDAO")
@Transactional
public class SalesDAOImpl implements SalesDAO{
	@Autowired
	private SessionFactory sessFactory;
	
	@Override
	public boolean add(Sales sales) {
		try {
			// add category to the database table.
			sessFactory.getCurrentSession().persist(sales);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean udpate(Sales sales) {
		try {
			// updating a single order.
			sessFactory.getCurrentSession().update(sales);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean delete(Sales sales) {
		// delete the item.
		try {
			// updating a single category.
			sessFactory.getCurrentSession().delete(sales);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
