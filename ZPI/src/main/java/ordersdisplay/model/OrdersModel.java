package ordersdisplay.model;

import java.util.Date;
import java.util.List;
import java.util.Observable;

import org.parse4j.ParseException;
import org.parse4j.ParseQuery;
import org.parse4j.callback.FindCallback;

import other.DataBaseConnection;
import model.Order;

public class OrdersModel extends Observable implements Runnable{
	
	OrderChanges orderChanges;
	boolean stop;
	Thread t;
	Date lastUpdated;
	List<Order> actualOrders;
	
	public OrdersModel(){

	}

	public void initialize(){
		final OrdersModel model = this;

		ParseQuery<Order> query = ParseQuery.getQuery(Order.class);
		query.orderByDescending("updatedAt");
		query.findInBackground(new FindCallback<Order>() {
			public void done(List<Order> scoreList, ParseException e) {
				if (e == null) {
					if(scoreList != null && scoreList.size() > 0){
						orderChanges = new OrderChanges(scoreList, 0);
						model.setChanged();
						model.notifyObservers();
						System.out.println("Znaleziono: " + scoreList.size() + " obiektów");
						
					}else{
//						System.out.println("Pusta baza");
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
			t = new Thread(this, "OrdersModelThread");
			t.start();
		}
	}

	public void stop(){
		stop = true;
	}

	/**  flaga: 0 - nowe wiersze, 1 - edycja wiersza, 2 - i to i to, 3 - usunięcie wiersza najlepiej szukać po PESELu) **/
	public OrderChanges getChanges(){
		// na końcu musi być flaga czy tylko edycja statusu, czy nowy kierowca online
		OrderChanges changes = orderChanges;
		if(changes != null){
			lastUpdated = changes.getOrders().get(0).getUpdatedAt();
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
			final OrdersModel model = this;

			ParseQuery<Order> query = ParseQuery.getQuery(Order.class);
			query.whereGreaterThan("updatedAt", lastUpdated);			
			query.findInBackground(new FindCallback<Order>() {
				public void done(List<Order> scoreList, ParseException e) {
					if (e == null) {
						if(scoreList != null && scoreList.size() > 0){							
							orderChanges = new OrderChanges(scoreList, 1);
							model.setChanged();
							model.notifyObservers();
							System.out.println("Zmieniono: " + scoreList.size() + " obiektów");							
						}else{
//							System.out.println("Pusta baza");
						}
					} else {

					}
				}
			});
		}
	}
	
	public List<Order> getActualData(){
		actualOrders = null;		
		DataBaseConnection.initialize();
		
		ParseQuery<Order> query = ParseQuery.getQuery(Order.class);		
		query.orderByDescending("updatedAt");
		try {
			actualOrders = query.find();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return actualOrders;
	}


}
