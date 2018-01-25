package net.mypos.MyProjectBackend.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="sales")
public class Sales {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="product_count")
	private int productCount;
	
	private double total;
	
	@Column(name="buying_price")
	private double byingPrice;
	
	private double discount;
	
	@Column(name="payment_method")
	private int paymentMethod;
	
	@Column(name="tax_paid")
	private double taxPaid;
	
	@Column(name="date_time", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;
	
	@Column(name="product_id")
	private int productId;
	
	@Column(name="customer_id")
	private int customerId;
	
	@Column(name="staff_id")
	private int staffId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getByingPrice() {
		return byingPrice;
	}

	public void setByingPrice(double byingPrice) {
		this.byingPrice = byingPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(int paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public double getTaxPaid() {
		return taxPaid;
	}

	public void setTaxPaid(double taxPaid) {
		this.taxPaid = taxPaid;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	@Override
	public String toString() {
		return "Sales [id=" + id + ", productCount=" + productCount + ", total=" + total + ", byingPrice=" + byingPrice
				+ ", discount=" + discount + ", paymentMethod=" + paymentMethod + ", taxPaid=" + taxPaid + ", dateTime="
				+ dateTime + ", productId=" + productId + ", customerId=" + customerId + ", staffId=" + staffId + "]";
	}
	
}
