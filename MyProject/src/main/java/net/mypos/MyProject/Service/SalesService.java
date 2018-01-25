package net.mypos.MyProject.Service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.mypos.MyProjectBackend.dao.SalesDAO;
import net.mypos.MyProjectBackend.dto.Sales;

@Service("salesService")
public class SalesService {
	
	@Autowired
	private SalesDAO salesDAO;
	
	public void addSales(int productId) {
		Sales order = new Sales();
		
		order.setProductCount(2);
		order.setByingPrice(100);
		order.setTotal(200);
		order.setProductId(1);
		order.setCustomerId(1);
		order.setStaffId(1);
		order.setDiscount(0);
		order.setPaymentMethod(1);
		order.setTaxPaid(0);
		
		Date currDateTime = new Date();
		order.setDateTime(currDateTime);
		
		salesDAO.add(order);
	}
}
