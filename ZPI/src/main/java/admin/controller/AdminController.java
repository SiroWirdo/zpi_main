package admin.controller;

import admin.model.AdminModel;
import admin.view.AdminView;

public class AdminController {
	AdminModel adminModel;
	AdminView adminView;

	public AdminController(AdminModel adminModel){
		this.adminModel = adminModel;
		adminView = new AdminView(this, adminModel);
		
		adminModel.initialize();
		adminView.initialize();
	}
}
