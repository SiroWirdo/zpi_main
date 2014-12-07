import model.Customer;
import model.Order;

import org.parse4j.ParseException;
import org.parse4j.ParseQuery;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import other.DataBaseConnection;
import algorithm.Algorithm;

public class RoutingTest {

	public static void main(String[] args) {
		DataBaseConnection.initialize();
		
		Logger orgHibernateLogger = (Logger) LoggerFactory.getLogger("ROOT");
		ch.qos.logback.classic.Level oldLogLevel = orgHibernateLogger.getLevel();
		orgHibernateLogger.setLevel(Level.OFF);
		
		Algorithm.initializeGraphHopper();
		Order o = null;

		String id = "C7YlvUUcff"; //ID istniejącego ordera
		ParseQuery<Order> queryOrder = ParseQuery.getQuery("Order");
		try {
			o = queryOrder.get(id);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(o != null){
			Algorithm a = new Algorithm(o, 0);
			a.run();
		}
		else{
			System.out.println("Order jest nullem");
		}
	}

}
