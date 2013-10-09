package controllers;

import java.io.File;
import java.util.ArrayList;

import models.Product;
import models.ProductForAuction;
import models.ProductForSale;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import play.*;
import play.mvc.*;
import play.libs.Json;
import views.html.*;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render("Your new application is ready."));
	}

	public static Result checkLogin(){
		JsonNode json = request().body().asJson();
		if(json == null) {
			return badRequest("Expecting Json data");
		} 
		else {
			String username = json.findPath("username").getTextValue();//json.get("username").getTextValue();
			String password = json.findPath("password").getTextValue();
			if(username.equals("lolo") && password.equals("bond")){
				return ok("You're now signed in");
			}
			else{
				return unauthorized("Bad username or password");
			}
		}
	}
	public static Result register(){
		JsonNode json = request().body().asJson();
		if(json == null) {
			return badRequest("Expecting Json data");
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
			return ok();
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

		Product item1 = new ProductForSale(0,"iPhone 5s", "12d 5h", 9.99, "http://127.0.0.1:9000/images/img1.jpg", 
				"juanitoManito77", 4.5, 10, 599.00);

		Product item2 = new ProductForAuction(1,"Samsumg Galaxy 4s", "5h 20m", 0, "http://127.0.0.1:9000/images/img2.jpg", 
				"loloLopez13", 3.0, 0.99, 357.99, 60, 0.55);
		Product item3 = new ProductForSale(2,"iPhone 5s", "12d 5h", 9.99, "http://127.0.0.1:9000/images/img1.jpg", 
				"Kebinbin", 4.5, 10, 100.00);

		Product item4 = new ProductForAuction(3,"Samsumg Galaxy 4s", "5h 20m", 0, "http://127.0.0.1:9000/images/img2.jpg", 
				"Kidobv", 3.0, 0.99, 500.99, 60, 0.55);
		Product item5 = new ProductForSale(4,"iPhone 5s", "12d 5h", 9.99, "http://127.0.0.1:9000/images/img1.jpg", 
				"YangXi", 4.5, 10, 443.00);

		Product item6 = new ProductForAuction(5,"Samsumg Galaxy 4s", "5h 20m", 0, "http://127.0.0.1:9000/images/img2.jpg", 
				"bondLolo", 3.0, 0.99, 999.99, 60, 0.55);

		ArrayList<Product> items = new ArrayList<Product>();
		items.add(item1);
		items.add(item2);
		items.add(item3);
		items.add(item4);
		items.add(item5);
		items.add(item6);
		
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

	public static Result getCartItems(String cartID){
		return search("nada");

	}
	public static Result updateCartItems(String cartID){
		return TODO;
	}
	public static Result getProductInfo(String productID){
		return search("nada");
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
	public static Result getImage(String imageName){
		String imgDir = "/home/cok0/git/TAPtoBUYsrv/images/default.jpg";
		if(imageName.equals("img1.jpg")){
			imgDir = "/home/cok0/git/TAPtoBUYsrv/images/img1.jpg";
		}
		else if(imageName.equals("img2.jpg")){
			imgDir = "/home/cok0/git/TAPtoBUYsrv/images/img2.jpg";
		}
		else if(imageName.equals("img3.jpg")){
			imgDir = "/home/cok0/git/TAPtoBUYsrv/images/img3.jpg";
		}	
		return ok(new File(imgDir));
	}
}
