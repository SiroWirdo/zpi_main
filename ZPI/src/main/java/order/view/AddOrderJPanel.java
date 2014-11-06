package order.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JButton;

import org.parse4j.ParseObject;

import order.controller.OrderController;
import order.model.OrderModel;
import model.Order;

import javax.swing.AbstractAction;
import javax.swing.Action;

public class AddOrderJPanel extends JPanel implements ActionListener{
	OrderModel orderModel;
	OrderController orderController;
	private JTextField surnameTextField;
	private JTextField phoneNumberTextField;
	private JTextField pickUpAddressTextField;
	private JTextField passangerCountTextField;
	private JButton addOrderBtn;
	private JTextArea customerRemarksTextArea;

	/**
	 * Create the panel.
	 */
	public AddOrderJPanel(OrderController orderController, OrderModel orderModel) {
		setLayout(null);
		this.orderModel = orderModel;
		this.orderController = orderController;
		initialize();

	}
	
	public void initialize(){
		JLabel lblNazwisko = new JLabel("Nazwisko:");
		lblNazwisko.setBounds(31, 45, 135, 14);
		add(lblNazwisko);
		
		JLabel lblNumerTelefonu = new JLabel("Nr telefonu:");
		lblNumerTelefonu.setBounds(31, 87, 135, 14);
		add(lblNumerTelefonu);
		
		JLabel lblAdresOdbioru = new JLabel("Adres odbioru:");
		lblAdresOdbioru.setBounds(31, 129, 135, 14);
		add(lblAdresOdbioru);
		
		JLabel lblLiczbaPasaerw = new JLabel("Liczba pasa\u017Cer\u00F3w:");
		lblLiczbaPasaerw.setBounds(31, 172, 135, 14);
		add(lblLiczbaPasaerw);
		
		JLabel lblUwagi = new JLabel("Uwagi:");
		lblUwagi.setBounds(31, 210, 135, 14);
		add(lblUwagi);
		
		this.surnameTextField = new JTextField();
		this.surnameTextField.setBounds(187, 42, 177, 20);
		add(this.surnameTextField);
		
		phoneNumberTextField = new JTextField();
		phoneNumberTextField.setBounds(188, 84, 176, 20);
		add(phoneNumberTextField);
		
		pickUpAddressTextField = new JTextField();
		pickUpAddressTextField.setBounds(187, 126, 177, 20);
		add(pickUpAddressTextField);
		
		passangerCountTextField = new JTextField();
		passangerCountTextField.setBounds(187, 169, 177, 20);
		add(passangerCountTextField);
		
		customerRemarksTextArea = new JTextArea();
		customerRemarksTextArea.setBounds(190, 210, 174, 50);
		add(customerRemarksTextArea);
		
		addOrderBtn = new JButton("Dodaj");
		addOrderBtn.addActionListener(this);
		addOrderBtn.setBounds(244, 304, 120, 23);
		add(addOrderBtn);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//if(e.getSource() == addOrderBtn){
		if(e.getActionCommand().equals("Dodaj")){
				orderController.addOrder(surnameTextField.getText(),
						new Integer(phoneNumberTextField.getText()),
						pickUpAddressTextField.getText(),
						customerRemarksTextArea.getText(),
						new Integer(passangerCountTextField.getText()));
		}
		
	}
}
