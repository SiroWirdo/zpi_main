package geocoding;

import org.jdesktop.swingx.mapviewer.Waypoint;
import org.parse4j.ParseGeoPoint;
import java.util.ArrayList;
import java.util.List;

import settings.Settings;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.PendingResult;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

public class ConverterGeoPosition {
	
	public static Waypoint geoPointToWaypoint(ParseGeoPoint geoPoint){
		return new Waypoint(geoPoint.getLatitude(), geoPoint.getLongitude());
	}

	public static AddressInfo addressToAdressInfo(String address){
		
		// Replace the API key below with a valid API key.
		GeoApiContext context = new GeoApiContext().setApiKey(Settings.GOOGLE_API_KEY);
		AddressInfo addressInfo = new AddressInfo();
		
		GeocodingResult[] results;
		try {
			results = GeocodingApi.geocode(context,
			    address).await();
			
			addressInfo.setFullAddress(results[0].formattedAddress);
			addressInfo.setLatLng(new LatLng(results[0].geometry.location.lat,
					results[0].geometry.location.lng));

//			System.out.println(results[0].formattedAddress);
//			System.out.println(results[0].geometry.location.lat);
//			System.out.println(results[0].geometry.location.lng);
			
//			geoPoint = new ParseGeoPoint(results[0].geometry.location.lat,
//					results[0].geometry.location.lng);
			
			System.out.println(addressInfo);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return addressInfo;
//		
//		GeocodingApiRequest req = GeocodingApi.newRequest(context).address("Wrocław");
//		req.awaitIgnoreError(); // No checked exception.
//
//		// Async
//		req.setCallback(new PendingResult.Callback<GeocodingResult[]>() {
//		  @Override
//		  public void onResult(GeocodingResult[] result) {
//		    
//		  }
//
//		  @Override
//		  public void onFailure(Throwable e) {
//		    // Handle error.
//		  }
//		});
	}
	
	public static ParseGeoPoint addressInfoToParseGeoPoint(AddressInfo addressInfo){ 
		return new ParseGeoPoint(addressInfo.getLatitude(), addressInfo.getLongtitude());
	}

//	public static GeocodingResult[] getPropositionForRsp(String address) {
//		GeocodingResult[] results = null;
//		try {
//			results = GeocodingApi.geocode(context, address).await();
//
//			System.out.println("Podpowiadane adresy to:");
//			for (int i = 0; i < results.length; i++) {
//				System.out.println("     " + i + "."
//						+ results[i].formattedAddress);
//			}
//
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		return results;
//	}
}
