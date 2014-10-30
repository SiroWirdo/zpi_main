package admin.driver.edit.model;

import java.util.Date;
import java.util.List;
import java.util.Observable;

import model.Driver;

import org.parse4j.ParseException;
import org.parse4j.ParseQuery;
import org.parse4j.callback.FindCallback;

import other.DataBaseConnection;

public class EditDriverModel extends Observable implements Runnable {
	EditDriverChanges editDriverChanges;
	boolean stop;
	Thread t;
	Date lastUpdated;
	List<Driver> actualDrivers;
	public void initialize(){
		final EditDriverModel model = this;

		DataBaseConnection.initialize();

		ParseQuery<Driver> query = ParseQuery.getQuery(Driver.class);
		query.orderByDescending("updatedAt");
		query.findInBackground(new FindCallback<Driver>() {
			public void done(List<Driver> scoreList, ParseException e) {
				if (e == null) {
					if(scoreList != null && scoreList.size() > 0){
						editDriverChanges = new EditDriverChanges(scoreList, 0);
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

		model.start();
	}

	public void start(){
		stop = false;
		if(t == null){
			t = new Thread(this, "EditDriverModelThread");
			t.start();
		}
	}

	public void stop(){
		stop = true;
	}

	/**  flaga: 0 - nowe wiersze, 1 - edycja wiersza, 2 - i to i to, 3 - usuniêcie wiersza najlepiej szukaæ po PESELu) **/
	public EditDriverChanges getChanges(){
		// na koñcu musi byæ flaga czy tylko edycja statusu, czy nowy kierowca online
		EditDriverChanges changes = editDriverChanges;
		if(changes != null){
			lastUpdated = changes.getDrivers().get(0).getUpdatedAt();
			System.out.println(lastUpdated);
		}
		//this.editDriverChanges = null;
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
			final EditDriverModel model = this;

			ParseQuery<Driver> query = ParseQuery.getQuery(Driver.class);
			query.whereGreaterThan("updatedAt", lastUpdated);
			query.findInBackground(new FindCallback<Driver>() {
				public void done(List<Driver> scoreList, ParseException e) {
					if (e == null) {
						if(scoreList != null && scoreList.size() > 0){
							editDriverChanges = new EditDriverChanges(scoreList, 1);
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

	public Driver getDriverByPesel(long pesel){
		ParseQuery<Driver> query = ParseQuery.getQuery(Driver.class);
		query.whereEqualTo("pesel", pesel);
		List<Driver> drivers;
		Driver driver = null;
		try {
			drivers = query.find();
			driver = drivers.get(0);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}
}
