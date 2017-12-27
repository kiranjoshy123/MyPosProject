package net.mypos.MyProjectBackend.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	private Userinfo user;	// DB column name will be user(from varaible name)_(entity name from UserInfo.i.e. is id)
	
	@Column(name="grand_Total")
	private double grandTotal = 0;
	
	@Column(name="cart_Lines")
	private int cartLines = 0;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Userinfo getUser() {
		return user;
	}
	public void setUser(Userinfo user) {
		this.user = user;
	}
	public double getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
	public int getCartLines() {
		return cartLines;
	}
	public void setCartLines(int cartLines) {
		this.cartLines = cartLines;
	}
	
	
	@Override
	public String toString() {
		return "Cart [id=" + id + ", userId=" + user.getId() + ", grandTotal=" + grandTotal + ", cartLines=" + cartLines
				+ "]";
	}
}
