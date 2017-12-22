package net.mypos.MyProject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.mypos.MyProjectBackend.dao.CatergoryDAO;
import net.mypos.MyProjectBackend.dao.ProductDAO;
import net.mypos.MyProjectBackend.dao.UserinfoDAO;
import net.mypos.MyProjectBackend.dto.Category;
import net.mypos.MyProjectBackend.dto.Product;
import net.mypos.MyProjectBackend.dto.Userinfo;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private CatergoryDAO categoryDAO;
	
	@Autowired
	private UserinfoDAO userinfoDAO;
	
	
	// Returning only the active products - For user.
	@RequestMapping("/all/products")
	@ResponseBody	
	public List<Product> getAllProducts(){
		return productDAO.listActiveProducts();
	}
	
	// Returning all the products( active/inactive) - For Admin
	@RequestMapping("/admin/all/products")
	@ResponseBody	
	public List<Product> getAllProductsForAdmin(){
		return productDAO.list();
	}
	
	@RequestMapping("/category/{id}/products")
	@ResponseBody
	public List<Product> getAllProductsByCategory(@PathVariable int id){
		return productDAO.listActiveProductsByCategory(id);
	}
	
	// Returning all the categories - For Admin
	@RequestMapping("/admin/all/categories")
	@ResponseBody	
	public List<Category> getAllCategoriesForAdmin(){
		return categoryDAO.list();
	}
	
	@RequestMapping("/admin/all/users")
	@ResponseBody
	public List<Userinfo> getAllListForAdmin(){
		return userinfoDAO.list();
	}
	
}
