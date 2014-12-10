package ordersdisplay.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;

import model.Order;
import ordersdisplay.model.OrdersModel;
import ordersdisplay.view.OrdersView;
import settings.Settings;

public class OrdersController implements Observer  {
	
	private OrdersView ordersView;
	private OrdersModel ordersModel;

	public OrdersController(OrdersModel ordersModel){
		this.ordersModel = ordersModel;
		this.ordersView = new OrdersView(this, ordersModel);
		ordersModel.addObserver(this);

		this.ordersModel.initialize();
		this.ordersView.initialize();

	}

	public OrdersView getOrdersView(){
		return ordersView;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 == ordersModel){
			if(ordersModel.getChanges().getFlag() == 0){
				List<Order> orders = ordersModel.getChanges().getOrders();
				for(Order order : orders){

					addRow(order);
				}
			}else{
				List<Order> orders = ordersModel.getChanges().getOrders();
				for(Order order : orders){

					updateRow(order);
				}
			}
		}
	}

	public void addRow(Order order){
		String status = Settings.orderStatus[order.getStatus()];
		String phone = "Brak kierowcy";
		if(order.getDriver() != null){
			phone = new Long(order.getDriver().getPhoneNumber()).toString();
		}

		ordersView.addRow(new Object[]{order.getId(), order.getCustomer().getSurname(), status, Settings.changeEncoding(order.getPickupAddress()), Settings.changeEncoding(order.getDestinationAddress()), order.getCost(), Settings.changeEncoding(order.getCustomerRemarks()), order.getPassengerCount(), phone});
		ordersView.repaint();
	}

	public void updateRow(Order order){
		String status = Settings.orderStatus[order.getStatus()];
		String phone = "Brak kierowcy";
		if(order.getDriver() != null){
			phone = new Long(order.getDriver().getPhoneNumber()).toString();
		}
		Object[] values = new Object[]{order.getId(), order.getCustomer().getSurname(), status, Settings.changeEncoding(order.getPickupAddress()), Settings.changeEncoding(order.getDestinationAddress()), order.getCost(), Settings.changeEncoding(order.getCustomerRemarks()), order.getPassengerCount(), phone};
		try{
			int row = ordersView.getRowById(order.getId());
			ordersView.updateRow(row, values);
			ordersView.repaint();
		}catch(OrderNotFoundException e){
			System.out.println(e.getMessage());
			addRow(order);
		}

	}

	public FiltrListener getFiltrListener(){
		return new FiltrListener();
	}

	private class FiltrListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

			ArrayList<RowFilter<DefaultTableModel, Object>> statusFilters = new ArrayList<RowFilter<DefaultTableModel, Object>>();
			ArrayList<RowFilter<DefaultTableModel, Object>> nameFilters = new ArrayList<RowFilter<DefaultTableModel, Object>>();

			RowFilter<DefaultTableModel, Object> statusFilter;
			RowFilter<DefaultTableModel, Object> nameFilter;
			RowFilter<DefaultTableModel, Object> mainFilter;

			for(String status : Settings.orderStatus){
				if(ordersView.isChecked(status)){
					RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter(status);
					statusFilters.add(filter);
				}
			}

			statusFilter = RowFilter.orFilter(statusFilters);

			if(!ordersView.getSurname().equals("")){
				RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter(ordersView.getSurname());
				nameFilters.add(filter);
			}

			if(nameFilters.size() > 0){
				nameFilter = RowFilter.andFilter(nameFilters);
				ArrayList<RowFilter<DefaultTableModel, Object>> combineFilters = new ArrayList<RowFilter<DefaultTableModel, Object>>();
				combineFilters.add(statusFilter);
				combineFilters.add(nameFilter);
				mainFilter = RowFilter.andFilter(combineFilters);
			}else{
				mainFilter = statusFilter;
			}

			ordersView.setFilters(mainFilter);
			ordersView.repaint();

		}
		

	}

}
