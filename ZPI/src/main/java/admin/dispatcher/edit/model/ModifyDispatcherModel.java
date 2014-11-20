package admin.dispatcher.edit.model;

import model.Dispatcher;

import org.parse4j.ParseException;

public class ModifyDispatcherModel {
	public void editDispatcher(Dispatcher dispatcher, String[] values){
		dispatcher.setName(values[0]);
		dispatcher.setSurname(values[1]);
		dispatcher.setPESEL(new Long(values[4]));
		dispatcher.setUser(values[7]);
		try {
			dispatcher.save();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
