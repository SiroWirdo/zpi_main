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
	private JTextField textField;
	private JTextField textField_1;
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
		lblNazwisko.setBounds(31, 45, 66, 14);
		add(lblNazwisko);
		
		JLabel lblNumerTelefonu = new JLabel("Nr telefonu:");
		lblNumerTelefonu.setBounds(31, 87, 79, 14);
		add(lblNumerTelefonu);
		
		JLabel lblAdresOdbioru = new JLabel("Adres odbioru:");
		lblAdresOdbioru.setBounds(31, 129, 79, 14);
		add(lblAdresOdbioru);
		
		JLabel lblLiczbaPasaerw = new JLabel("Liczba pasa\u017Cer\u00F3w:");
		lblLiczbaPasaerw.setBounds(31, 172, 96, 14);
		add(lblLiczbaPasaerw);
		
		JLabel lblUwagi = new JLabel("Uwagi:");
		lblUwagi.setBounds(31, 210, 46, 14);
		add(lblUwagi);
		
		textField = new JTextField();
		textField.setBounds(187, 42, 120, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(188, 84, 119, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		pickUpAddressTextField = new JTextField();
		pickUpAddressTextField.setBounds(187, 126, 120, 20);
		add(pickUpAddressTextField);
		pickUpAddressTextField.setColumns(10);
		
		passangerCountTextField = new JTextField();
		passangerCountTextField.setBounds(187, 169, 120, 20);
		add(passangerCountTextField);
		passangerCountTextField.setColumns(10);
		
		customerRemarksTextArea = new JTextArea();
		customerRemarksTextArea.setBounds(190, 210, 117, 54);
		add(customerRemarksTextArea);
		
		addOrderBtn = new JButton("Dodaj");
		addOrderBtn.addActionListener(this);
		addOrderBtn.setBounds(362, 340, 120, 23);
		add(addOrderBtn);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//if(e.getSource() == addOrderBtn){
		if(e.getActionCommand().equals("Dodaj")){
			System.out.println("Klikniêcie buttonu");
			String ad = pickUpAddressTextField.getText();
			String re = customerRemarksTextArea.getText();
			System.out.println("addres: " + ad + "remarks" + re);
			Integer pc = new Integer(passangerCountTextField.getText());
			orderController.addOrder( ad, re, pc);	
		}
		
	}
}
