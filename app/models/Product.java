package models;


public class Product {
	private int id;
	private String title;
	private String timeDuration;
	private boolean timeEnded;
	private double shippingPrice; //free=> 0
	//item_info
	private String product;
	private String model;
	private String brand;
	private String dimensions;
	private String description;
	private String imgLink;
	
	public Product(int id, String title, String timeDuration, boolean timeEnded,
			double shippingPrice, String product, String model, String brand,
			String dimensions, String description, String imgLink) {
		super();
		this.id = id;
		this.title = title;
		this.timeDuration = timeDuration;
		this.timeEnded = timeEnded;
		this.shippingPrice = shippingPrice;
		this.product = product;
		this.model = model;
		this.brand = brand;
		this.dimensions = dimensions;
		this.description = description;
		this.imgLink = imgLink;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTimeDuration() {
		return timeDuration;
	}
	public void setTimeDuration(String timeDuration) {
		this.timeDuration = timeDuration;
	}
	public boolean isTimeEnded() {
		return timeEnded;
	}
	public void setTimeEnded(boolean timeEnded) {
		this.timeEnded = timeEnded;
	}
	public double getShippingPrice() {
		return shippingPrice;
	}
	public void setShippingPrice(double shippingPrice) {
		this.shippingPrice = shippingPrice;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDimensions() {
		return dimensions;
	}
	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImgLink() {
		return imgLink;
	}
	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}
}
