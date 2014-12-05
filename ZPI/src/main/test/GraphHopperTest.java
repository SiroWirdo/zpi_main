import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.util.CmdArgs;
import com.graphhopper.util.PointList;


public class GraphHopperTest {
	public static void main( String[] args ) {
		Logger orgHibernateLogger = (Logger) LoggerFactory.getLogger("ROOT");
		ch.qos.logback.classic.Level oldLogLevel = orgHibernateLogger.getLevel();
		orgHibernateLogger.setLevel(Level.OFF);
		
		 GraphHopper hopper = new GraphHopper().forDesktop();
		 hopper.setOSMFile("poland-latest.osm.pbf"); 
		 hopper.setGraphHopperLocation("src/main/resources/"); 
		 
		 hopper.setEncodingManager(new EncodingManager(EncodingManager.CAR));
		 hopper.importOrLoad(); 
//		 hopper.route(new GHRequest(
//		     52.524786, 13.401604, 
//		     52.503266, 13.358688)); 
//		 hopper.close(); 
		 
		 
//		 
//		 GraphHopper hopper = new GraphHopper().forDesktop();
//	    	hopper.setInMemory(true);
//	    	String osmFile ="poland-latest.osm.pbf";
//			hopper.setOSMFile(osmFile);
//	    	String graphFolder = "src/main/resources/";
//			// where to store graphhopper files?
//	    	hopper.setGraphHopperLocation(graphFolder);
//	    	hopper.setEncodingManager(new EncodingManager(EncodingManager.CAR));
//
//	    	// now this can take minutes if it imports or a few seconds for loading
//	    	// of course this is dependent on the area you import
//	    	hopper.importOrLoad();
//
//	    	// simple configuration of the request object, see the GraphHopperServlet class for more possibilities.
	    	GHRequest req = new GHRequest(51.1097487, 17.1170762, 51.097885, 17.028538);
//	    	    //setWeighting("fastest").
//	    	    req.setVehicle("car");
	    	GHResponse rsp = hopper.route(req);
//	    
//	    	// first check for errors
//	    	if(rsp.hasErrors()) {
//	    	   // handle them!
//	    	   // rsp.getErrors()
//	    	   return;
//	    	}
//
//	    	// route was found? e.g. if disconnected areas (like island) 
//	    	// no route can ever be found
//	    	if(!rsp.isFound()) {
//	    	   // handle properly
//	    	   return;
//	    	}
//
//	    	// points, distance in meters and time in millis of the full path
	    	PointList pointList = rsp.getPoints();
	    	double distance = rsp.getDistance();
	    	long millis = rsp.getMillis();
////	    	// get the turn instructions for the path
////	    	InstructionList il = rsp.getInstructions();
////	    	Translation tr = trMap.getWithFallBack(Locale.US);
////	    	List<String> iList = il.createDescription(tr);
//	//
////	    	// or get the result as gpx entries:
////	    	List<GPXEntry> list = il.createGPXList();
	    	System.out.println("odleglosc: " + distance + ", sek: " + millis);
		}
}
