package controllers;

import org.codehaus.jackson.JsonNode;

import play.*;
import play.mvc.*;

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
    	    if(username.equals("Lolo") && password.equals("bond")){
    	      return ok("You're now signed in");
    	    }
    	    else{
    	    	return unauthorized("Bad username or password");
    	    }
    	  }
    }
}
