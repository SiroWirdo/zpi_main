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

import other.DataBaseConnection;
import settings.Settings;

public class OrderModel {
	private ParseObject order;
	private ParseObject customer;
	
	public OrderModel(){
	}
	
	public ParseObject getCustomer() {
		return customer;
	}
	
	public void initialize(){
		DataBaseConnection.initialize();
	}
	
	/*
	 * Create the order
	 */
	public void addOrder(String surname, Number phoneNumber , String pickUpAddress,
			String customerRemarks, Number passangerCount){
		Order order = new Order();
		
		Customer customer = new Customer();
		customer.put("surname", surname);
		customer.put("phoneNumber", phoneNumber);
		try {
			customer.save();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		order.put("customerId", customer);
//		TODO naprawiæ dodawanie dispatchera
//		ParseUser dispatherObj = Settings.USER_OBJECT;
//		order.put("dispatcher", new ParsePointer("Dispatcher", Settings.USER_OBJECT.getObjectId()));
		order.put("customerRemarks", customerRemarks);
		order.put("passengerCount", passangerCount);
		order.put("status", 0); //"oczekuj¹ce"
		AddressInfo addressInfo = ConverterGeoPosition.addressToAdressInfo(pickUpAddress);
		order.put("pickupAddress", addressInfo.getFullAddress());
		order.put("pickupAddressGeo", ConverterGeoPosition.addressInfoToParseGeoPoint(addressInfo));
		order.saveInBackground();		
	
//		try {
//			customer.save();
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		order.saveInBackground();
//		setDriverId(); <--- TODO algorytm
		//create the customer
	}
	
	public String addCustomer(String surname, Number phoneNumber){
		Customer customer = new Customer();
		customer.put("surname", surname);
		customer.put("phoneNumber", phoneNumber);
		try {
			customer.save();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(customer.getId());
		return customer.getId();
	}
	
	public String getCustomerId(){
		return customer.getString("objectId");
	}
	
	public void setDriverID(){
		/*
		 * Algorytm do przydzielania kierowcy do zleceniA!
		 */
	}
	
}
