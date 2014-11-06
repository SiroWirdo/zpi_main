package order.view;

import javax.swing.JDialog;

import model.Customer;
import model.Order;
import order.controller.OrderController;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextArea;

import settings.Settings;

public class OrderDetailsView extends JDialog {

	private Order orderModel;
	private OrderController orderDetailsController;
	
	
	private JLabel lastNameLabel;
	private JLabel phoneNrLabel;
	private JLabel addressLabel;
	private JLabel statusLabel;
	private JLabel passengerNrLabel;
	private JLabel createDateLabel;
	private JTextArea customerRemarksTextArea;
	
	public OrderDetailsView(OrderController orderDetailsController, Order orderModel) {	
		this.orderDetailsController = orderDetailsController;
		this.orderModel = orderModel;
		getContentPane().setLayout(null);
		
		lastNameLabel = new JLabel();
		lastNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lastNameLabel.setBounds(34, 26, 208, 37);
		getContentPane().add(lastNameLabel);
		
		JLabel lblNrTelefonu = new JLabel("nr telefonu:");
		lblNrTelefonu.setBounds(34, 74, 85, 14);
		getContentPane().add(lblNrTelefonu);
		
		JLabel lblAdres = new JLabel("adres:");
		lblAdres.setBounds(34, 99, 46, 14);
		getContentPane().add(lblAdres);
		
		JLabel lblStatus = new JLabel("status:");
		lblStatus.setBounds(34, 124, 46, 14);
		getContentPane().add(lblStatus);
		
		JLabel lblUwagiKlienta = new JLabel("uwagi klienta:");
		lblUwagiKlienta.setBounds(34, 149, 85, 14);
		getContentPane().add(lblUwagiKlienta);
		
		JLabel lblLiczbaPasaerw = new JLabel("liczba pasa\u017Cer\u00F3w:");
		lblLiczbaPasaerw.setBounds(34, 194, 85, 14);
		getContentPane().add(lblLiczbaPasaerw);
		
		JLabel lblUtworzono = new JLabel("utworzono:");
		lblUtworzono.setBounds(34, 219, 85, 14);
		getContentPane().add(lblUtworzono);
		
		phoneNrLabel = new JLabel();
		phoneNrLabel.setBounds(129, 74, 113, 14);
		getContentPane().add(phoneNrLabel);
		
		addressLabel = new JLabel();
		addressLabel.setBounds(129, 99, 113, 14);
		getContentPane().add(addressLabel);
		
		statusLabel = new JLabel();
		statusLabel.setBounds(129, 124, 113, 14);
		getContentPane().add(statusLabel);
		
		passengerNrLabel = new JLabel();
		passengerNrLabel.setBounds(129, 194, 113, 14);
		getContentPane().add(passengerNrLabel);
		
		createDateLabel = new JLabel();
		createDateLabel.setBounds(129, 219, 113, 14);
		getContentPane().add(createDateLabel);
		
		customerRemarksTextArea = new JTextArea();
		customerRemarksTextArea.setBounds(126, 149, 145, 34);
		getContentPane().add(customerRemarksTextArea);
		initialize();
	}
	
	public void initialize(){
		setBounds(200, 200, 450, 342);
		this.setVisible(true);
	}
	
	public void setAllDetailsInLabel(){
		setOrderDetails();
		setCustomerDetails();
	}

	public void setOrderDetails(){
		addressLabel.setText(orderModel.getPickupAddress());
		statusLabel.setText(Settings.orderStatus[orderModel.getStatus()]);
		passengerNrLabel.setText(""+orderModel.getPassengerCount());
		//createDateLabel.setText(orderModel.getCreatedAt());
		customerRemarksTextArea.setText(orderModel.getCustomerRemarks());
	}
	
	public void setCustomerDetails(){
		Customer customer = orderModel.getCustomer();
		lastNameLabel.setText(customer.getSurname());
		phoneNrLabel.setText(""+customer.getPhoneNumber());
	}
}
