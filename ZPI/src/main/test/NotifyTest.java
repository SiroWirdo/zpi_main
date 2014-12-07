import model.Driver;
import model.Order;

import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParsePush;
import org.parse4j.ParseQuery;

import other.DataBaseConnection;


public class NotifyTest {
	public static void main(String[] args){
		DataBaseConnection.initialize();
		ParseQuery<Driver> query = new ParseQuery<Driver>(Driver.class);
		query.whereEqualTo("objectId", "gYvqcZalEh");
		Driver driver = null;
		try {
			driver = query.find().get(0);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		ParsePush push = new ParsePush();
		//ArrayList<String> channels = new ArrayList<String>();
		//sprawdzić jakie potrzebne są do listy rzeczy czy: ["",id] czy [id] czy inaczej
		ParseQuery<Order> query_two = new ParseQuery<Order>(Order.class);
		query_two.whereEqualTo("objectId", "so1ioAD8Za");
		Order order = null;
		try {
			order = query_two.find().get(0);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println(driver.getId() + " " + order.getId());
		ParseObject pointer = (ParseObject) driver.getParseObject("userId");
		String userId = (String)pointer.getObjectId();
		String channel = "user_" + userId;
		push.setOrderMessage(order.getObjectId());
		push.setChannel(channel);
		try {
			push.send();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
