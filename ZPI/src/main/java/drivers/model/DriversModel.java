package drivers.model;


import java.util.Date;
import java.util.List;
import java.util.Observable;

import model.Driver;

import org.parse4j.ParseException;
import org.parse4j.ParseQuery;
import org.parse4j.callback.FindCallback;

import other.DataBaseConnection;

public class DriversModel extends Observable implements Runnable {
	DriverChanges driverChanges;
	boolean stop;
	Thread t;
	Date lastUpdated;

	public DriversModel(){

	}

	public void initialize(){
		DataBaseConnection.initialize();

		ParseQuery<Driver> query = ParseQuery.getQuery(Driver.class);
		query.whereNotEqualTo("status", 5);
		query.orderByDescending("updatedAt");
		query.findInBackground(new FindCallback<Driver>() {
			public void done(List<Driver> scoreList, ParseException e) {
				if (e == null) {
					if(scoreList != null && scoreList.size() > 0){
						driverChanges = new DriverChanges(scoreList, 0);
						System.out.println("Znaleziono: " + scoreList.size() + " obiektów");
					}else{
						System.out.println("Pusta baza");
					}
				} else {

				}
			}
		});


	}

	public void start(){
		stop = false;
		if(t == null){
			t = new Thread(this, "DriversModelThread");
			t.start();
		}
	}

	public void stop(){
		stop = true;
	}

	/**  flaga: 0 - nowe wiersze, 1 - edycja wiersza, 2 - i to i to, 3 - usuniêcie wiersza najlepiej szukaæ po PESELu) **/
	public DriverChanges getChanges(){
		// na koñcu musi byæ flaga czy tylko edycja statusu, czy nowy kierowca online
		DriverChanges changes = driverChanges;
		if(changes != null){
			lastUpdated = changes.getDrivers().get(0).getUpdatedAt();
			System.out.println(lastUpdated);
		}
		//this.driverChanges = null;
		return changes;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!stop){

		}
	}
}
