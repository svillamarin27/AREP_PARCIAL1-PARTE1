package edu.escuelaing.arep.parcial;

import static spark.Spark.*;
import spark.Request;
import spark.Response;
public class App {
	public static void main(String[] args) {
		port(getPort());
		
		get("/parcialVillamarinR", (req, res) -> "Hello Heroku");
	}
	static int getPort() {
		 if (System.getenv("PORT") != null) {
			 return Integer.parseInt(System.getenv("PORT"));
		 }
		 return 4567; 
		 }

}
