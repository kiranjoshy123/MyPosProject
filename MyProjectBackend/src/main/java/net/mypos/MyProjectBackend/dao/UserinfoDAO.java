package net.mypos.MyProjectBackend.dao;

import java.util.List;

import net.mypos.MyProjectBackend.dto.Userinfo;

public interface UserinfoDAO {
	
	List<Userinfo> list();
	
	Userinfo get(int userID);
	Userinfo getbyEmail(String email);
	
	boolean add(Userinfo user);
	boolean update(Userinfo user);
	boolean delete(Userinfo user);

}
