package admin.dispatcher.add.model;

import model.Dispatcher;

import org.parse4j.ParseException;
import org.parse4j.ParsePointer;

import other.DataBaseConnection;

public class AddDispatcherModel {
	
	public void initialize(){
		DataBaseConnection.initialize();
	}

	public void addDispatcher(String name, String surname, String pesel, ParsePointer user){
		Dispatcher dispatcher = new Dispatcher();
		dispatcher.setName(name);
		dispatcher.setSurname(surname);
		dispatcher.setPESEL(new Long(pesel));
		dispatcher.put("userId", user);
		try {
			dispatcher.save();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//userId

	}
}
