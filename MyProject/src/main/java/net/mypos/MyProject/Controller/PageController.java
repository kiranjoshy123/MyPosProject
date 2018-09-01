package net.mypos.MyProject.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.mypos.MyProject.Service.CartService;
import net.mypos.MyProject.exception.ProductNotFoundException;
import net.mypos.MyProjectBackend.dao.CatergoryDAO;
import net.mypos.MyProjectBackend.dao.ProductDAO;
import net.mypos.MyProjectBackend.dao.SubcategoryDAO;
import net.mypos.MyProjectBackend.dao.UserinfoDAO;
import net.mypos.MyProjectBackend.daoimpl.UserinfoDAOImpl;
import net.mypos.MyProjectBackend.dto.Admin;
import net.mypos.MyProjectBackend.dto.Cart;
import net.mypos.MyProjectBackend.dto.CartLine;
import net.mypos.MyProjectBackend.dto.Category;
import net.mypos.MyProjectBackend.dto.Product;
import net.mypos.MyProjectBackend.dto.Subcategory;
import net.mypos.MyProjectBackend.dto.Userinfo;

@Controller
public class PageController {

	private static final Logger logger = LoggerFactory.getLogger(PageController.class);

	private static final String SecurityContextHold = null;
	
	@Autowired
	CartService cartService;

	@Autowired
	private CatergoryDAO categoryDAO;

	@Autowired
	private SubcategoryDAO subcategoryDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private UserinfoDAO userinfoDAO;

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		mv.addObject("userClickedHome", true);
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("bOrderItems", true);

		return mv;
	}

	@RequestMapping(value = { "/about" })
	public ModelAndView about(HttpSession session) {
		int t = (int) session.getAttribute("test");
		Integer test1 = t;

		logger.info(session.getId());
		logger.info(test1.toString());
		logger.info("in about");
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickedAbout", true);
		return mv;
	}

	@RequestMapping(value = { "/contact" })
	public ModelAndView contact(HttpSession session) {
		int t = 100;
		session.setAttribute("test", t);

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickedContact", true);
		return mv;
	}
	
	@RequestMapping(value = { "/history" })
	public ModelAndView history() {
		ModelAndView mv = new ModelAndView("history");
		mv.addObject("title", "History");
		return mv;
	}
	
	
	/*
	 * Methods to load all the products based on category
	 * */
	@RequestMapping(value = { "/show/subcategory/{id}/products" })
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");
		
		// categorydao to fetch a single category
		Subcategory subcategory = null;
		subcategory = subcategoryDAO.get(id);
		
		mv.addObject("title", subcategory.getName());
		mv.addObject("subcategory", subcategory);
		mv.addObject("productList", productDAO.listActiveProductsByCategory(id));
		
		mv.addObject("userClickedCategoryProducts", true);
		return mv;
	}
	
	/*
	 * Methods to load all the sub categories from category
	 * */
	@RequestMapping(value = { "/show/category/{id}/subcategory" })
	public ModelAndView showSubCategories(@PathVariable("id") int id) {
		logger.info("showSubCategories is " + id);
		ModelAndView mv = new ModelAndView("page");
		
		// subcategorydao to fetch a single category
		Category category = null;
		category = categoryDAO.get(id);
		
		mv.addObject("title", category.getName());
		mv.addObject("category", category);
		mv.addObject("subcategoryList", subcategoryDAO.listActiveSubcategoriesByCategory(id));
		logger.info("subcategoryDAO.listActiveSubcategoriesByCategory() Size"
				+ subcategoryDAO.listActiveSubcategoriesByCategory(id).size());

		mv.addObject("userClickedSubCategory", true);
		return mv;
	}
	
	
	/*
	 * Viewing a single product
	 **/
	@RequestMapping(value = "/search/products", method = RequestMethod.POST)
	@ResponseBody
	public Product searchProduct(@ModelAttribute("code") String code) throws ProductNotFoundException {
		logger.info("retieveProduct " + code);
		Product product = productDAO.get(code);
		if (product == null) {
			throw new ProductNotFoundException();
		}
		return product;
	}

	@RequestMapping(value = { "/show/{id}/product" })
	public ModelAndView showSingleProduct(@PathVariable("id") int id) throws ProductNotFoundException {

		ModelAndView mv = new ModelAndView("page");
		Product product = productDAO.get(id);
		if (product == null) {
			throw new ProductNotFoundException();
		}
		logger.info("In show single product");
		logger.info(product.getName());
		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		mv.addObject("userClickedShowProduct", true);
		return mv;
	}

	/*
	 * Login
	 **/
	@RequestMapping(value = { "/login" })
	public ModelAndView showLogin(@RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout, HttpSession session) {
		int test = 1;
		
		session.setAttribute("test", test);
		ModelAndView mv = new ModelAndView("login");
		if (error != null) {
			mv.addObject("message", "Invalid user name or password");
		}

		if (logout != null) {
			mv.addObject("logout", "User has successfully logout!");
		}

		mv.addObject("title", "Login");
		mv.addObject("users", userinfoDAO.getActiveUserslist());
		logger.info(userinfoDAO.getActiveUserslist().toString());
		return mv;
	}

	@RequestMapping(value = { "/perform-logout" })
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		return "redirect:/login?logout";
	}

	/*
	 * Access denied page
	 **/
	@RequestMapping(value = { "/access-denied" })
	public ModelAndView showAccessDenied() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("title", "403 - Access Denied");
		mv.addObject("ErrorTitle", "Error!!");
		mv.addObject("ErrorDescription", "You are not authorised to view this page.");
		return mv;
	}

	@RequestMapping(value = { "/print" })
	public ModelAndView print() {
		logger.info("In print");
		ModelAndView mv = new ModelAndView("print");
		mv.addObject("title", "403 - Access Denied");
		mv.addObject("ErrorTitle", "Error!!");
		mv.addObject("ErrorDescription", "You are not authorised to view this page.");
		return mv;
	}
	
	@RequestMapping("/add/product/{id}")
	public String addCartLine(@PathVariable("id") int id) {
	    cartService.addCartLine(id);
		return "success";

	}
}
