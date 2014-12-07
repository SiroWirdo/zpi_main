package order.view;

import javax.swing.JDialog;

import model.Customer;
import model.Order;
import order.controller.OrderController;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.util.Date;

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
	private JTextArea customerRemarksTextArea;
	private JLabel lblUwagiKlienta;
	
	public OrderDetailsView(OrderController orderDetailsController, Order orderModel) {	
		this.orderDetailsController = orderDetailsController;
		this.orderModel = orderModel;
		getContentPane().setLayout(null);
		setTitle("Szczegóły klienta");
		lastNameLabel = new JLabel();
		lastNameLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lastNameLabel.setBounds(21, 11, 309, 37);
		getContentPane().add(lastNameLabel);
		
		JLabel lblNrTelefonu = new JLabel("nr telefonu:");
		lblNrTelefonu.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNrTelefonu.setBounds(21, 55, 85, 14);
		getContentPane().add(lblNrTelefonu);
		
		JLabel lblAdres = new JLabel("adres:");
		lblAdres.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAdres.setBounds(21, 80, 46, 14);
		getContentPane().add(lblAdres);
		
		JLabel lblStatus = new JLabel("status:");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatus.setBounds(21, 119, 46, 14);
		getContentPane().add(lblStatus);
		
		lblUwagiKlienta = new JLabel("<html><body>uwagi<p>klienta:");
		lblUwagiKlienta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUwagiKlienta.setBounds(21, 182, 85, 28);
		getContentPane().add(lblUwagiKlienta);
		
		JLabel lblLiczbaPasaerw = new JLabel("<html><body>liczba<p>pasa\u017Cer\u00F3w:");
		lblLiczbaPasaerw.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLiczbaPasaerw.setBounds(21, 139, 75, 34);
		getContentPane().add(lblLiczbaPasaerw);
		
		phoneNrLabel = new JLabel();
		phoneNrLabel.setBounds(113, 59, 157, 14);
		getContentPane().add(phoneNrLabel);
		
		addressLabel = new JLabel();
		addressLabel.setBounds(113, 80, 157, 37);
		getContentPane().add(addressLabel);
		
		statusLabel = new JLabel();
		statusLabel.setBounds(113, 123, 157, 14);
		getContentPane().add(statusLabel);
		
		passengerNrLabel = new JLabel();
		passengerNrLabel.setBounds(113, 154, 113, 14);
		getContentPane().add(passengerNrLabel);
		
		customerRemarksTextArea = new JTextArea();
		customerRemarksTextArea.setBounds(113, 184, 157, 53);
		customerRemarksTextArea.setLineWrap(true);
		customerRemarksTextArea.setEditable(false);
		customerRemarksTextArea.setFocusable(false);
		customerRemarksTextArea.setBackground(new Color(43,82,102));
		customerRemarksTextArea.setForeground(Color.WHITE);
		customerRemarksTextArea.setBorder(null);
		getContentPane().add(customerRemarksTextArea);
		initialize();
	}
	
	public void initialize(){
		setBounds(200, 200, 294, 285);
		setAlwaysOnTop(true);
		this.setVisible(true);
	}
	
	public void setAllDetailsInLabel(){
		setOrderDetails();
		setCustomerDetails();
	}

	public void setOrderDetails(){
		String[] addr = orderModel.getPickupAddress().split(",");
		StringBuilder sb = new StringBuilder("<html><body>");
		for(int i = 0; i < addr.length - 1; i++){
			sb.append(addr[i]);
			sb.append("<p>");
		}
		addressLabel.setText(sb.toString());
		
		statusLabel.setText(Settings.orderStatus[orderModel.getStatus()]);
		passengerNrLabel.setText(""+orderModel.getPassengerCount());
//		createDateLabel.setText(""+orderModel.getCreatedAt());
		if(orderModel.getCustomerRemarks()!= null && orderModel.getCustomerRemarks()!= "" && orderModel.getCustomerRemarks().trim()!= ""){
			customerRemarksTextArea.setText(orderModel.getCustomerRemarks());
		}
		else{
//			lblUwagiKlienta.setVisible(false);
			customerRemarksTextArea.setText("brak uwag");
		}
	}
	
	public void setCustomerDetails(){
		Customer customer = orderModel.getCustomer();
		lastNameLabel.setText(customer.getSurname());
		phoneNrLabel.setText(""+customer.getPhoneNumber());
	}
}
