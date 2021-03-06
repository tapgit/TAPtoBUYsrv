package models;

public class User {
	private int id;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String email;
	
	private Address[] shipping_address;
	private CreditCard[] credit_card;
	public User(int id, String firstname, String lastname, String username,
			String password, String email, Address[] shipping_address,
			CreditCard[] credit_card) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.shipping_address = shipping_address;
		this.credit_card = credit_card;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public Address[] getShipping_address() {
		return shipping_address;
	}
	public void setShipping_address(Address[] shipping_address) {
		this.shipping_address = shipping_address;
	}
	public CreditCard[] getCredit_card() {
		return credit_card;
	}
	public void setCredit_card(CreditCard[] credit_card) {
		this.credit_card = credit_card;
	}
	
}
