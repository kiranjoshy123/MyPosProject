package net.mypos.MyProjectBackend.dao;

import java.util.List;

import net.mypos.MyProjectBackend.dto.Sales;

public interface SalesDAO {
	boolean add(Sales sales);
	boolean udpate(Sales sales);
	boolean delete(Sales sales);
	
	public List<Sales> getTodaysSales(int staffId);
	public List<Sales> getCompleteSales(int staffId);
}
