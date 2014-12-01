package admin.dispatcher.edit.model;

import model.Dispatcher;

import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

public class ModifyDispatcherModel {
	public void editDispatcher(Dispatcher dispatcher, String[] values){
		dispatcher.setName(values[0]);
		dispatcher.setSurname(values[1]);
		dispatcher.setPESEL(new Long(values[2]));
		try {
			dispatcher.save();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*if(dispatcher.getUser() != null){
			String userId = dispatcher.getUser().getObjectId();
			ParseQuery<ParseObject> query = ParseQuery.getQuery("users");
			query.whereEqualTo("objectId", userId);
			try {
				ParseObject user = query.find().get(0);
				user.put("email", values[3]);
				user.save();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		
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
