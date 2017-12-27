package net.mypos.MyProjectBackend.dao;

import java.util.List;

import net.mypos.MyProjectBackend.dto.Cart;
import net.mypos.MyProjectBackend.dto.Userinfo;

public interface UserinfoDAO {
	
	List<Userinfo> list();
	
	Userinfo get(int userID);
	Userinfo getbyEmail(String email);
	
	boolean add(Userinfo user,Cart cart);
	boolean update(Userinfo user);
	boolean delete(Userinfo user);
	
	Cart getCart(int userID);
}
