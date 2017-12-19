package net.mypos.MyProject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.mypos.MyProjectBackend.dao.CatergoryDAO;
import net.mypos.MyProjectBackend.dto.Category;
import net.mypos.MyProjectBackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	@Autowired
	private CatergoryDAO categoryDAO;
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public ModelAndView showManageProducts() {
		
		// Create a new Product.
		Product newProduct = new Product();
		newProduct.setSupplier_id(1);
		newProduct.setIs_active(true);
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickedManageProducts",true);
		mv.addObject("title","Manage Products");
		mv.addObject("product", newProduct);
		
		return mv;
	}
	
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return categoryDAO.list();
	}
}
