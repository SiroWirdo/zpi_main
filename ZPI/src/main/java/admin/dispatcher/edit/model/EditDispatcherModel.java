package admin.dispatcher.edit.model;

import java.util.Date;
import java.util.List;
import java.util.Observable;

import model.Dispatcher;

import org.parse4j.ParseException;
import org.parse4j.ParseQuery;
import org.parse4j.callback.FindCallback;

import other.DataBaseConnection;

public class EditDispatcherModel extends Observable {
	EditDispatcherChanges editDispatcherChanges;
	boolean stop;
	Thread t;
	Date lastUpdated;
	List<Dispatcher> actualDispatcher;
	public void initialize(){
		final EditDispatcherModel model = this;

		DataBaseConnection.initialize();

		ParseQuery<Dispatcher> query = ParseQuery.getQuery(Dispatcher.class);
		query.orderByDescending("updatedAt");
		query.findInBackground(new FindCallback<Dispatcher>() {
			public void done(List<Dispatcher> scoreList, ParseException e) {
				if (e == null) {
					if(scoreList != null && scoreList.size() > 0){
						editDispatcherChanges = new EditDispatcherChanges(scoreList, 0);
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

		//model.start();
	}
/*
	public void start(){
		stop = false;
		if(t == null){
			t = new Thread(this, "EditDispatcherModelThread");
			t.start();
		}
	}

	public void stop(){
		stop = true;
	}*/

	/**  flaga: 0 - nowe wiersze, 1 - edycja wiersza, 2 - i to i to, 3 - usunięcie wiersza najlepiej szukać po PESELu) **/
	public EditDispatcherChanges getChanges(){
		// na końcu musi być flaga czy tylko edycja statusu, czy nowy kierowca online
		EditDispatcherChanges changes = editDispatcherChanges;
		if(changes != null){
			lastUpdated = changes.getDispatchers().get(0).getUpdatedAt();
			System.out.println(lastUpdated);
		}
		//this.editDriverChanges = null;
		return changes;
	}

	/*@Override
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
			final EditDispatcherModel model = this;

			ParseQuery<Dispatcher> query = ParseQuery.getQuery(Dispatcher.class);
			query.whereGreaterThan("updatedAt", lastUpdated);
			query.findInBackground(new FindCallback<Dispatcher>() {
				public void done(List<Dispatcher> scoreList, ParseException e) {
					if (e == null) {
						if(scoreList != null && scoreList.size() > 0){
							editDispatcherChanges = new EditDispatcherChanges(scoreList, 1);
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
	}*/

	public List<Dispatcher> getActualData(){
		actualDispatcher = null;
		DataBaseConnection.initialize();

		ParseQuery<Dispatcher> query = ParseQuery.getQuery(Dispatcher.class);
		query.orderByDescending("updatedAt");
		try {
			actualDispatcher = query.find();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return actualDispatcher;
	}

	public Dispatcher getDispatcherByPesel(long pesel){
		ParseQuery<Dispatcher> query = ParseQuery.getQuery(Dispatcher.class);
		query.whereEqualTo("PESEL", pesel);
		List<Dispatcher> dispatchers;
		Dispatcher dispatcher = null;
		try {
			dispatchers = query.find();
			dispatcher = dispatchers.get(0);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dispatcher;
	}

	public void refresh(){
		final EditDispatcherModel model = this;

		ParseQuery<Dispatcher> query = ParseQuery.getQuery(Dispatcher.class);
		query.whereGreaterThan("updatedAt", lastUpdated);
		query.findInBackground(new FindCallback<Dispatcher>() {
			public void done(List<Dispatcher> scoreList, ParseException e) {
				if (e == null) {
					if(scoreList != null && scoreList.size() > 0){
						editDispatcherChanges = new EditDispatcherChanges(scoreList, 1);
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
