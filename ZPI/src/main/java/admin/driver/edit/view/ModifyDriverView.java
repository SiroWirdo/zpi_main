package admin.driver.edit.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	private JLabel jlCarId;
	private JLabel jlUserId;
	private JTextField tfName;
	private JTextField tfSurname;
	private JTextField tfPhoneNumber;
	private JTextField tfLicenseNumber;
	private JTextField tfPesel;
	private JTextField tfStatus;
	private JTextField tfCarId;
	private JTextField tfUserId;
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

		int x = 10;
		int y = 10;

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		this.add(mainPanel);

		jlName = new JLabel("Imi�: ");
		jlName.setBounds(x, y, 60, 20);
		mainPanel.add(jlName);

		tfName = new JTextField();
		tfName.setBounds(x + 130, y, 100, 20);
		mainPanel.add(tfName);

		jlSurname = new JLabel("Nazwisko: ");
		jlSurname.setBounds(x, y=y+30, 100, 20);
		mainPanel.add(jlSurname);

		tfSurname = new JTextField();
		tfSurname.setBounds(x + 130, y, 100, 20);
		mainPanel.add(tfSurname);

		jlPhoneNumber = new JLabel("Telefon: ");
		jlPhoneNumber.setBounds(x, y=y+30, 60, 20);
		mainPanel.add(jlPhoneNumber);

		tfPhoneNumber = new JTextField();
		tfPhoneNumber.setBounds(x+130, y, 100, 20);
		mainPanel.add(tfPhoneNumber);

		jlLicenseNumber = new JLabel("Licencja: ");
		jlLicenseNumber.setBounds(x, y=y+30, 60, 20);
		mainPanel.add(jlLicenseNumber);

		tfLicenseNumber = new JTextField();
		tfLicenseNumber.setBounds(x+130, y, 100, 20);
		mainPanel.add(tfLicenseNumber);

		jlPesel = new JLabel("PESEL: ");
		jlPesel.setBounds(x, y=y+30, 60, 20);
		mainPanel.add(jlPesel);

		tfPesel = new JTextField();
		tfPesel.setBounds(x+130, y, 100, 20);
		mainPanel.add(tfPesel);

		jlStatus = new JLabel("Status: ");
		jlStatus.setBounds(x, y=y+30, 60, 20);
		mainPanel.add(jlStatus);

		tfStatus = new JTextField();
		tfStatus.setBounds(x+130, y, 100, 20);
		mainPanel.add(tfStatus);

		jlCarId = new JLabel("Id samochodu: ");
		jlCarId.setBounds(x, y=y+30, 120, 20);
		mainPanel.add(jlCarId);

		tfCarId = new JTextField();
		tfCarId.setBounds(x+130, y, 100, 20);
		mainPanel.add(tfCarId);

		jlUserId = new JLabel("Id u�ytkownika: ");
		jlUserId.setBounds(x, y=y+30, 120, 20);
		mainPanel.add(jlUserId);

		tfUserId = new JTextField();
		tfUserId.setBounds(x+130, y, 100, 20);
		mainPanel.add(tfUserId);

		cancel = new JButton("Anuluj");
		cancel.setBounds(x, y=y+40, 100, 20);
		cancel.addActionListener(modifyDriverController.getCancelButtonListener());
		mainPanel.add(cancel);

		edit = new JButton("Edytuj");
		edit.setBounds(x+130, y, 100, 20);
		edit.addActionListener(modifyDriverController.getEditButtonListener());
		mainPanel.add(edit);
		//TODO Dodac edycje hasla

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
		if(driver.getUser() != null){
			userId = driver.getUser().getObjectId();
		}
		tfUserId.setText(userId);
		this.repaint();
	}

	public Driver getDriver(){
		return this.driver;
	}

	public String[] getValues(){
		String[] values = {tfName.getText(), tfSurname.getText(), tfPhoneNumber.getText(), tfLicenseNumber.getText(),
				tfPesel.getText(), tfStatus.getText(), tfCarId.getText(), tfUserId.getText()};
		return values;
	}

}