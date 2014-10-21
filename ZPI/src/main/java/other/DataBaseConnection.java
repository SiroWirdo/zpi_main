package other;

import org.parse4j.Parse;

public class DataBaseConnection {
	public final static String APPLICATION_ID = "Ljocg29Z4B0Nihu6UgGFPDzyMWsd2bGEGvlWjG3U";
	public final static String REST_API_KEY = "Oh8pFyCHvyGfypVSOw3gk9JIBuq3HkmQceN6wVtr";
	
	public static void initialize(){
		Parse.initialize(APPLICATION_ID, REST_API_KEY);
	}
}
