package admin.driver.edit.model;

import java.util.List;

import model.Driver;

public class EditDriverChanges {
	int flag;
	List<Driver> drivers;

	public EditDriverChanges(List<Driver> drivers, int flag){
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
