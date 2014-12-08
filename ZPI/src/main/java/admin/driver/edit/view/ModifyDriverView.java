package admin.driver.edit.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import settings.Settings;
import model.Driver;
import admin.driver.edit.controller.ModifyDriverController;
import admin.driver.edit.model.ModifyDriverModel;


public class ModifyDriverView extends JFrame{
	private ModifyDriverController modifyDriverController;
	private ModifyDriverModel modifyDriverModel;
	private JLabel jlName;
	private JLabel jlSurname;
	private JLabel jlPhoneNumber;
	private JLabel jlLicenseNumber;
	private JLabel jlPesel;
	private JLabel jlStatus;
	//private JLabel jlCarId;
	private JLabel jlMail;
	private JTextField tfName;
	private JTextField tfSurname;
	private JTextField tfPhoneNumber;
	private JTextField tfLicenseNumber;
	private JTextField tfPesel;
	private JTextField tfStatus;
	private JTextField tfCarId;
	private JTextField tfMail;
	private JButton editCar;
	private JButton resetPassword;
	private JButton edit;
	private JButton cancel;
	private JPanel mainPanel;
	private Driver driver;


	public ModifyDriverView(ModifyDriverController modifyDriverController, ModifyDriverModel modifyDriverModel){
		this.modifyDriverController = modifyDriverController;
		this.modifyDriverModel = modifyDriverModel;

	}

	public void initialize(){
		this.setBounds(500, 300, 300, 350);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Edycja kierowcy");
		this.setResizable(false);

		int x = 10;
		int y = 10;

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		this.add(mainPanel);

		jlName = new JLabel("Imię: ");
		jlName.setBounds(x, y, 60, 20);
		mainPanel.add(jlName);

		tfName = new JTextField();
		tfName.setBounds(x + 130, y, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		mainPanel.add(tfName);

		jlSurname = new JLabel("Nazwisko: ");
		jlSurname.setBounds(x, y=y+30, 100, 20);
		mainPanel.add(jlSurname);

		tfSurname = new JTextField();
		tfSurname.setBounds(x + 130, y, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		mainPanel.add(tfSurname);

		jlPhoneNumber = new JLabel("Telefon: ");
		jlPhoneNumber.setBounds(x, y=y+30, 60, 20);
		mainPanel.add(jlPhoneNumber);

		tfPhoneNumber = new JTextField();
		tfPhoneNumber.setBounds(x+130, y, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		mainPanel.add(tfPhoneNumber);

		jlLicenseNumber = new JLabel("Licencja: ");
		jlLicenseNumber.setBounds(x, y=y+30, 60, 20);
		mainPanel.add(jlLicenseNumber);

		tfLicenseNumber = new JTextField();
		tfLicenseNumber.setBounds(x+130, y, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		mainPanel.add(tfLicenseNumber);

		jlPesel = new JLabel("PESEL: ");
		jlPesel.setBounds(x, y=y+30, 60, 20);
		mainPanel.add(jlPesel);

		tfPesel = new JTextField();
		tfPesel.setBounds(x+130, y, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		mainPanel.add(tfPesel);

		jlStatus = new JLabel("Status: ");
		jlStatus.setBounds(x, y=y+30, 60, 20);
		mainPanel.add(jlStatus);

		tfStatus = new JTextField();
		tfStatus.setBounds(x+130, y, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		mainPanel.add(tfStatus);
/*
		jlCarId = new JLabel("Id samochodu: ");
		jlCarId.setBounds(x, y=y+30, 120, 20);
		mainPanel.add(jlCarId);
*/
		tfCarId = new JTextField();
		//tfCarId.setBounds(x+130, y, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		mainPanel.add(tfCarId);

		jlMail = new JLabel("E-mail: ");
		//jlMail.setBounds(x, y=y+30, 60, 20);
		mainPanel.add(jlMail);

		tfMail = new JTextField();
		//tfMail.setBounds(x+130, y, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		mainPanel.add(tfMail);

		jlMail.setVisible(false);
		tfMail.setVisible(false);
		tfCarId.setVisible(false);
		
		editCar = new JButton("Edytuj samochód");
		editCar.setBounds(x, y=y+30, 255, 25);
		editCar.addActionListener(modifyDriverController.getEditCarButtonListener());
		mainPanel.add(editCar);

		resetPassword = new JButton("Zresetuj hasło");
		resetPassword.setBounds(x, y=y+30, 255, 25);
		resetPassword.addActionListener(modifyDriverController.getRestartPasswordButtonListener());
		mainPanel.add(resetPassword);

		cancel = new JButton("Anuluj");
		cancel.setBounds(x, y=y+40, Settings.BUTTON_WIDTH, Settings.BUTTON_HEIGHT);
		cancel.addActionListener(modifyDriverController.getCancelButtonListener());
		mainPanel.add(cancel);

		edit = new JButton("Edytuj");
		edit.setBounds(x+130, y, Settings.BUTTON_WIDTH, Settings.BUTTON_HEIGHT);
		edit.addActionListener(modifyDriverController.getEditButtonListener());
		mainPanel.add(edit);
		//TODO Dodac edycje hasla. Zmiana użytkownika po username a nie po id

		this.setVisible(true);
	}

	public void setValues(Driver driver){
		this.driver = driver;
		tfName.setText(driver.getName());
		tfSurname.setText(driver.getSurname());
		tfPhoneNumber.setText(new Long(driver.getPhoneNumber()).toString());
		tfLicenseNumber.setText(driver.getLicenseNumber());
		tfPesel.setText(new Long(driver.getPESEL()).toString());
		tfStatus.setText(new Integer(driver.getStatus()).toString());
		String carId = "";
		if(driver.getCar() != null){
			carId = driver.getCar().getObjectId();
		}
		tfCarId.setText(carId);
		String userId = "";
		String email = "";
		if(driver.getUser() != null){
			userId = driver.getUser().getObjectId();
			email = modifyDriverModel.getUserMail(userId);

		}
		tfMail.setText(email);
		this.repaint();
	}

	public Driver getDriver(){
		return this.driver;
	}

	public String[] getValues(){
		String[] values = {tfName.getText(), tfSurname.getText(), tfPhoneNumber.getText(), tfLicenseNumber.getText(),
				tfPesel.getText(), tfStatus.getText(), tfCarId.getText(), tfMail.getText()};
		return values;
	}
	
	public String getCarId(){
		return tfCarId.getText();
	}

}
