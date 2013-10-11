package controllers;

import java.io.File;
import java.util.ArrayList;

import models.Product;
import models.ProductForAuction;
import models.ProductForAuctionInfo;
import models.ProductForSale;
import models.ProductForSaleInfo;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import play.*;
import play.mvc.*;
import play.libs.Json;
import views.html.*;

public class Application extends Controller {

	public static String scaledImagesDir = "C:\\Users\\Kidany\\Documents\\GitHub\\git\\TAPtoBUYsrv\\images\\scaled\\";
	public static String imagesDir = "C:\\Users\\Kidany\\Documents\\GitHub\\git\\TAPtoBUYsrv\\images\\";
	
	public static Result index() {
		return ok(index.render("Your new application is ready."));
	}

	public static Result checkLogin(){
		JsonNode json = request().body().asJson();
		if(json == null) {
			return badRequest("Expecting Json data");//400
		} 
		else {
			String username = json.findPath("username").getTextValue();//json.get("username").getTextValue();
			String password = json.findPath("password").getTextValue();
			if(username.equals("lolo") && password.equals("bond")){
				return ok("You're now signed in");//200
			}
			else{
				return unauthorized("Bad username or password");//401
			}
		}
	}
	public static Result register(){
		JsonNode json = request().body().asJson();
		if(json == null) {
			return badRequest("Expecting Json data");//400
		} 
		else {

			String firstname = json.get("firstname").getTextValue();
			String lastname = json.get("lastname").getTextValue();
			String username = json.get("username").getTextValue();
			String password = json.get("password").getTextValue();
			String email = json.get("email").getTextValue();
			//			//Shipping Address
			JsonNode shippingAddress = json.get("shipping_address");
			String shipCountry = shippingAddress.get("country").getTextValue();
			//FALTA		String shipContactName = shippingAddress.get("contact_name").getTextValue();
			String shipStreet = shippingAddress.get("street").getTextValue();
			String shipCity = shippingAddress.get("city").getTextValue();
			String shipState = shippingAddress.get("state").getTextValue();
			String shipZipCode = shippingAddress.get("zip_code").getTextValue();
			String shipTelephone = shippingAddress.get("telephone").getTextValue();
			//Billing Address
			JsonNode billingAddress = json.get("billing_address");
			String billCountry = billingAddress.get("country").getTextValue();
			//FALTA     String billContactName = billingAddress.get("contact_name").getTextValue();
			String billStreet = billingAddress.get("street").getTextValue();
			String billCity = billingAddress.get("city").getTextValue();
			String billState = billingAddress.get("state").getTextValue();
			String billZipCode = billingAddress.get("zip_code").getTextValue();
			String billTelephone = billingAddress.get("telephone").getTextValue();
			//			//Credit Card
			JsonNode creditCard = json.get("credit_card");
			String creditCardNumber = creditCard.get("number").getTextValue();
			String creditCardHoldersName = creditCard.get("holders_name").getTextValue();
			String creditCardExpDate = creditCard.get("exp_date").getTextValue();		


			//create user with his/her cart
			return created();//201
		}

	}
	public static Result mainCategories(){
		return categories(null,"All");
	}
	public static Result categories(String parentCat, String childCat){
		ObjectNode json = Json.newObject();

		//primer nivel
		if(parentCat==null && childCat.equals("All")){
			json.put("Books", true);
			json.put("Electronics", true);
			json.put("Computers", true);
			json.put("Clothing", true);
			json.put("Shoes", true);
			json.put("Sports", true);
		}
		//segundo nivel
		else if(parentCat.equals("All") && childCat.equals("Books")){
			json.put("Children", false);
			json.put("Fiction", false);
			json.put("Technology", false);
			json.put("Business", false);
		}
		else if(parentCat.equals("All") && childCat.equals("Electronics")){
			json.put("TV", false);
			json.put("Audio", false);
			json.put("Phones", false);
			json.put("Cameras", false);
			json.put("Video", false);
		}
		else if(parentCat.equals("All") && childCat.equals("Computers")){
			json.put("Laptops", false);
			json.put("Desktops", false);
			json.put("Tablets", false);
			json.put("Printers", false);
		}
		else if(parentCat.equals("All") && childCat.equals("Clothing")){
			json.put("Children", false);
			json.put("Men", true);
			json.put("Women", true);
		}
		else if(parentCat.equals("All") && childCat.equals("Shoes")){
			json.put("Children", false);
			json.put("Women", false);
			json.put("Men", false);
		}
		else if(parentCat.equals("All") && childCat.equals("Sports")){
			json.put("Bicycles", true);
			json.put("Fishing", false);
			json.put("Baseball", false);
			json.put("Golf", false);
			json.put("Basketball", false);
		}
		//tercer nivel
		else if(parentCat.equals("Clothing") && childCat.equals("Men")){
			json.put("Shirts", false);
			json.put("Pants", false);
			json.put("Socks", false);
		}
		else if(parentCat.equals("Clothing") && childCat.equals("Women")){
			json.put("Shirts", false);
			json.put("Pants", false);
			json.put("Dresses", false);
		}
		//cuarto nivel
		else if(parentCat.equals("Sports") && childCat.equals("Bicycles")){
			json.put("Frames", false);
			json.put("Wheels", false);
			json.put("Helmet", false);
			json.put("Parts", false);
		}
		return ok(json);
	}

