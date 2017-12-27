package net.mypos.MyProject.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.mypos.MyProject.Service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	CartService cartservice;
	
	@RequestMapping(value="/add/{productId}/product")
	public String addToCart(@PathVariable int productId) {
		logger.info("Inside addToCart");
		cartservice.addCartLine(productId);
		return "redirect:/show/all/products";
	}
}
