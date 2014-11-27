package validation;

import java.util.List;

import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

public class UserValidation {
	
	public static boolean isUserNameUnique(String username){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("users");
		query.whereEqualTo("username", username);
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
