package net.mypos.MyProjectBackend.dao;

import net.mypos.MyProjectBackend.dto.Cart;
import net.mypos.MyProjectBackend.dto.User;

public interface UserDAO {
	
	boolean addUser(User user);
	User getByEmail(String email);
	boolean updateCart(Cart cart);

}
