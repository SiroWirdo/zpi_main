package order_info.view;

import javax.swing.JPanel;

import model.Customer;
import order_info.controller.OrderInfoController;
import order_info.model.OrderInfoModel;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import settings.Settings;

import java.awt.Color;

public class SingleInfoPanel extends JComponent{
	private OrderInfoModel infoModel;
	private OrderInfoController infoController;
	
	private JLabel surnameText;
	private JLabel phoneText;
	private JLabel statusText;
	private JLabel driverText;
	private JLabel infText;
	
	public SingleInfoPanel(OrderInfoController infoController, OrderInfoModel infoModel) {
		setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		this.infoModel = infoModel;
		this.infoController = infoController;
		setLayout(null);
		
		initialize();
	}
	
	public void initialize(){
		JLabel surnameLabel = new JLabel("nazwisko:");
		surnameLabel.setBounds(20, 22, 81, 14);
		add(surnameLabel);
		
		JLabel phoneLabel = new JLabel("telefon:");
		phoneLabel.setBounds(20, 44, 46, 14);
		add(phoneLabel);
		
		JLabel statusLabel = new JLabel("status:");
		statusLabel.setBounds(20, 69, 46, 14);
		add(statusLabel);
		
		JLabel driverLabel = new JLabel("<html><body>przydzielony<p>kierowca:");
		driverLabel.setBounds(20, 94, 81, 23);
		add(driverLabel);
		
		JLabel infoLabel = new JLabel("info:");
		infoLabel.setBounds(20, 131, 46, 14);
		add(infoLabel);
		
		JButton resumeBtn = new JButton("Wzn\u00F3w");
		resumeBtn.setBounds(20, 164, 89, 23);
		add(resumeBtn);
		
		JButton cancelBtn = new JButton("Anuluj");
		cancelBtn.setBounds(156, 164, 89, 23);
		add(cancelBtn);
		
		surnameText = new JLabel();
		surnameText.setBounds(125, 22, 46, 14);
		add(surnameText);
		
		phoneText = new JLabel();
		phoneText.setBounds(125, 44, 46, 14);
		add(phoneText);
		
		statusText = new JLabel();
		statusText.setBounds(125, 69, 46, 14);
		add(statusText);
		
		driverText = new JLabel();
		driverText.setBounds(125, 91, 46, 14);
		add(driverText);
		
		infText = new JLabel();
		infText.setBounds(76, 121, 169, 35);
		add(infText);
	}
	
	public void setTextLabels(Customer c, int status, String driverFullName){
		surnameText.setText(c.getSurname());
		phoneText.setText("" + c.getPhoneNumber());
		statusText.setText(Settings.orderStatus[status]);
		driverText.setText(driverFullName);
	}
	
	public void setInfo(String info){
		infText.setText(info);
	}
}
