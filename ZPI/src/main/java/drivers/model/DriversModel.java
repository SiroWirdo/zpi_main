package drivers.model;

import java.util.Observable;

import other.DataBaseConnection;

public class DriversModel extends Observable {
	
	public DriversModel(){
		
	}
	
	public void initialize(){
		DataBaseConnection.initialize();
	}
}
