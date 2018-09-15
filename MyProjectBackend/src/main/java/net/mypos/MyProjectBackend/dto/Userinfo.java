package net.mypos.MyProjectBackend.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;


@Entity
public class Userinfo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "first_name")
	@NotBlank(message = "Please enter the First Name!")
	private String firstName;
	
	@Column(name = "last_name")
	@NotBlank(message = "Please enter the Last Name!")
	private String lastName;
	
	private String role;
	private boolean enabled = true;
	
	@NotBlank(message = "Please enter a valid passcode. Only numbers allowed!")
	@Size(min = 4, max = 10, message = "Passcode lenght should be in minimum 4 and maximum 10.")
	private String password;
	
	@NotBlank(message = "Please enter the email!")
	private String email;
	
	@Column(name = "user_name")
	@NotBlank(message = "Please enter a valid user name!")
	private String userName;
	
	@NotBlank(message = "Please enter an address!")
	private String address;
	
	@Column(name = "contact_number")
	@NotBlank(message = "Please enter a contact Number!")
	private String contactNumber;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", first_name=" + firstName + ", last_name=" + lastName + ", role=" + role
				+ ", enabled=" + enabled + ", password=" + password + ", email=" + email + ", user_name=" + userName
				+ ", address=" + address + ", contact_number=" + contactNumber + "]";
	}
	
	
}
