package ordersdisplay.view;

import ordersdisplay.controller.EditOrderController;
import ordersdisplay.model.EditOrderModel;

public class EditOrderView {
	EditOrderController editOrderController;
	EditOrderModel editOrderModel;
	
	public EditOrderView(EditOrderController editOrderController, EditOrderModel editOrderModel){
		this.editOrderController = editOrderController;
		this.editOrderModel = editOrderModel;
		
	}
	
	public void initialize(){
		
	}
	
}
