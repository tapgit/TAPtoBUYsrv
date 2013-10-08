package models;

public class ProductForSale extends Product {
	private int startingQuantity;
	private int remainingQuantity;
	private double instantPrice;


	public ProductForSale(int id, String title, String timeDuration,
			boolean timeEnded, double shippingPrice, String product,
			String model, String brand, String dimensions, String description,
			String imgLink, String sellerUsername, double sellerRate,
			int startingQuantity, int remainingQuantity, double instantPrice) {
		super(id, title, timeDuration, timeEnded, shippingPrice, product,
				model, brand, dimensions, description, imgLink, sellerUsername,
				sellerRate);
		this.startingQuantity = startingQuantity;
		this.remainingQuantity = remainingQuantity;
		this.instantPrice = instantPrice;
	}
	public int getStartingQuantity() {
		return startingQuantity;
	}
	public void setStartingQuantity(int startingQuantity) {
		this.startingQuantity = startingQuantity;
	}
	public int getRemainingQuantity() {
		return remainingQuantity;
	}
	public void setRemainingQuantity(int remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}
	public double getInstantPrice() {
		return instantPrice;
	}
	public void setInstantPrice(double instantPrice) {
		this.instantPrice = instantPrice;
	}
	
}
