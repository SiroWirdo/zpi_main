package admin.driver.edit.model;

import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

import model.Driver;

public class ModifyDriverModel {

	public void editDriver(Driver driver, String[] values){
		driver.setName(values[0]);
		driver.setSurname(values[1]);
		driver.setPhoneNumber(new Long(values[2]));
		driver.setLicenseNumber(values[3]);
		driver.setPESEL(new Long(values[4]));
		driver.setStatus(new Integer(values[5]));
		driver.setCar(values[6]);
	//	driver.setUser(values[7]);
		try {
			driver.save();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getUserMail(String userId){
		String mail = "";
		ParseObject user = null;
		if(!userId.equals("")){
			ParseQuery<ParseObject> query = ParseQuery.getQuery("users");
			query.whereEqualTo("objectId", userId);
			try {
				user = query.find().get(0);
				
				mail = (String)user.get("email");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return mail;
	}
}
