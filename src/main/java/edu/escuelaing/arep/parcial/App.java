package edu.escuelaing.arep.parcial;

import static spark.Spark.*;
import org.json.*;
import spark.Request;
import java.util.ArrayList;
import java.util.List;
import spark.Response;
public class App {
	public static void main(String[] args) {
		port(getPort());
		get("/parcialVillamarinR", (req, res) -> inputDataPage(req, res));
		get("/results", (req, res) -> resultsPage(req, res));  
	}
	static int getPort() {
		 if (System.getenv("PORT") != null) {
			 return Integer.parseInt(System.getenv("PORT"));
		 }
		 return 4567; 
		 }

	private static String inputDataPage(Request req, Response res) {
        String page
                = "<!DOCTYPE html>"
                + "<html>"
                + "<body style=\"background-color:#B9C5BC;\">"
                +"<font align=\"center\" color=\"Green\" face=\"Comic Sans MS,arial\">"
                + "<h1>Calculadora Trigonometrica</h1>"
                + "<form action=\"/results\">"
                + "  Ingrese unicamente las estas funciones trigonometricas 'sin','cos' o 'tan' seguido de un - y el numero en radian (EJ: sin-0) <br>"
                + "  <input type=\"text\" name=\"numbers\" >"
                + "  <br><br>"
                + "  <input type=\"submit\" value=\"Submit\">"
                + "</form>"
                + "</body>"
                + "</html>";
        return page;
    }
	private static JSONObject resultsPage(Request req, Response res) {
        String[] listaFunctions= req.queryParams("numbers").split("-");
        double number = Double.parseDouble(listaFunctions[1]);
        Seno sinFuc = new Seno();
        Coseno cosFuc = new Coseno();
        Tangente tanFuc = new Tangente();
        TrigonometricFunctions trigoFunc = new TrigonometricFunctions();
        double trigonometricResult = 0;

        if (listaFunctions[0].equals("sin") ){
        	trigonometricResult = sinFuc.sin(number);
        } else if (listaFunctions[0].equals("cos") ){
        	trigonometricResult = cosFuc.cos(number);
        } else if (listaFunctions[0].equals("tan") ){
        	trigonometricResult= tanFuc.tan(number);
        }else {
        	trigonometricResult = trigoFunc.ninguna(number);
        }

        res.header("Content-Type","application/json");        
        
        JSONObject jsonTrigo = new JSONObject();
        jsonTrigo.put("el resultado de " + listaFunctions[0] +"-" + number +" es : ", trigonometricResult);
        
                
        
        return jsonTrigo;
    }

		
	
}
