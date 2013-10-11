package models;

public class CreditCard {
	
	//private int id;
	private String number;
	private String holders_name;
	private String exp_date;
	public CreditCard(String number, String holders_name, String exp_date) {
		super();
		this.number = number;
		this.holders_name = holders_name;
		this.exp_date = exp_date;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getHolders_name() {
		return holders_name;
	}
	public void setHolders_name(String holders_name) {
		this.holders_name = holders_name;
	}
	public String getExp_date() {
		return exp_date;
	}
	public void setExp_date(String exp_date) {
		this.exp_date = exp_date;
	}
	
	

}
