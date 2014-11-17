package admin.driver.edit.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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


	public ModifyDriverView(ModifyDriverController modifyDriverController, ModifyDriverModel modifyDriverModel){
		this.modifyDriverController = modifyDriverController;
		this.modifyDriverModel = modifyDriverModel;

	}

	public void initialize(){
		this.setBounds(100, 100, 800, 600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		int x = 10;
		int y = 10;

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		this.add(mainPanel);

		jlName = new JLabel("Imiê: ");
		jlName.setBounds(x, y, 60, 20);
		mainPanel.add(jlName);

		tfName = new JTextField();
		tfName.setBounds(x + 70, y, 100, 20);
		mainPanel.add(tfName);

		jlSurname = new JLabel("Nazwisko: ");
		jlSurname.setBounds(x, y=y+30, 60, 20);
		mainPanel.add(jlName);

		tfSurname = new JTextField();
		tfSurname.setBounds(x + 70, y, 100, 20);
		mainPanel.add(tfSurname);

		jlPhoneNumber = new JLabel("Telefon: ");
		jlPhoneNumber.setBounds(x, y=y+30, 60, 20);
		mainPanel.add(jlPhoneNumber);

		tfPhoneNumber = new JTextField();
		tfPhoneNumber.setBounds(x+70, y, 100, 20);
		mainPanel.add(tfPhoneNumber);

		jlLicenseNumber = new JLabel("Licencja: ");
		jlLicenseNumber.setBounds(x, y=y+30, 60, 100);
		mainPanel.add(jlLicenseNumber);

		tfLicenseNumber = new JTextField();
		tfLicenseNumber.setBounds(x+70, y, 100, 20);
		mainPanel.add(tfLicenseNumber);

		jlPesel = new JLabel("PESEL: ");
		jlPesel.setBounds(x, y=y+30, 60, 20);
		mainPanel.add(jlPesel);

		tfPesel = new JTextField();
		tfPesel.setBounds(x+70, y, 100, 20);
		mainPanel.add(tfPesel);

		jlStatus = new JLabel("Status: ");
		jlStatus.setBounds(x, y=y+30, 60, 20);
		mainPanel.add(jlStatus);

		tfStatus = new JTextField();
		tfStatus.setBounds(x+70, y, 100, 20);
		mainPanel.add(tfStatus);

		jlCarId = new JLabel("ID samochodu: ");
		jlCarId.setBounds(x, y=y+30, 120, 20);
		mainPanel.add(jlCarId);
		
		tfCarId = new JTextField();
		tfCarId.setBounds(x+130, y, 100, 20);
		mainPanel.add(tfCarId);
		
		jlUserId = new JLabel("Id u¿ytkownika: ");
		jlUserId.setBounds(x, y=y+30, 120, 20);
		mainPanel.add(jlUserId);
		
		tfUserId = new JTextField();
		tfUserId.setBounds(x+130, y, 100, 20);
		mainPanel.add(tfUserId);
		

		this.setVisible(true);
	}
}
