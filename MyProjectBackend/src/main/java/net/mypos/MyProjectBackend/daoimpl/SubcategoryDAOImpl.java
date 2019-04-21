package net.mypos.MyProjectBackend.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.mypos.MyProjectBackend.dao.SubcategoryDAO;
import net.mypos.MyProjectBackend.dto.Product;
import net.mypos.MyProjectBackend.dto.Subcategory;

@Repository("subcategoryDAO")
@Transactional
public class SubcategoryDAOImpl implements SubcategoryDAO{

	@Autowired
	private SessionFactory sessFactory;
	
	
	@Override
	public Subcategory get(int id) {
		try {
			return sessFactory.getCurrentSession().get(Subcategory.class,Integer.valueOf(id));
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean add(Subcategory subcategory) {
		try {
			// add category to the database table.
			sessFactory.getCurrentSession().persist(subcategory);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean udpate(Subcategory subcategory) {
		try {
			// updating a single category.
			sessFactory.getCurrentSession().update(subcategory);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Subcategory subcategory) {
		// Disable the item.
		subcategory.setActive(false);
		try {
			// updating a single category.
			sessFactory.getCurrentSession().update(subcategory);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<Subcategory> listActiveSubcategories() {
		String selectActiveCategory = "FROM Subcategory WHERE active =:active";
		Query query = sessFactory.getCurrentSession().createQuery(selectActiveCategory);
		query.setParameter("active", true);
		return query.getResultList();
	}

	@Override
	public List<Subcategory> listActiveSubcategoriesByCategory(int categoryID) {
		String selectActiveSubCategoryByCategory = "FROM Subcategory WHERE is_active = :active AND category_id = :categoryID";
		return sessFactory
				.getCurrentSession()
					.createQuery(selectActiveSubCategoryByCategory,Subcategory.class)
						.setParameter("active", true)
							.setParameter("categoryID", categoryID)
								.getResultList();
	}

	@Override
	public List<Subcategory> getLatestActiveSubcategories(int count) {
		String selectActiveProducts = "FROM Subcategory WHERE is_active = :active ORDER BY id";
		return sessFactory
				.getCurrentSession()
					.createQuery(selectActiveProducts,Subcategory.class)
						.setParameter("active", true)
							.setFirstResult(0)
								.setMaxResults(count)
									.getResultList();
	}

}
