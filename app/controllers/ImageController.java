package controllers;

import java.io.File;

import play.mvc.Controller;
import play.mvc.Result;
import testmodels.Test;

public class ImageController  extends Controller{
	public static Result getImage(String imageName){
		if(imageName.equals("img1.jpg")||imageName.equals("img2.jpg")||imageName.equals("img3.jpg")||imageName.equals("img4.jpg")||
				imageName.equals("img5.jpg")||imageName.equals("img6.jpg")){
			return ok(new File(Test.imagesDir + imageName));//200
		}
		else{
			return notFound("No image found with the requested name");//404
		}
	}

	public static Result getScaledImage(String imageName){
		if(imageName.equals("img1.jpg")||imageName.equals("img2.jpg")||imageName.equals("img3.jpg")||imageName.equals("img4.jpg")||
				imageName.equals("img5.jpg")||imageName.equals("img6.jpg")){
			return ok(new File(Test.imagesDir + "scaled/" + imageName));//200
		}
		else{
			return notFound("No image found with the requested name");//404
		}
	}
}
