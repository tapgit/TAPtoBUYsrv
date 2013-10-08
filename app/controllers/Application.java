package controllers;

import java.util.ArrayList;

import models.Product;
import models.ProductForAuction;
import models.ProductForSale;

import org.codehaus.jackson.JsonNode;
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
			//create user
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
		
		Product item1 = new ProductForSale(0, "iPhone 5s new!", "7d 1h",
				false, 7.99,"iPhone", "5s",
				"Apple", "1 x 10", "Brand new white iphone with 16gb",
				"http://www.server.com/image.jpg","juanito77",5.0, 10, 9, 300.50);
		Product item2 = new ProductForAuction(1, "Galaxy s4 blue new!", "2w 5d 1h",
				false, 0, "Galaxy", "s4", "Sansung", "3.5 x 5", "Brand new unonpened black unlocked", 
				"http://www.server.com/image.jpg","", 1.0 , 50.99, 299.50, 50, 0.7);
	

		
		ObjectNode json = Json.newObject();
		
		ObjectNode item1Json = Json.newObject();
		item1Json.put("forBid", false);
		item1Json.putPOJO("item", Json.toJson(item1));
		json.put("item-001", item1Json);
		
		ObjectNode item2Json = Json.newObject();
		item2Json.put("forBid", true);
		item2Json.putPOJO("item", Json.toJson(item2));
		json.put("item-002", item2Json);

//		ObjectNode item3Json = Json.newObject();
//		item2Json.put("forBid", false);
//		item2Json.putPOJO("item", Json.toJson(item2));
//		json.put("item-003", item2Json);
//
//		ObjectNode ite4Json = Json.newObject();
//		item2Json.put("forBid", true);
//		item2Json.putPOJO("item", Json.toJson(item2));
//		json.put("item-002", item2Json);
//
//		ObjectNode item5Json = Json.newObject();
//		item2Json.put("forBid", true);
//		item2Json.putPOJO("item", Json.toJson(item2));
//		json.put("item-002", item2Json);
		return ok(json);
	}
	
	public static Result getCartItems(String cartID){
		return search("nada");

	}
	public static Result getProductInfo(String productID){
		return search("nada");
	}
}
