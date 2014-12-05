package order.model;

import geocoding.AddressInfo;
import geocoding.ConverterGeoPosition;
import model.Customer;
import model.Order;

import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParsePointer;
import org.parse4j.ParseRelation;
import org.parse4j.ParseUser;

import algorithm.Algorithm;
import other.DataBaseConnection;
import settings.Settings;

public class OrderModel {

	private Algorithm a;
	
	public OrderModel(){
	}

	public void initialize(){
		DataBaseConnection.initialize();
	}
	
	/*
	 * Utworzenie nowego zamówienia
	 */
	public void addOrder(String surname, Number phoneNumber , String pickUpAddress,
			String customerRemarks, Number passangerCount){
		
		Order order = new Order();
		
		Customer customer = addCustomer(surname, phoneNumber);
		order.setCustomer(customer);
		order.setCustomerRemarks(customerRemarks);
		order.setPassengerCount(passangerCount);
		order.setStatus(Settings.WAITING_ORDER_STATUS);
		
		AddressInfo addressInfo = ConverterGeoPosition.addressToAdressInfo(pickUpAddress);
		order.setPickupAddress(addressInfo.getFullAddress());
		order.setPickupAddressGeo(ConverterGeoPosition.addressInfoToParseGeoPoint(addressInfo));
		
//		TODO naprawiæ dodawanie dispatchera
//		ParseUser dispatherObj = Settings.USER_OBJECT;
//		order.put("dispatcher", new ParsePointer("Dispatcher", Settings.USER_OBJECT.getObjectId()));
		order.saveInBackground();		
		
		/*
		 * Uruchomienie algorytmu przydzia³u
		 */
		assignDriver(order);

	}
	
	/*
	 * Utworzenie nowego klienta
	 */
	public Customer addCustomer(String surname, Number phoneNumber){
		Customer customer = new Customer();
		customer.setSurname(surname);
		customer.setPhoneNumber(phoneNumber);
		try {
			customer.save();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		System.out.println(customer.getId());
		return customer;
	}
	
	/*
	 * Podpiêcie algorytmu przydzielania kierowcy do zlecenia!
	 */
	public void assignDriver(Order order){
		Algorithm.initializeGraphHopper();
		Algorithm a = new Algorithm(order);
		a.run();
	}
	
}
