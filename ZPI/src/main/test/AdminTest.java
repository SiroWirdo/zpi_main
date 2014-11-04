import admin.controller.AdminController;
import admin.model.AdminModel;


public class AdminTest {
	
	public static void main(String[] args){
		AdminModel adminModel = new AdminModel();
		AdminController adminController = new AdminController(adminModel);
	}
}
