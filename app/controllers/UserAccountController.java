package controllers;

import models.Address;
import models.CreditCard;
import models.User;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import testmodels.Test;

public class UserAccountController extends Controller {

	public static Result checkLogin(){
		JsonNode json = request().body().asJson();
		if(json == null) {
			return badRequest("Expecting Json data");//400
		} 
		else {
			String username = json.findPath("username").getTextValue();//json.get("username").getTextValue();
			String password = json.findPath("password").getTextValue();
			ObjectNode respJson = Json.newObject();
			if(username.equals("lolo") && password.equals("bond")){
				respJson.put("admin", false);
				respJson.put("id", 0);
				return ok(respJson);//200 (send client the userId of the user that's being signed in)
			}
			else if(username.equals("admin") && password.equals("admin")){
				respJson.put("admin", true);
				respJson.put("id", 1);
				return ok(respJson);
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

			//			String firstname = json.get("firstname").getTextValue();
			//			String lastname = json.get("lastname").getTextValue();
			//			String username = json.get("username").getTextValue();
			//			String password = json.get("password").getTextValue();
			//			String email = json.get("email").getTextValue();
			//			//			//Shipping Address
			//			JsonNode shippingAddress = json.get("shipping_address");
			//			String shipCountry = shippingAddress.get("country").getTextValue();
			//			//FALTA		String shipContactName = shippingAddress.get("contact_name").getTextValue();
			//			String shipStreet = shippingAddress.get("street").getTextValue();
			//			String shipCity = shippingAddress.get("city").getTextValue();
			//			String shipState = shippingAddress.get("state").getTextValue();
			//			String shipZipCode = shippingAddress.get("zip_code").getTextValue();
			//			String shipTelephone = shippingAddress.get("telephone").getTextValue();
			//			//Billing Address
			//			JsonNode billingAddress = json.get("billing_address");
			//			String billCountry = billingAddress.get("country").getTextValue();
			//			//FALTA     String billContactName = billingAddress.get("contact_name").getTextValue();
			//			String billStreet = billingAddress.get("street").getTextValue();
			//			String billCity = billingAddress.get("city").getTextValue();
			//			String billState = billingAddress.get("state").getTextValue();
			//			String billZipCode = billingAddress.get("zip_code").getTextValue();
			//			String billTelephone = billingAddress.get("telephone").getTextValue();
			//			//			//Credit Card
			//			JsonNode creditCard = json.get("credit_card");
			//			String creditCardNumber = creditCard.get("number").getTextValue();
			//			String creditCardHoldersName = creditCard.get("holders_name").getTextValue();
			//			String creditCardExpDate = creditCard.get("exp_date").getTextValue();		


			//create user with his/her cart
			return created();//201
		}

	}

	public static Result getUserAccountInfo(int userId){
		if(userId!=0){
			return notFound("User not found");//404
		}
		User theUser = Test.getUser();
		return ok(Json.toJson(theUser));//200 respond with test user
	}
	public static Result updateUserAccount(int userId){
		JsonNode json = request().body().asJson();
		if(json == null) {
			return badRequest("Expecting Json data");//400
		} 
		else if(userId!=0){
			return notFound("User not found");//404
		}
		else{

			return ok("User account has been updated");//200
		}
	}
	public static Result deleteUserAccount(int userId){
		if(userId!=0){
			return notFound("User not found");//404
		}
		else{
			
			
			return noContent();//204 (user account deleted successfully)
		}
	}

//	private static User jsonToUser(JsonNode json){
	
	//			String firstname = json.get("firstname").getTextValue();
	//			String lastname = json.get("lastname").getTextValue();
	//			String username = json.get("username").getTextValue();
	//			String password = json.get("password").getTextValue();
	//			String email = json.get("email").getTextValue();
	//			//			//Shipping Address
	//			JsonNode shippingAddress = json.get("shipping_address");
	//			String shipCountry = shippingAddress.get("country").getTextValue();
	//			//FALTA		String shipContactName = shippingAddress.get("contact_name").getTextValue();
	//			String shipStreet = shippingAddress.get("street").getTextValue();
	//			String shipCity = shippingAddress.get("city").getTextValue();
	//			String shipState = shippingAddress.get("state").getTextValue();
	//			String shipZipCode = shippingAddress.get("zip_code").getTextValue();
	//			String shipTelephone = shippingAddress.get("telephone").getTextValue();
	//			//Billing Address
	//			JsonNode billingAddress = json.get("billing_address");
	//			String billCountry = billingAddress.get("country").getTextValue();
	//			//FALTA     String billContactName = billingAddress.get("contact_name").getTextValue();
	//			String billStreet = billingAddress.get("street").getTextValue();
	//			String billCity = billingAddress.get("city").getTextValue();
	//			String billState = billingAddress.get("state").getTextValue();
	//			String billZipCode = billingAddress.get("zip_code").getTextValue();
	//			String billTelephone = billingAddress.get("telephone").getTextValue();
	//			//			//Credit Card
	//			JsonNode creditCard = json.get("credit_card");
	//			String creditCardNumber = creditCard.get("number").getTextValue();
	//			String creditCardHoldersName = creditCard.get("holders_name").getTextValue();
	//			String creditCardExpDate = creditCard.get("exp_date").getTextValue();
//
//	}
	
	public static Result test(){
		User theUser = Test.getUser();
		JsonNode json = Json.toJson(theUser);

		
		
		
		
		return ok(json);
	}


}
