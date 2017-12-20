package net.mypos.MyProject.Controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.mypos.MyProjectBackend.dao.CatergoryDAO;
import net.mypos.MyProjectBackend.dao.ProductDAO;
import net.mypos.MyProjectBackend.dto.Category;
import net.mypos.MyProjectBackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	@Autowired
	private CatergoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation", required=false) String operation) {
		
		// Create a new Product.
		Product newProduct = new Product();
		newProduct.setSupplier_id(1);
		newProduct.setIs_active(true);
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickedManageProducts",true);
		mv.addObject("title","Manage Products");
		mv.addObject("product", newProduct);
		
		if(operation != null) {
			if(operation.equals("product")){
				mv.addObject("message", "Product submitted successfully!");
			}
		}
		
		return mv;
	}
	
	@RequestMapping(value="/{id}/product", method=RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id) {
			
			// Create a new Product.
			Product newProduct = productDAO.get(id);
			
			ModelAndView mv = new ModelAndView("page");
			mv.addObject("userClickedManageProducts",true);
			mv.addObject("title","Manage Products");
			
			// set the product fetch from database
			mv.addObject("product", newProduct);
			return mv;
		} 
	
	
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product modifiedProduct, BindingResult results, Model model) {
		
		logger.info(modifiedProduct.toString());
		
		// Check for errors.
		if(results.hasErrors()) {
			model.addAttribute("userClickedManageProducts",true);
			model.addAttribute("title","Manage Products");
			model.addAttribute("message","Validation failed for Product Submission!");
			return "page";
		}
		
		if(modifiedProduct.getId() == 0) {
			// id = 0 means, product doesn't exists. Hence add a new one. 
			productDAO.add(modifiedProduct);
		}
		else {
			productDAO.update(modifiedProduct);
		}
		
		return "redirect:/manage/products?operation=product";
	}
	
	
	@RequestMapping(value="/product/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {
		
		Product extProduct = productDAO.get(id);
		boolean isAlredyActive = extProduct.isIs_active();
		extProduct.setIs_active(!isAlredyActive);
		productDAO.update(extProduct);
		
		return isAlredyActive ? "You have succesfully Deactivated the product with id " + id : "You have succesfully Aactivated the product with id " + id; 
	}
	
	
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return categoryDAO.list();
	}
	
}
