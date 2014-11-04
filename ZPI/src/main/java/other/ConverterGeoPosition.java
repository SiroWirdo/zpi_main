package other;

import org.jdesktop.swingx.mapviewer.Waypoint;
import org.parse4j.ParseGeoPoint;

public class ConverterGeoPosition {
	
	public static Waypoint geoPointToWaypoint(ParseGeoPoint geoPoint){
		return new Waypoint(geoPoint.getLatitude(), geoPoint.getLongitude());
	}

	public static void addressToGeoPoint(String address){
		//TODO
	}
}
