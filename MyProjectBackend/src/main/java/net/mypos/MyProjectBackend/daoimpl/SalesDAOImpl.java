package net.mypos.MyProjectBackend.daoimpl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

	@Override
	public List<Sales> getTodaysSalesById(int staffId) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date curDate = cal.getTime();
		
		String selectTodaysSalesForAStaff = "FROM Sales WHERE staff_id = :staffId AND DATE(date_time) =:curDate";
		return sessFactory
				.getCurrentSession()
					.createQuery(selectTodaysSalesForAStaff,Sales.class)
							.setParameter("staffId", staffId)
								.setParameter("curDate", curDate)
									.getResultList();
	}

	@Override
	public List<Sales> getCompleteSalesById(int staffId) {
		String selectTodaysSalesForAStaff = "FROM Sales WHERE staff_id = :staffId";
		return sessFactory
				.getCurrentSession()
					.createQuery(selectTodaysSalesForAStaff,Sales.class)
							.setParameter("staffId", staffId)
									.getResultList();
	}

	@Override
	public List<Sales> getTodaysSales() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date curDate = cal.getTime();
		
		String selectTodaysSalesForAStaff = "FROM Sales WHERE DATE(date_time) =:curDate";
		return sessFactory
				.getCurrentSession()
					.createQuery(selectTodaysSalesForAStaff,Sales.class)
								.setParameter("curDate", curDate)
									.getResultList();
	}

	@Override
	public List<Sales> getCompleteSales() {
		String selectTodaysSalesForAStaff = "FROM Sales";
		return sessFactory
				.getCurrentSession()
					.createQuery(selectTodaysSalesForAStaff,Sales.class)
									.getResultList();
	}
}
