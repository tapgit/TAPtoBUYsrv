package models;

public class ProductForAuction extends Product {
	private double startinBidPrice;
	private double currentBidPrice;
	private int totalBids;
	private double bidRate;


	public ProductForAuction(int id, String title, String timeDuration,
			boolean timeEnded, double shippingPrice, String product,
			String model, String brand, String dimensions, String description,
			String imgLink, String sellerUsername, double sellerRate,
			double startinBidPrice, double currentBidPrice, int totalBids,
			double bidRate) {
		super(id, title, timeDuration, timeEnded, shippingPrice, product,
				model, brand, dimensions, description, imgLink, sellerUsername,
				sellerRate);
		this.startinBidPrice = startinBidPrice;
		this.currentBidPrice = currentBidPrice;
		this.totalBids = totalBids;
		this.bidRate = bidRate;
	}

	public double getStartinBidPrice() {
		return startinBidPrice;
	}

	public void setStartinBidPrice(double startinBidPrice) {
		this.startinBidPrice = startinBidPrice;
	}

	public double getCurrentBidPrice() {
		return currentBidPrice;
	}

	public void setCurrentBidPrice(double currentBidPrice) {
		this.currentBidPrice = currentBidPrice;
	}

	public int getTotalBids() {
		return totalBids;
	}

	public void setTotalBids(int totalBids) {
		this.totalBids = totalBids;
	}

	public double getBidRate() {
		return bidRate;
	}

	public void setBidRate(double bidRate) {
		this.bidRate = bidRate;
	}
	
}
