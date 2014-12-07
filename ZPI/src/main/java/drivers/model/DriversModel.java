package drivers.model;


import java.util.Date;
import java.util.List;
import java.util.Observable;

import model.Driver;

import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;
import org.parse4j.callback.FindCallback;

import other.DataBaseConnection;

public class DriversModel extends Observable implements Runnable {
	DriverChanges driverChanges;
	boolean stop;
	Thread t;
	Date lastUpdated;
	List<Driver> actualDrivers;

	public DriversModel(){

	}

	public void initialize(){
		final DriversModel model = this;

		ParseQuery<Driver> query = ParseQuery.getQuery(Driver.class);
		query.orderByDescending("updatedAt");
		query.findInBackground(new FindCallback<Driver>() {
			public void done(List<Driver> scoreList, ParseException e) {
				if (e == null) {
					if(scoreList != null && scoreList.size() > 0){
						System.out.println("baza: " + scoreList.get(0).get("surname"));
						driverChanges = new DriverChanges(scoreList, 0);
						model.setChanged();
						model.notifyObservers();
						System.out.println("Znaleziono: " + scoreList.size() + " obiektów");
						
					}else{
						System.out.println("Pusta baza");
					}
				} else {

				}
			}
		});
		

		ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Driver");
		query2.whereEqualTo("objectId", "0n4mCIRXVO");
		query2.findInBackground(new FindCallback<ParseObject>() {
		    public void done(List<ParseObject> scoreList, ParseException e) {
		        if (e == null) {
		        	System.out.println("Baza z backgroundem: " + (String)scoreList.get(0).get("surname"));
		        } else {
		        	
		        }
		    }
		});
		
		model.start();
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

	/**  flaga: 0 - nowe wiersze, 1 - edycja wiersza, 2 - i to i to, 3 - usunięcie wiersza najlepiej szukać po PESELu) **/
	public DriverChanges getChanges(){
		// na końcu musi być flaga czy tylko edycja statusu, czy nowy kierowca online
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
		DataBaseConnection.initialize();

		while(!stop){
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			final DriversModel model = this;

			ParseQuery<Driver> query = ParseQuery.getQuery(Driver.class);
			query.whereGreaterThan("updatedAt", lastUpdated);			
			query.findInBackground(new FindCallback<Driver>() {
				public void done(List<Driver> scoreList, ParseException e) {
					if (e == null) {
						if(scoreList != null && scoreList.size() > 0){							
							driverChanges = new DriverChanges(scoreList, 1);
							model.setChanged();
							model.notifyObservers();
							System.out.println("Zmieniono: " + scoreList.size() + " obiektów");							
						}else{
							System.out.println("Pusta baza");
						}
					} else {

					}
				}
			});
		}
	}
	
	public List<Driver> getActualData(){
		actualDrivers = null;		
		DataBaseConnection.initialize();
		
		ParseQuery<Driver> query = ParseQuery.getQuery(Driver.class);		
		query.orderByDescending("updatedAt");
		try {
			actualDrivers = query.find();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return actualDrivers;
	}
}