	public static Result search(String searchString){
		
		ArrayList<Product> items = getProductList();

		ObjectNode respJson = Json.newObject();
		ArrayNode array = respJson.arrayNode();

		ObjectNode itemJson = null;
		for(Product p: items){
			itemJson = Json.newObject();
			if(p instanceof ProductForSale){ //for sale
				itemJson.put("forBid", false);
			}
			else{ //for auction
				itemJson.put("forBid", true);
			}
			itemJson.putPOJO("item", Json.toJson(p));
			array.add(itemJson);
		}
		respJson.put("results", array);
		return ok(respJson);
	}

	public static Result getCartItems(int userId){	
		ArrayList<Product> itemsInCart = getCartItemsList();
		
		if(userId==0){
			ObjectNode respJson = Json.newObject();
			ArrayNode array = respJson.arrayNode();
			ObjectNode itemJson = null;
			
			for(Product p: itemsInCart){
				itemJson = Json.newObject();
				itemJson.putPOJO("item", Json.toJson(p));
				array.add(itemJson);
			}
			respJson.put("cart", array);
			return ok(respJson);//200
		}
		else{
			return notFound("No cart found related to that user id");//404
		}
	}
	public static Result addItemToCart(int userId, int productId){
		JsonNode json = request().body().asJson();
		if(json == null) {
			return badRequest("Expecting Json data");//400
		} 
		else {
			if(userId != 0){
				return notFound("No cart found related to that user id");//404
			}
			else if(!(productId >=0 && productId < 6)){
				return notFound("Product not found");//404
			}
			else{
				//Add item to cart
				
				return ok();//200
			}
		}
	}
	public static Result deleteItemFromCart(int userId, int productId){
		JsonNode json = request().body().asJson();
		if(json == null) {
			return badRequest("Expecting Json data");//400
		} 
		else {
			if(userId != 0){
				return notFound("No cart found related to that user id");//404
			}
			else if(!(productId >=0 && productId < 6)){
				return notFound("Product not found");//404
			}
			else{
				//Delete item from cart
				
				return noContent();//200
			}
		}
	}
	
	public static Result getSellProduct(int productId){
		return TODO;
	}

	public static Result getProductInfo(int productId){
		ArrayList<Product> productInfos =getProductInfoList();
		int target = -1;
		for(Product pInfo: productInfos){
			if(pInfo.getId( )== productId){
				target = pInfo.getId();
				break;
			}
		}
		if(target == -1){
			return notFound("No product found with the requested id");//404
		}
		else{
			ObjectNode itemInfoJson = Json.newObject();
			if(productInfos.get(target) instanceof ProductForSaleInfo){//for sale
				itemInfoJson.put("forBid", false);
			}
			else{//for auction
				itemInfoJson.put("forBid", true);
			}
			itemInfoJson.putPOJO("productInfo", Json.toJson(productInfos.get(target)));
			return ok(itemInfoJson);//200
		}
	}
	
	public static Result getUserAccount(String id){
		return TODO;
	}
	public static Result updateUserAccount(String id){
		return TODO;
	}
	public static Result deleteUserAccount(String id){
		return TODO;
	}

