package admin.driver.edit.view;

import javax.swing.JFrame;

import admin.driver.edit.controller.ModifyDriverController;
import admin.driver.edit.model.ModifyDriverModel;


public class ModifyDriverView extends JFrame{
	ModifyDriverController modifyDriverController;
	ModifyDriverModel modifyDriverModel;
	
	
	public ModifyDriverView(ModifyDriverController modifyDriverController, ModifyDriverModel modifyDriverModel){
		this.modifyDriverController = modifyDriverController;
		this.modifyDriverModel = modifyDriverModel;

	}
	
	public void initialize(){
		
	}
}
