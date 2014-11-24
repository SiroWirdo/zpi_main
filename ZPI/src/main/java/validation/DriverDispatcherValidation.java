package validation;

import java.util.List;

import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

public class DriverDispatcherValidation {
	public static boolean isPeselUnique(String pesel){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Driver");
		query.whereEqualTo("PESEL", new Long(pesel));
		try {
			List<ParseObject> found = query.find();
			if(found != null){
				return false;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		query = ParseQuery.getQuery("Dispatcher");
		query.whereEqualTo("PESEL", new Long(pesel));
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
	
	public static boolean isLicenseUnique(String license){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Driver");
		query.whereEqualTo("licenseNumber", license);
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