	private static ArrayList<Product> getProductList(){
		String scaledImgDir = "http://10.0.2.2:9000/images/scaled/";

		Product item1 = new ProductForSale(0,"iPhone 5s black new", "12d 5h", 9.99, scaledImgDir+"img1.jpg", "juanitoManito77", 4.5, 10, 599.00);
		Product item2 = new ProductForAuction(1,"Database System Concepts 6.ed", "10h 20m",0, scaledImgDir+ "img4.jpg", "loloLopez13", 3.0, 0.99, 24.99,11);
		Product item3 = new ProductForAuction(2,"Samsumg Galaxy 4s used(like new)", "20m 33s", 0, scaledImgDir+ "img2.jpg", "Kidobv", 5.0, 0.99, 500.99, 60);
		Product item4 = new ProductForSale(3,"Java concepts horstmann 6 ed hardcover", "1d 3h", 3.99, scaledImgDir+ "img5.jpg", "Apu Diaz", 2.5, 5, 79.99);
		Product item5 = new ProductForAuction(4,"Samsumg Galaxy 4s unlocked", "2d 5h", 0, scaledImgDir+ "img3.jpg", "bondLolo", 4.8, 9.99, 299.99, 29);
		Product item6 = new ProductForSale(5,"iPad 4 (new unopened)", "10d 3h", 10.99, scaledImgDir+ "img6.jpg", "YangXi", 5.80, 50, 499.99);

		ArrayList<Product> items = new ArrayList<Product>();
		items.add(item1);
		items.add(item2);
		items.add(item3);
		items.add(item4);
		items.add(item5);
		items.add(item6);
		return items;
	}
	private static ArrayList<Product> getProductInfoList(){
		String imgDir = "http://10.0.2.2:9000/images/";
		Product productInfo1 = new ProductForSaleInfo(0,"iPhone 5s black new", "12d 5h", 9.99, imgDir+"img1.jpg", "juanitoManito77", 4.5, 10, 599.00, "iPhone", "5s", "Apple", "10x5", "Brand new black iphone 5s");
		Product productInfo2 = new ProductForAuctionInfo(1,"Database System Concepts 6.ed", "10h 20m",0, imgDir+ "img4.jpg", "loloLopez13", 3.0, 0.99, 24.99,11, "DataBase System Concepts", "6th", "Wiley", "10x5", "Brand new international 6th edition..");
		Product productInfo3 = new ProductForAuctionInfo(2,"Samsumg Galaxy 4s used(like new)", "20m 33s", 0, imgDir+ "img2.jpg", "Kidobv", 5.0, 0.99, 500.99, 60, "Samsung Galaxy", "4s", "Samsung", "10x5", "Used(like new) samsung galaxy..");
		Product productInfo4 = new ProductForSaleInfo(3,"Java concepts horstmann 6 ed hardcover", "1d 3h", 3.99, imgDir+ "img5.jpg", "Apu Diaz", 2.5, 5, 79.99, "Java Concepts", "6th", "Wiley", "10x5", "Brand new hardcover book..");
		Product productInfo5 = new ProductForAuctionInfo(4,"Samsumg Galaxy 4s unlocked", "2d 5h", 0, imgDir+ "img3.jpg", "bondLolo", 4.8, 9.99, 299.99, 29,"Samsung Galaxy", "4s", "Samsung", "10x5", "Samsung galaxy 4s unlocked working perfectly..");
		Product productInfo6 = new ProductForSaleInfo(5,"iPad 4 (new unopened)", "10d 3h", 10.99, imgDir+ "img6.jpg", "YangXi", 5.80, 50, 499.99, "iPad", "4", "Apple", "10x5", "Brand new black iPad 4");
		ArrayList<Product> productInfos = new ArrayList<Product>();
		productInfos.add(productInfo1);
		productInfos.add(productInfo2);
		productInfos.add(productInfo3);
		productInfos.add(productInfo4);
		productInfos.add(productInfo5);
		productInfos.add(productInfo6);
		return productInfos;
	}
	private static ArrayList<Product> getCartItemsList(){
		ArrayList<Product> items = getProductList();
		ArrayList<Product> cartItems = new ArrayList<Product>();
		cartItems.add(items.get(0));
		cartItems.add(items.get(2));
		cartItems.add(items.get(5));
		return cartItems;
	}
	
	public static Result getImage(String imageName){
		if(imageName.equals("img1.jpg")||imageName.equals("img2.jpg")||imageName.equals("img3.jpg")||imageName.equals("img4.jpg")||
				imageName.equals("img5.jpg")||imageName.equals("img6.jpg")){
			return ok(new File(imagesDir + imageName));//200
		}
		else{
			return notFound("No image found with the requested name");//404
		}
	}

	public static Result getScaledImage(String imageName){
		if(imageName.equals("img1.jpg")||imageName.equals("img2.jpg")||imageName.equals("img3.jpg")||imageName.equals("img4.jpg")||
				imageName.equals("img5.jpg")||imageName.equals("img6.jpg")){
			return ok(new File(scaledImagesDir + imageName));//200
		}
		else{
			return notFound("No image found with the requested name");//404
		}
	}

}
