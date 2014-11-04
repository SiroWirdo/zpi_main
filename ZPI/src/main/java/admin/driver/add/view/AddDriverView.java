package admin.driver.add.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import admin.driver.add.controller.AddDriverController;
import admin.driver.add.model.AddDriverModel;

public class AddDriverView extends JFrame{
	private AddDriverController addDriverController;
	private AddDriverModel addDriverModel;
	private JPanel mainPanel;
	private JLabel name;
	private JLabel surname;
	private JLabel pesel;
	private JLabel phone;
	private JLabel license;
	private JLabel userId;
	private JTextField nameTF;
	private JTextField surnameTF;
	private JTextField peselTF;
	private JTextField phoneTF;
	private JTextField licenseTF;
	private JTextField userIdTF;
	private JButton addDriver;
	private JButton cancel;
	
	public AddDriverView(AddDriverController addDriverController, AddDriverModel addDriverModel){
		this.addDriverController = addDriverController;
		this.addDriverModel = addDriverModel;
	}
	
	public void initialize(){
		this.setBounds(300, 200, 520, 225);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		int x = 10;
		int y = 10;
		
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		add(mainPanel);
		
		name = new JLabel("Imiê: ");
		name.setBounds(x, y, 60, 20);
		mainPanel.add(name);
		
		nameTF = new JTextField();
		nameTF.setBounds(x+80, y, 100, 20);
		mainPanel.add(nameTF);
		
		surname = new JLabel("Nazwisko: ");
		surname.setBounds(x, y = y + 30, 70, 20);
		mainPanel.add(surname);
		
		surnameTF = new JTextField();
		surnameTF.setBounds(x + 80, y, 100, 20);
		mainPanel.add(surnameTF);
		
		pesel = new JLabel("PESEL: ");
		pesel.setBounds(x, y = y + 30, 60, 20);
		mainPanel.add(pesel);
		
		peselTF = new JTextField();
		peselTF.setBounds(x+80, y, 100, 20);
		mainPanel.add(peselTF);
		
		phone = new JLabel("Telefon: ");
		phone.setBounds(x = x + 280, y = 10, 60, 20);
		mainPanel.add(phone);
		
		phoneTF = new JTextField();
		phoneTF.setBounds(x + 80, y, 100, 20);
		mainPanel.add(phoneTF);
		
		license = new JLabel("Licencja: ");
		license.setBounds(x, y = y+30, 60, 20);
		mainPanel.add(license);
		
		licenseTF = new JTextField();
		licenseTF.setBounds(x + 80, y, 100, 20);
		mainPanel.add(licenseTF);
		
		userId = new JLabel("U¿ytkownik: ");
		userId.setBounds(x, y=y+30, 80, 20);
		mainPanel.add(userId);
		
		userIdTF = new JTextField();
		userIdTF.setBounds(x+80, y, 100, 20);
		mainPanel.add(userIdTF);
		
		addDriver = new JButton("Dodaj");
		addDriver.setBounds(370, 130, 100, 30);
		addDriver.addActionListener(addDriverController.getAddButtonListener());
		mainPanel.add(addDriver);
		
		cancel = new JButton("Anuluj");
		cancel.setBounds(10, 130, 100, 30);
		cancel.addActionListener(addDriverController.getCancelButtonListener());
		mainPanel.add(cancel);
		
		
		this.setVisible(true);
		
	}
	
	public String[] getValues(){
		String[] values = {nameTF.getText(), surnameTF.getText(), peselTF.getText(), phoneTF.getText(),	licenseTF.getText(), userIdTF.getText()};
		return values;
	}
	
	public void clearTextFields(){
		nameTF.setText("");
		surnameTF.setText("");
		peselTF.setText("");
		phoneTF.setText("");
		licenseTF.setText("");
		userIdTF.setText("");
	}
}
