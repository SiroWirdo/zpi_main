package drivers.model;

import java.util.List;

import model.Driver;

import org.parse4j.ParseObject;

public class DriverChanges {
	int flag;
	List<Driver> drivers;
	
	public DriverChanges(List<Driver> drivers, int flag){
		this.drivers = drivers;
		this.flag = flag;
	}
	
	public List<Driver> getDrivers(){
		return drivers;		
	}
	
	public void setDrivers(List<Driver> drivers){
		this.drivers = drivers;
	}
	
	public int getFlag(){
		return flag;
	}
	
	public void setFlag(int flag){
		this.flag = flag;
	}
}
