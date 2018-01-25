package net.mypos.MyProjectBackend.dao;

import net.mypos.MyProjectBackend.dto.Sales;

public interface SalesDAO {
	boolean add(Sales sales);
	boolean udpate(Sales sales);
	boolean delete(Sales sales);
}
