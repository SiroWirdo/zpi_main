package order_info.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.SwingUtilities;

import model.Customer;
import model.Driver;
import model.Order;
import order_info.model.OrderInfoModel;
import order_info.view.InformationPanel;
import order_info.view.SingleInfoPanel;
import settings.Settings;

public class OrderInfoController {
	private OrderInfoModel infoModel;
	private InformationPanel infoView;
	
	public OrderInfoController(OrderInfoModel infoModel) {
		this.infoModel = infoModel;
		infoView = new InformationPanel(this, infoModel);
//		infoModel.initialize();
//		infoView.initialize();
		refreshListInfo(15000); //TODO wrzuciæ do settingsów
	}
	
	public InformationPanel getInfoView(){
		return infoView;
	}
	
	public void addOrderToList(Order o){
		infoModel.addOrder(o);
	}
	
	public void refreshListInfo(final long millis) {
		Thread refreshThread = new Thread() {
			public void run() {
				while (true) {
					System.out.println("OrderInfoController: Uaktualniam info!");
					updateListInfo();
					try {
						Thread.sleep(millis);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		refreshThread.start();
	}
	
	private void updateListInfo(){
		List<Order> refreshedLastOrders = infoModel.refreshLastOrders();
		List<SingleInfoPanel> singleInfoPanels = createSingleInfoPanels(refreshedLastOrders);
		setInfoInView(singleInfoPanels);
		drawInfoView();
	}
	
	private List<SingleInfoPanel> createSingleInfoPanels(List<Order> orders){
		List<SingleInfoPanel> sips = new ArrayList<SingleInfoPanel>();
		for(Order o: orders){
			sips.add(createSingleInfoPanel(o));
		}
		return sips;
	}
	
	private SingleInfoPanel createSingleInfoPanel(final Order o){
		final SingleInfoPanel sip = new SingleInfoPanel(this, infoModel);
		final Customer c = o.getCustomer();
		Driver d = o.getDriver();
		
		String driverName =  "Nie przydzielono";
		
		if(d != null){
			StringBuilder sb = new StringBuilder();
			sb.append(d.getSurname());
			sb.append(" ");
			sb.append(d.getName());
			
			driverName = sb.toString();
		}
		
		final String driverFullName = driverName;
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				sip.setTextLabels(c, o.getStatus(), driverFullName);
				sip.setVisible(true);
			}
		});
		
		return sip;
	}
	
	private void setInfoInView(List<SingleInfoPanel> sips){
		Collections.reverse(sips);
		infoView.setListInfoPanels(sips);
		infoView.setVisible(true);
	}
	
	private void drawInfoView(){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				infoView.paintAllSingleInfo();
			}
		});
	}
	
}
