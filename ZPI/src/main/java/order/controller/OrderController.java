package order.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import org.parse4j.ParseGeoPoint;

import algorithm.Algorithm;
import order.model.OrderModel;
import order.view.AddOrderJPanel;
import order.view.OrderDetailsView;
import settings.Settings;
import main.controller.MapController;
import model.Order;

public class OrderController {
	
	private OrderModel orderModel;
	private AddOrderJPanel addOrderView;
	
	private MapController mapController;
	
	private ButtonListener buttonListener;
	private ValidateTextFieldListener validateTextFieldListener;
	private TrimTextFieldListener trimTextFieldListener;
	Thread customerThread;
	Thread orderThread;

	private Order orderDetailsModel;
	private OrderDetailsView orderView;

	public OrderController(OrderModel orderModel, MapController mapController) {
		this.orderModel = orderModel;
		this.mapController = mapController;
		buttonListener = new ButtonListener();
		validateTextFieldListener = new ValidateTextFieldListener();
		trimTextFieldListener = new TrimTextFieldListener();
		orderModel.initialize();
		addOrderView = new AddOrderJPanel(this, orderModel);
		addOrderView.initialize();
	}

	public OrderController(Order orderDetailsModel) {
		this.orderDetailsModel = orderDetailsModel;
		orderView = new OrderDetailsView(this, orderDetailsModel);
	}

	public AddOrderJPanel getAddOrderView() {
		return addOrderView;
	}

	public AddOrderJPanel getAddOrderJPanel(){
		return addOrderView;
	}
	
	public Order addOrder(String surname, Number phoneNumber,
			String pickUpAddress, String customerRemarks, Number passengerCount) {
		// String customerId = orderModel.addCustomer(surname, phoneNumber);
		return orderModel.addOrder(surname, phoneNumber, pickUpAddress,
				customerRemarks, passengerCount);
	}

	public void setOrderDetails() {
		orderView.setAllDetailsInLabel();
	}

	public ButtonListener getButtonListener() {
		return buttonListener;
	}

	public ValidateTextFieldListener getValidateTextFieldListener() {
		return validateTextFieldListener;
	}
	
	public TrimTextFieldListener getTrimTextFieldListener() {
		return trimTextFieldListener;
	}

	public class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Dodaj")) {
				
			    Thread queryThread = new Thread() {
			        public void run() {
			          checkViewAndAddOrder();
			        }
			      };
			      queryThread.start();
			}
			else if(e.getActionCommand().equals("Wyczyœæ")){
				 addOrderView.cleanFields();
				 addOrderView.cleanAllErrors();
			}
		}
	}
	
	public void checkViewAndAddOrder(){
		addOrderView.cleanAllErrors();
		addOrderView.validateFields();
		if (addOrderView.isFormValidate()) {
			String defaultCity = "";
			if(addOrderView.isDefaultCityChecked()){
				defaultCity = Settings.DEFAULT_CITY;
			}
			final Order o = addOrder(
					addOrderView.getSurnameTextField().getText().trim(),
					new Long(addOrderView.getPhoneNumberTextField()
							.getText().trim()),
					addOrderView.getPickUpAddressTextField().getText() + defaultCity,
					addOrderView.getCustomerRemarksTextArea().getText(),
					(int)addOrderView
							.getPassengerCountComboBox().getSelectedItem());
		    Thread changePositionThread = new Thread() {
		        public void run() {
		        	setPositionOnNewOrder(o);
		        }
		      };
		      changePositionThread.start();
		      
			assignDriver(o);
			if(addOrderView.isCleanAfterAddChecked()){
				addOrderView.cleanAll();	
			}
		} else {
			System.out.println("Formularz nie przeszed³ walidacji");
		}
	};
	
	/*
	 * Podpiêcie algorytmu przydzielania kierowcy do zlecenia!
	 */
	public void assignDriver(Order order){
		Algorithm.initializeGraphHopper();
		Algorithm a = new Algorithm(order);
		a.run();
	}
	
	private void setPositionOnNewOrder(Order newOrder){
		ParseGeoPoint geoPoint = newOrder.getPickupAddressGeo();
		mapController.setPosition(geoPoint.getLatitude(), geoPoint.getLongitude());
	}
	
	public class ValidateTextFieldListener implements FocusListener{

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void focusLost(FocusEvent e) {
			JTextField focusField = (JTextField)e.getSource();
			String text = focusField.getText();
			if(!text.isEmpty()){
				if(text.contains("-")){
					String[] splitedText = text.split("-");
					String first = splitedText[0];
					String second = splitedText[1];
					
					String firstLetterFromFirst = first.substring(0, 1);
					String lastLettersFromFirst = first.substring(1, first.length());
					String newFirst = firstLetterFromFirst.toUpperCase() + lastLettersFromFirst.toLowerCase();
					
					String firstLetterFromSecond = second.substring(0, 1);
					String lastLettersFromSecond = second.substring(1, second.length());
					String newSecond = firstLetterFromSecond.toUpperCase() + lastLettersFromSecond.toLowerCase();
					
					String formatText = newFirst + "-" + newSecond;
					focusField.setText(formatText);
				}
				else{
					String firstLetter = text.substring(0, 1);
					String lastLetters = text.substring(1, text.length());
					String formatText = firstLetter.toUpperCase() + lastLetters.toLowerCase();
					focusField.setText(formatText);
				}
			}
		}
		
	}
	
	
	public class TrimTextFieldListener implements FocusListener{

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void focusLost(FocusEvent e) {
			JTextField focusField = (JTextField)e.getSource();
			focusField.setText(focusField.getText().trim());
		}
	}
}
