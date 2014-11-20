package other;

import model.Car;
import model.Customer;
import model.Dispatcher;
import model.Driver;
import model.Order;

import org.parse4j.Parse;
import org.parse4j.ParseUser;
import org.parse4j.util.ParseRegistry;

public class DataBaseConnection {
	public final static String APPLICATION_ID = "Ljocg29Z4B0Nihu6UgGFPDzyMWsd2bGEGvlWjG3U";
	public final static String REST_API_KEY = "Oh8pFyCHvyGfypVSOw3gk9JIBuq3HkmQceN6wVtr";
	
	public static void initialize(){
		ParseRegistry.registerSubclass(Order.class);
		ParseRegistry.registerSubclass(Car.class);
		ParseRegistry.registerSubclass(Dispatcher.class);
		ParseRegistry.registerSubclass(Driver.class);
		ParseRegistry.registerSubclass(Customer.class);
		ParseRegistry.registerSubclass(ParseUser.class);
		Parse.initialize(APPLICATION_ID, REST_API_KEY);
	}
}
