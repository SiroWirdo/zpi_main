package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.Car;
import model.Driver;
import model.Order;

import org.parse4j.ParseException;
import org.parse4j.ParseGeoPoint;
import org.parse4j.ParseObject;
import org.parse4j.ParsePush;
import org.parse4j.ParseQuery;

import settings.Settings;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.util.PointList;

public class Algorithm implements Runnable {

	public static GraphHopper hopper;

	private Order order;
	private List<Driver> drivers;
	private List<DriverResponse> driversWithRouteData;
	private boolean isOrderAssigned;
	private Driver assignedDriver;
	private ParseGeoPoint customerPosition;
	private boolean stop;

	private final int NUMBER_CHOOSED_DRIVERS = 6;
	private final long WAITING_TIME_RSP_DRIVER = 30000;

	public Algorithm(Order order) {
		this.order = order;
		drivers = new ArrayList<Driver>();
		driversWithRouteData = new ArrayList<DriverResponse>();
		isOrderAssigned = false;
		assignedDriver = null;
		customerPosition = order.getPickupAddressGeo();
		stop = false;
	}
	
	public static void initializeGraphHopper(){
		hopper = new GraphHopper().forDesktop();
		hopper.setOSMFile("poland-latest.osm.pbf");
		hopper.setGraphHopperLocation("src/main/resources/");
		hopper.setEncodingManager(new EncodingManager(EncodingManager.CAR));
		hopper.importOrLoad();
	}

	@Override
	public void run() {
		while (!stop) {
			/*
			 * Pobranie z bazy wszystkich kierowców o statusie wolnym i z odpowiedni¹ "pojemnoœci¹" auta
			 */
			drivers = getAvailableDrivers();
			
			if (drivers != null && drivers.size() > 0) {
				System.out.println("Znaleziono dostêpnych driverów: " + drivers.size());
				
				/*
				 * Sortowanie kierowców po odleg³oœci w linii prostej od klienta
				 */
				sortListByDistanceInStraightLine(drivers);
				
				/*
				 * Wybranie okreœlonej liczby kierowcw, którzy s¹ najbli¿ej w linii prostej
				 */
				List<Driver> sortedDrivers = new ArrayList<Driver>(restrictListToTheBestResults(drivers));
				
				/*
				 * Wylicznie odleg³oœci po trasie
				 */
				driversWithRouteData = routeDistance(sortedDrivers);

				/*
				 * 
				 */
				doingNotifyDriver();
			} else {
				// TODO wyœwietl komunikat, ze nie ma dostepnych kierowców!
				System.out.println("Nie ma aktualnie dostêpnych kierowców!");
			}
		}
	}
	
