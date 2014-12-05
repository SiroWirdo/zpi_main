package geocoding;

import com.google.maps.model.LatLng;

public class AddressInfo {
	private String fullAddress;
	private LatLng latLng;
	
	public AddressInfo(){
		
	}
	
	public AddressInfo(String fullAddress, LatLng latLng) {
		this.fullAddress = fullAddress;
		this.latLng = latLng;
	}
	
	public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	public LatLng getLatLng() {
		return latLng;
	}
	public void setLatLng(LatLng latLng) {
		this.latLng = latLng;
	}
	
	public double getLatitude() {
		return latLng.lat;
	}
	
	public double getLongtitude() {
		return latLng.lng;
	}
	
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append("Full address: ");
		result.append(getFullAddress());
		result.append("\nLatitude: ");
		result.append(getLatitude());
		result.append("\nLongtitude: ");
		result.append(getLongtitude());
		
		return result.toString();
	}
	
}
