package drivers.view;
import javax.swing.JDialog;

import drivers.controller.DriversController;
import model.Car;
import model.Driver;

import javax.swing.JLabel;

import settings.Settings;

import java.awt.Font;

public class DriverDetailsView extends JDialog {

	Driver driverModel;
	DriversController driversController;
	
	private JLabel fullNameLabel;
	private JLabel phoneNrLabel;
	private JLabel licenseNumberLabel;
	private JLabel peselLabel;
	private JLabel statusLabel;
	private JLabel registrationNrLabel;
	private JLabel sideNrLabel;
	
	public DriverDetailsView(DriversController driversController, Driver driverModel) {
		this.driverModel = driverModel;
		this.driversController = driversController;
		getContentPane().setLayout(null);
		
		initialize();
	}	
	
	public void initialize(){
		setBounds(200, 200, 435, 198);
		
		JLabel lblNrTel = new JLabel("nr tel.:");
		lblNrTel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNrTel.setBounds(20, 51, 60, 14);
		getContentPane().add(lblNrTel);
		
		JLabel lblLicencja = new JLabel("licencja:");
		lblLicencja.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLicencja.setBounds(20, 76, 60, 14);
		getContentPane().add(lblLicencja);
		
		JLabel lblPesel = new JLabel("PESEL:");
		lblPesel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPesel.setBounds(20, 101, 60, 14);
		getContentPane().add(lblPesel);
		
		JLabel lblStatus = new JLabel("status:");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatus.setBounds(20, 126, 60, 14);
		getContentPane().add(lblStatus);
		
		fullNameLabel = new JLabel();
		fullNameLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		fullNameLabel.setBounds(20, 4, 365, 36);
		getContentPane().add(fullNameLabel);
		
		JLabel lblSamochd = new JLabel("Samoch\u00F3d");
		lblSamochd.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSamochd.setBounds(194, 38, 129, 36);
		getContentPane().add(lblSamochd);
		
		JLabel lblNrRejestracyjny = new JLabel("nr rejestracyjny:");
		lblNrRejestracyjny.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNrRejestracyjny.setBounds(194, 76, 100, 14);
		getContentPane().add(lblNrRejestracyjny);
		
		JLabel lblNrBoczny = new JLabel("nr boczny:");
		lblNrBoczny.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNrBoczny.setBounds(194, 101, 100, 14);
		getContentPane().add(lblNrBoczny);
		
		phoneNrLabel = new JLabel();
		phoneNrLabel.setBounds(74, 51, 98, 14);
		getContentPane().add(phoneNrLabel);
		
		licenseNumberLabel = new JLabel();
		licenseNumberLabel.setBounds(74, 76, 98, 14);
		getContentPane().add(licenseNumberLabel);
		
		peselLabel = new JLabel();
		peselLabel.setBounds(68, 101, 105, 14);
		getContentPane().add(peselLabel);
		
		statusLabel = new JLabel();
		statusLabel.setBounds(74, 126, 105, 14);
		getContentPane().add(statusLabel);
		
		registrationNrLabel = new JLabel();
		registrationNrLabel.setBounds(304, 76, 105, 14);
		getContentPane().add(registrationNrLabel);
		
		sideNrLabel = new JLabel();
		sideNrLabel.setBounds(304, 101, 105, 14);
		getContentPane().add(sideNrLabel);
		
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.setTitle("Szczegóły kierowcy");
	}	
	
	public void setAllDataInLabel(){
		setDriverData();
		setCarData();
	}
	
	public void setDriverData(){
		fullNameLabel.setText(driverModel.getSurname() + " " + driverModel.getName());
		phoneNrLabel.setText(""+driverModel.getPhoneNumber());
		licenseNumberLabel.setText(driverModel.getLicenseNumber());
		peselLabel.setText(""+driverModel.getPESEL());
		statusLabel.setText(Settings.driverStatus[driverModel.getStatus()]);
	}
	
	public void setCarData(){
		Car car = driverModel.getCar();
		registrationNrLabel.setText(car.getRegistrationNumber());
		sideNrLabel.setText(Integer.toString(car.getSideNumber()));
	}
}
