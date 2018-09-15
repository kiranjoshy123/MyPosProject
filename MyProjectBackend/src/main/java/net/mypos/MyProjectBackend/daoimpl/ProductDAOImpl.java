package net.mypos.MyProjectBackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.mypos.MyProjectBackend.dao.ProductDAO;
import net.mypos.MyProjectBackend.dto.Product;


@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessFactory;
	
	@Override
	public List<Product> list() {
		return sessFactory.getCurrentSession()
				.createQuery("FROM Product", Product.class)
					.getResultList();
	}

	@Override
	public Product get(int productID) {
		try {
			return sessFactory.getCurrentSession().get(Product.class,Integer.valueOf(productID));
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Product get(String code) {
		try {
			String selectActiveProducts = "FROM Product WHERE code = :code";
			return sessFactory
					.getCurrentSession()
						.createQuery(selectActiveProducts,Product.class)
							.setParameter("code", code)
								.getSingleResult();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean add(Product product) {
		try {
			// add category to the database table.
			sessFactory.getCurrentSession().persist(product);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Product product) {
		try {
			// updating a single category.
			sessFactory.getCurrentSession().update(product);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Product product) {
		// Disable the item.
		product.setIs_active(false);
		try {
			// updating a single category.
			return this.update(product);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Product> listActiveProducts() {
		String selectActiveProducts = "FROM Product WHERE is_active = :active";
		return sessFactory
				.getCurrentSession()
					.createQuery(selectActiveProducts,Product.class)
						.setParameter("active", true)
							.getResultList();
	}

	@Override
	public List<Product> listActiveProductsByCategory(int subcategoryID) {
		String selectActiveProductsBysubcategory = "FROM Product WHERE is_active = :active AND subcategory_id = :subcategoryID";
		return sessFactory
				.getCurrentSession()
					.createQuery(selectActiveProductsBysubcategory,Product.class)
						.setParameter("active", true)
							.setParameter("subcategoryID", subcategoryID)
								.getResultList();
	}
	
	@Override
	public List<Product> listActiveProductsByCode(String code){
		String selectActiveProducts = "FROM Product WHERE code = :code";
		return sessFactory
				.getCurrentSession()
					.createQuery(selectActiveProducts,Product.class)
						.setParameter("code", code)
							.getResultList();
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		String selectActiveProducts = "FROM Product WHERE is_active = :active ORDER BY id";
		return sessFactory
				.getCurrentSession()
					.createQuery(selectActiveProducts,Product.class)
						.setParameter("active", true)
							.setFirstResult(0)
								.setMaxResults(count)
									.getResultList();
	}

	
	
}
