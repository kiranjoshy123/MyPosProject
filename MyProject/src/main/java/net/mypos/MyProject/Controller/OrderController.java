package net.mypos.MyProject.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.mypos.MyProject.Service.SalesService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	SalesService saleService;
	
	@RequestMapping(value="/pay")
	public ModelAndView processOrder() {
		ModelAndView mv = new ModelAndView("page");
		//mv.addObject("title", product.getName()); 
		//mv.addObject("product", product);
		mv.addObject("userClickedOrderPayment", true);
		return mv;
	}
	
	@RequestMapping(value="payment/cash")
	public String processCashPayment() {
		saleService.addSales(1);
		return "redirect:/home";
	}
	
}
