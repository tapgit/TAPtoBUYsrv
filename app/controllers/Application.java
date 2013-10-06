package controllers;

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
    	    String username = json.findPath("username").getTextValue();
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
    	return TODO;
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
    
}
