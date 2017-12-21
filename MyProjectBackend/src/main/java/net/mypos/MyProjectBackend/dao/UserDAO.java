package net.mypos.MyProjectBackend.dao;

import java.util.List;

import net.mypos.MyProjectBackend.dto.Cart;
import net.mypos.MyProjectBackend.dto.User;

public interface UserDAO {
	
	List<User> list();
	
	boolean addUser(User user);
	User getByEmail(String email);
	
	boolean addCart(Cart cart);
	boolean updateCart(Cart cart);

}
