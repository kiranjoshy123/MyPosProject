package net.mypos.MyProject.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.mypos.MyProject.exception.ProductNotFoundException;
import net.mypos.MyProjectBackend.dao.CatergoryDAO;
import net.mypos.MyProjectBackend.dao.ProductDAO;
import net.mypos.MyProjectBackend.dto.Category;
import net.mypos.MyProjectBackend.dto.Product;

@Controller
public class PageController {

	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	@Autowired
	private CatergoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		logger.info("Inside PageController index method - INFO");
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		mv.addObject("userClickedHome", true);
		mv.addObject("categories", categoryDAO.list());
		
		return mv;
	}
	
	@RequestMapping(value = { "/about" })
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickedAbout", true);
		return mv;
	}
	
	@RequestMapping(value = { "/contact" })
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickedContact", true);
		return mv;
	}
	
	
	/*
	 * Methods to load all the products based on category
	 * */
	@RequestMapping(value = { "/show/all/products" })
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");
		mv.addObject("userClickedAllProducts", true);
		mv.addObject("categories", categoryDAO.list());
		
		return mv;
	}
	
	/*
	 * Methods to load all the products based on category
	 * */
	@RequestMapping(value = { "/show/category/{id}/products" })
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");
		
		// categorydao to fetch a single category
		Category category = null;
		category = categoryDAO.get(id);
		
		mv.addObject("title", category.getName());
		mv.addObject("category", category);
		mv.addObject("categories", categoryDAO.list());
		
		mv.addObject("userClickedCategoryProducts", true);
		return mv;
	}
	
	/*
	 * Viewing a single product
	 **/
	 @RequestMapping(value= {"/show/{id}/product"})
	 public ModelAndView showSingleProduct(@PathVariable("id") int id) throws ProductNotFoundException {
		 
		 ModelAndView mv = new ModelAndView("page");
		 Product product = productDAO.get(id);
		 if(product == null) {
			 throw new ProductNotFoundException();
		 }
		 
		 mv.addObject("title", product.getName());
		 mv.addObject("product", product);
		 mv.addObject("userClickedShowProduct", true);
		 return mv;
	 }
	 
	 /*
	 * Login
	 **/
	 @RequestMapping(value= {"/login"})
	 public ModelAndView showLogin(@RequestParam(name ="error", required=false)String error) {
		 ModelAndView mv = new ModelAndView("login");
		 if(error != null) {
			 mv.addObject("message","Invalid user name or password");
		 }
		 
		 mv.addObject("title", "Login");
		 return mv;
	 }
	 
	 /*
	 * Access denied page
	 **/
	 @RequestMapping(value= {"/access-denied"})
	 public ModelAndView showAccessDenied() {
		 ModelAndView mv = new ModelAndView("error");		 
		 mv.addObject("title", "403 - Access Denied");
		 mv.addObject("ErrorTitle", "Error!!");
		 mv.addObject("ErrorDescription", "You are not authorised to view this page.");
		 return mv;
	 }
}