	public List<Driver> getAvailableDrivers(){
		List<Driver> availableDrivers = new ArrayList<Driver>();
		ParseQuery<Driver> query = ParseQuery.getQuery(Driver.class);
		query.whereEqualTo("status", Settings.FREE_CAR_STATUS);
		// TODO trzeba uwzglêdniæ pojemnoœæ danego auta!!!
		// query.whereGreaterThanOrEqualTo("carCapacity",
		// order.getPassengerCount());
		try {
			availableDrivers = query.find();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return availableDrivers;
	}

	public void sortListByDistanceInStraightLine(List<Driver> driverList){
		
		Collections.sort(driverList, new Comparator<Driver>() {
			@Override
			public int compare(Driver arg0, Driver arg1) {
				// nie jestem pewien czy to zadzia³a ale chyba tak
				Car car0 = arg0.getCar();
				Car car1 = arg1.getCar();
				if (car0 != null && car1 != null) {
					return Double.compare(
							countDistanceInStraightLine(car0),
							countDistanceInStraightLine(car1));
				}
				return 2; // TODO trzeba to jakoœ ³adniej obs³u¿yæ
			}

		});
	}
	
	public double countDistanceInStraightLine(Car car) {
		ParseGeoPoint driverPosition = car.getCurrentPosition();

		double distance = driverPosition.distanceInKilometersTo(customerPosition);
		return distance;
	}
	
	public ArrayList<Driver> restrictListToTheBestResults(List<Driver> drivers){
		ArrayList<Driver> sortedDrivers = new ArrayList<Driver>();

		int sortedDriversLength = NUMBER_CHOOSED_DRIVERS;
		if (drivers.size() < NUMBER_CHOOSED_DRIVERS) {
			sortedDriversLength = drivers.size();
		}
		for (int i = 0; i < sortedDriversLength; i++) {
			sortedDrivers.add(drivers.get(i));
		}
		return sortedDrivers;
	}

	public void notifyDriver(Driver driver) {
		ParsePush push = new ParsePush();
		// ArrayList<String> channels = new ArrayList<String>();
		// sprawdziæ jakie potrzebne s¹ do listy rzeczy czy: ["",id] czy [id]
		// czy inaczej
		ParseObject pointer = (ParseObject) driver.getParseObject("userId");
		String userId = (String) pointer.getObjectId();
		String channel = "user_" + userId;
		push.setOrderMessage(order.getObjectId());
		push.setChannel(channel);
		
		try {
			push.send();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void doingNotifyDriver() {
		System.out.println("NotifyDriver: ");
		isOrderAssigned = false;
		int i = 0;
		while (!isOrderAssigned && driversWithRouteData != null
				&& i < driversWithRouteData.size()) {
			/*
			 * Powiadamiamy kolejnego kierowca z listy
			 */
			assignedDriver = driversWithRouteData.get(i).getDriver();
			System.out.println("Powiadamiam drivera: " + assignedDriver.getName() + " ID:" + assignedDriver.getId());
			notifyDriver(assignedDriver);

			/*
			 * Czekamy okreœlon¹ liczbê czasu na jego odpowiedŸ
			 */
			waitingForDriverResponse(WAITING_TIME_RSP_DRIVER);
		
			System.out.println("Status ordera: " + order.getStatus());
			/*
			 * Pobieramy z bazy aktualne dane zamówienia
			 */
			order = updateOrderData();
			System.out.println("Po aktualizacji status ordera:" + order.getStatus());
			
			/*
			 * Sprawdzamy czy status zamówienia zosta³ zmieniony przez powiadomionego kierowce
			 */
			if (isDriverResponse()) {
				
				/* TODO wyœwietl komunikat dla dispatchera, ze zosta³
				* przydzielony do konkretnego kierowcy
				* i jego odleg³oœc i czas oczekiwania
				*/

			} else { //Powiadom kolejnego kierowce z listy
				i++;
			}
		}
		/*
		 * Jeœli ¿aden z zapytanych kierowców nie podejmie siê ordera to powiadom dyspozytora komunikatem
		 */
		checkOrderAssigned();
	}

	public void waitingForDriverResponse(long milliseconds){
		try {
			System.out.println("Oczekujê na decyzjê drivera...");
			Thread.sleep(milliseconds);
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isDriverResponse(){
		isOrderAssigned = false;
		if (order.getStatus() == Settings.ACCEPTED_ORDER_STATUS) {
			isOrderAssigned = true;
			// TODO kierowca musi sam wstawiac swoje ID do ordera i zmieniac status tego ordera na 4!
			stop = true;
			System.out.println("Przydzielony driver to: " + assignedDriver.getName() + "!!!");
		}
		return isOrderAssigned;
	}
	
	public void checkOrderAssigned(){
		if (!isOrderAssigned) {
			stop = true;
			// TODO co jeœli nikt nie odpowie - wyrzuæ komunikat
			System.out.println("¯aden z przydzielonych kierowców nie zaakceptowa³ ordera");	
		}
	}
	
	public GHResponse routeBetweenPoints(ParseGeoPoint driverParseGeoPoint,
			ParseGeoPoint customerGeoPoint) {
	
		GHRequest req = new GHRequest(driverParseGeoPoint.getLatitude(),
				driverParseGeoPoint.getLongitude(),
				customerGeoPoint.getLatitude(), customerGeoPoint.getLongitude());
		// req.setWeighting("fastest");

		GHResponse rsp = hopper.route(req);

		PointList pointList = rsp.getPoints();
		double distance = rsp.getDistance();
		long millis = rsp.getMillis();

		System.out.println("odleglosc: " + distance + ", sek: " + millis);
		return rsp;
	}

	public List<DriverResponse> routeDistance(List<Driver> chooseDrivers) {
		List<DriverResponse> driverRspList = new ArrayList<DriverResponse>();
		GHResponse rsp;
		DriverResponse driverRsp;

		for (Driver d : chooseDrivers) {
			rsp = routeBetweenPoints(d.getCar().getCurrentPosition(),
					customerPosition);
			driverRsp = new DriverResponse(d, rsp);

			driverRspList.add(driverRsp);
		}
		sortByDistance(driverRspList);

		return driverRspList;
	}
	
	public void sortByDistance(List<DriverResponse> driverRspList){
		Collections.sort(driverRspList, driverRspList.get(0)
				.getDriverResponseComparator());
	}
	
	public Order updateOrderData(){
		Order o = null;
		String id = order.getObjectId();

		ParseQuery<Order> queryOrder = ParseQuery.getQuery("Order");
		try {
			o = queryOrder.get(id);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return o;
	}

	private class DriverResponse implements Comparable<DriverResponse> {
		private Driver driver;
		private GHResponse rsp;

		public DriverResponse(Driver driver, GHResponse rsp) {
			this.setDriver(driver);
			this.setRsp(rsp);
		}

		public Driver getDriver() {
			return driver;
		}

		public void setDriver(Driver driver) {
			this.driver = driver;
		}

		public GHResponse getRsp() {
			return rsp;
		}

		public void setRsp(GHResponse rsp) {
			this.rsp = rsp;
		}

		public double getDistanceInMeters() {
			return rsp.getDistance();
		}

		public double getTimeInMillis() {
			return rsp.getMillis();
		}

		@Override
		public int compareTo(DriverResponse driverResponse) {
			double compareDistance = ((DriverResponse) driverResponse)
					.getDistanceInMeters();
			double compareTime = driverResponse.getTimeInMillis();

			// TODO poprawiæ porównywanie, dodaæ porównywanie po czasie
			return (int) (this.getDistanceInMeters() - compareDistance);
		}

		private Comparator<DriverResponse> driverResponseComparator = new Comparator<DriverResponse>() {

			public int compare(DriverResponse driverResponse1,
					DriverResponse driverResponse2) {
				return driverResponse1.compareTo(driverResponse2);
			}
		};

		public Comparator<DriverResponse> getDriverResponseComparator() {
			return driverResponseComparator;
		}
	}
}
