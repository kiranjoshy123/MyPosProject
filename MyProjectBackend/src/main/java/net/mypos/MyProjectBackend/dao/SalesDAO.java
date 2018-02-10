package net.mypos.MyProjectBackend.dao;

import java.util.List;

import net.mypos.MyProjectBackend.dto.Sales;

public interface SalesDAO {
	boolean add(Sales sales);
	boolean udpate(Sales sales);
	boolean delete(Sales sales);
	
	// Get the sales for each staff.
	public List<Sales> getTodaysSalesById(int staffId);
	public List<Sales> getCompleteSalesById(int staffId);
	
	// Get the entire sales for Admin.
	public List<Sales> getTodaysSales();
	public List<Sales> getCompleteSales();
}
