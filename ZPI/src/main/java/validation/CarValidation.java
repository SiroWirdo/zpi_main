package validation;

import java.util.List;

import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

public class CarValidation {
	
	public static boolean isRegistrationNumberUnique(String registrationNumber){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Car");
		query.whereEqualTo("registrationNumber", registrationNumber);
		try {
			List<ParseObject> found = query.find();
			if(found != null){
				return false;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	public static boolean isSideNumberUnique(String sideNumber){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Car");
		query.whereEqualTo("sideNumber", new Long(sideNumber));
		try {
			List<ParseObject> found = query.find();
			if(found != null){
				return false;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
}
