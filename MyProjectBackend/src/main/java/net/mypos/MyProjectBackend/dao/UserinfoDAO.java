package net.mypos.MyProjectBackend.dao;

import java.util.List;

import net.mypos.MyProjectBackend.dto.Admin;
import net.mypos.MyProjectBackend.dto.Cart;
import net.mypos.MyProjectBackend.dto.Customer;
import net.mypos.MyProjectBackend.dto.Staff;
import net.mypos.MyProjectBackend.dto.Supplier;
import net.mypos.MyProjectBackend.dto.Userinfo;

public interface UserinfoDAO {
	
	List<Userinfo> list();
	List<Userinfo> getActiveUserslist();
	
	Userinfo get(int userID);
	Userinfo getbyEmail(String email);
	
	Admin getAdminByPersonId(int userID);
	Staff getStaffByPersonId(int userID);
	Supplier getSupplierByPersonId(int userID);
	Customer getCustomerByPersonId(int userID);
	
	boolean add(Userinfo user, String role);
	boolean update(Userinfo user);
	boolean delete(Userinfo user);
	
	Cart getCart(int userID);
}
