package order.controller;

import geocoding.ConverterGeoPosition;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.parse4j.ParseGeoPoint;

import com.google.maps.model.GeocodingResult;

import algorithm.Algorithm;
import order.model.OrderModel;
import order.view.AddOrderJPanel;
import order.view.Java2sAutoComboBox;
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
			String pickUpAddress, String customerRemarks, Number passangerCount) {
		// String customerId = orderModel.addCustomer(surname, phoneNumber);
		return orderModel.addOrder(surname, phoneNumber, pickUpAddress,
				customerRemarks, passangerCount);
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
			else if(e.getActionCommand().equals("Wyczyść")){
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
					new Integer(addOrderView
							.getPassangerCountTextField().getText().trim()));
			  SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
			        	mapController.refreshMap();
			        	setPositionOnNewOrder(o);   
		            }
		        });
     
			assignDriver(o);
			if(addOrderView.isCleanAfterAddChecked()){
				addOrderView.cleanAll();	
			}
		} else {
			System.out.println("Formularz nie przeszedł walidacji");
		}
	};
	
	/*
	 * Podpięcie algorytmu przydzielania kierowcy do zlecenia!
	 */
	public void assignDriver(final Order order){
		
	    Thread queryThread = new Thread() {
	        public void run() {
	    		Algorithm.initializeGraphHopper();
	    		Algorithm a = new Algorithm(order, 0);
	    		a.run();
	        }
	      };
	      queryThread.start();

	}
	
	private void setPositionOnNewOrder(Order newOrder){
		ParseGeoPoint geoPoint = newOrder.getPickupAddressGeo();
		mapController.setPosition(geoPoint.getLatitude(), geoPoint.getLongitude());
	}
	
//	private void setPropListInCombobox(GeocodingResult[] results){
//		addOrderView.setPickUpAddressTextFieldList(results);
//	}
	
//	public ComboBoxChangeValueListener getComboBoxChangeValueListener(){
//		return new ComboBoxChangeValueListener();
//	}
	
//	public class ComboBoxChangeValueListener implements ActionListener{
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			String text = addOrderView.getComboValue();
//			GeocodingResult[] results = ConverterGeoPosition.getPropositionForRsp(text);
//			addOrderView.setPickUpAddressTextFieldList(results);		
//		}
//	
//	}
	
	/*private JComboBox getJComboBox(){
		return addOrderView.getComboBox();
	}
	
	public TextFieldKeyListener getTextFieldKeyListener(){
		return new TextFieldKeyListener();
	}
	
	public class TextFieldKeyListener implements KeyListener{
	      public void keyReleased(KeyEvent e) {
	    	  int keyCode = e.getKeyChar();
	    	  System.out.println("KEY CODE:" + keyCode);
	    	  JComboBox combo = addOrderView.getComboBox();
	    	  JTextField textField = (JTextField)e.getSource();
//	    	  if( (keyCode >= 65 && keyCode <=95) || Settings.POLISH_KEY_CODE.contains(keyCode)){
	    	  if( (keyCode >= 65 && keyCode <=122) || Settings.POLISH_KEY_CODE.contains(keyCode)){
	    		
				System.out.println("Szukam dla tekstu:" + textField.getText());
				GeocodingResult[] result = ConverterGeoPosition.getPropositionForRsp(textField.getText());
				combo.removeAllItems();
				combo.hidePopup();
				final JComboBox newCombo = combo;
				for(int i = 0; i < result.length; i++){
					combo.addItem(result[i].formattedAddress);
				}
				  SwingUtilities.invokeLater(new Runnable() {
			            @Override
			            public void run() {
			    			newCombo.showPopup();       
			            }
			        });
	    	  }
	    	  //
	    	  else if(e.getKeyCode() == KeyEvent.VK_UP){
	    		  System.out.println("Up");
	    	  }
	    	  else if(e.getKeyCode() == KeyEvent.VK_DOWN){
	    		 int index =  combo.getSelectedIndex();
	    		 if(index < combo.getComponentCount()){
	    		  combo.setSelectedIndex(index++);
	    		 // combo.setF
	    		 }
	    		  
	    	  }
	    	  else if(e.getKeyCode() == KeyEvent.VK_ENTER){
	    		  System.out.println("Enter");
	    	  }

	      }

	      public void keyTyped(KeyEvent e) {
	      }

	      public void keyPressed(KeyEvent e) {
	      }
	    }
	
	
	public TextFieldDocListener getTextFieldDocListener(){
		return new TextFieldDocListener();
	}
	public class TextFieldDocListener implements DocumentListener{

		@Override
		public void insertUpdate(DocumentEvent e) {
			Thread filerThread = new Thread() {
		        public void run() {
		        	 JTextField textField = addOrderView.getEditedField();
					  System.out.println("Szukam dla tekstu:" + textField.getText());
					  final GeocodingResult[] result = ConverterGeoPosition.getPropositionForRsp(textField.getText());
					  
					  Java2sAutoComboBox combo = addOrderView.getComboBox();
					  combo.removeAllItems();
					  for(int i = 0; i < result.length; i++){
							combo.addItem(result[i].formattedAddress);
						}
//					  combo.setC
					  combo.showPopup();
//					  final JComboBox newCombo = combo;
//					  SwingUtilities.invokeLater(new Runnable() {
//				            @Override
//				            public void run() {
//				            	combo.removeAll();	
//				            	
//				            }
//					  }
//					  );
		        }
		      };
		      filerThread.start();
			  
//		
			
//			 Runnable doHighlight = new Runnable() {
//			        @Override
//			        public void run() {
//			            final JComboBox combo = addOrderView.getComboBox();
//				    	  JTextField textField = (JTextField) combo.getEditor().getEditorComponent();
//					    	//  textField.setCursor(cursor);
//							System.out.println("Szukam dla tekstu:" + textField.getText());
//							GeocodingResult[] result = ConverterGeoPosition.getPropositionForRsp(textField.getText());
//							combo.removeAllItems();
//							  SwingUtilities.invokeLater(new Runnable() {
//						            @Override
//						            public void run() {
//						            	combo.hidePopup();      
//						            }
//							  }
//							  );
//								
//							for(int i = 0; i < result.length; i++){
//								combo.addItem(result[i].formattedAddress);
//							}
//							
//							final JComboBox newCombo = combo;
//
//							  SwingUtilities.invokeLater(new Runnable() {
//						            @Override
//						            public void run() {
//						    			newCombo.showPopup();       
//						            }
//							  }
//							  );
//			        }
//			    };       
//			    SwingUtilities.invokeLater(doHighlight);
//			
    	  }
		
		@Override
		public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			
		}			 
	    }
	 */
	
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
