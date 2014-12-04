package admin.driver.add.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import settings.Settings;
import admin.car.add.view.AddCarView;
import admin.driver.add.controller.AddDriverController;
import admin.driver.add.model.AddDriverModel;
import admin.user.add.controller.AddUserController;
import admin.user.add.model.AddUserModel;
import admin.user.add.view.AddUserView;

public class AddDriverView extends JFrame{
	private AddDriverController addDriverController;
	private AddDriverModel addDriverModel;
	private JPanel mainPanel;
	private AddUserView addUserView;
	private AddUserModel addUserModel;
	private AddUserController addUserController;
	private AddCarView addCarView;
	private JPanel optionPanel;
	private JLabel name;
	private JLabel surname;
	private JLabel pesel;
	private JLabel phone;
	private JLabel license;
//	private JLabel userId;
	private JTextField nameTF;
	private JTextField surnameTF;
	private JTextField peselTF;
	private JTextField phoneTF;
	private JTextField licenseTF;
//	private JTextField userIdTF;
	private JButton addDriver;
	private JButton cancel;
	private JLabel driverLB;

	public AddDriverView(AddDriverController addDriverController, AddDriverModel addDriverModel){
		this.addDriverController = addDriverController;
		this.addDriverModel = addDriverModel;
	}

	public void initialize(){
		this.setBounds(300, 200, 520, 700);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLayout(new GridLayout(4,1));

		int x = 10;
		int y = 10;

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		add(mainPanel);

		addUserView = addDriverController.getUserView();
		add(addUserView);
		
		addCarView = addDriverController.getAddCarView();
		add(addCarView);

		optionPanel = new JPanel();
		optionPanel.setLayout(null);
		add(optionPanel);

		driverLB = new JLabel("Dane kierowcy: ");
		driverLB.setBounds(x, y, 200, 20);
		mainPanel.add(driverLB);

		name = new JLabel("Imiê: ");
		name.setBounds(x, y = y + 30, 60, 20);
		mainPanel.add(name);

		nameTF = new JTextField("kowal");
		nameTF.setBounds(x+80, y, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		mainPanel.add(nameTF);

		surname = new JLabel("Nazwisko: ");
		surname.setBounds(x, y = y + 30, 70, 20);
		mainPanel.add(surname);

		surnameTF = new JTextField("iui");
		surnameTF.setBounds(x + 80, y, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		mainPanel.add(surnameTF);

		pesel = new JLabel("PESEL: ");
		pesel.setBounds(x, y = y + 30, 60, 20);
		mainPanel.add(pesel);

		peselTF = new JTextField("3939292");
		peselTF.setBounds(x+80, y, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		mainPanel.add(peselTF);

		phone = new JLabel("Telefon: ");
		phone.setBounds(x = x + 280, y = 40, 60, 20);
		mainPanel.add(phone);

		phoneTF = new JTextField("920329320");
		phoneTF.setBounds(x + 80, y, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		mainPanel.add(phoneTF);

		license = new JLabel("Licencja: ");
		license.setBounds(x, y = y+30, 60, 20);
		mainPanel.add(license);

		licenseTF = new JTextField("339d3");
		licenseTF.setBounds(x + 80, y, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		mainPanel.add(licenseTF);
/*
		userId = new JLabel("U¿ytkownik: ");
		userId.setBounds(x, y=y+30, 80, 20);
		mainPanel.add(userId);

		userIdTF = new JTextField();
		userIdTF.setBounds(x+80, y, 100, 20);
		mainPanel.add(userIdTF);
*/
		addDriver = new JButton("Dodaj");
		addDriver.setBounds(370, 10, Settings.BUTTON_WIDTH, Settings.BUTTON_HEIGHT);
		addDriver.addActionListener(addDriverController.getAddButtonListener());
		optionPanel.add(addDriver);

		cancel = new JButton("Anuluj");
		cancel.setBounds(10, 10, Settings.BUTTON_WIDTH, Settings.BUTTON_HEIGHT);
		cancel.addActionListener(addDriverController.getCancelButtonListener());
		optionPanel.add(cancel);

		// TODO dorobiæ dodawanie samochodu dla kierowcy
		this.setVisible(true);

	}

	public String[] getValues(){
		String[] values = {nameTF.getText(), surnameTF.getText(), peselTF.getText(), phoneTF.getText(),	licenseTF.getText()};
		return values;
	}

	public void clearTextFields(){
		nameTF.setText("");
		surnameTF.setText("");
		peselTF.setText("");
		phoneTF.setText("");
		licenseTF.setText("");
		//userIdTF.setText("");
	}
}
