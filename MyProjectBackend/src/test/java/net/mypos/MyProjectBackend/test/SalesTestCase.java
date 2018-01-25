package net.mypos.MyProjectBackend.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.mypos.MyProjectBackend.dao.SalesDAO;
import net.mypos.MyProjectBackend.dto.Sales;

public class SalesTestCase {
	private static AnnotationConfigApplicationContext context;
	private static SalesDAO salesDAO;
	
	private Sales sales;
	
	@BeforeClass
	public static void init() {
		
		context = new AnnotationConfigApplicationContext();
		context.scan("net.myPos.MyProjectBackend");
		context.refresh();
		
		salesDAO = (SalesDAO)context.getBean("salesDAO");
	}
	
	//@Test
	public void testAddSales() {
		sales = new Sales();
		sales.setProductCount(2);
		sales.setByingPrice(100);
		sales.setTotal(200);
		sales.setProductId(1);
		sales.setCustomerId(1);
		sales.setStaffId(1);
		sales.setDiscount(0);
		sales.setPaymentMethod(1);
		sales.setTaxPaid(0);
		
		Date currDateTime = new Date();
		sales.setDateTime(currDateTime);
		
		assertEquals("Something went wrong while inserting a new sales!", true, salesDAO.add(sales));
	}
}
