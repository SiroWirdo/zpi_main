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
		
		//initialize();
	}	
	
	public void initialize(){
		setBounds(200, 200, 475, 208);
		
		JLabel lblNrTel = new JLabel("nr tel.:");
		lblNrTel.setBounds(30, 51, 60, 14);
		getContentPane().add(lblNrTel);
		
		JLabel lblLicencja = new JLabel("licencja:");
		lblLicencja.setBounds(30, 76, 60, 14);
		getContentPane().add(lblLicencja);
		
		JLabel lblPesel = new JLabel("PESEL:");
		lblPesel.setBounds(30, 101, 60, 14);
		getContentPane().add(lblPesel);
		
		JLabel lblStatus = new JLabel("status:");
		lblStatus.setBounds(30, 126, 60, 14);
		getContentPane().add(lblStatus);
		
		fullNameLabel = new JLabel();
		fullNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fullNameLabel.setBounds(30, 4, 183, 36);
		getContentPane().add(fullNameLabel);
		
		JLabel lblSamochd = new JLabel("Samoch\u00F3d");
		lblSamochd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSamochd.setBounds(247, 4, 129, 36);
		getContentPane().add(lblSamochd);
		
		JLabel lblNrRejestracyjny = new JLabel("nr rejestracyjny:");
		lblNrRejestracyjny.setBounds(225, 51, 100, 14);
		getContentPane().add(lblNrRejestracyjny);
		
		JLabel lblNrBoczny = new JLabel("nr boczny:");
		lblNrBoczny.setBounds(225, 76, 100, 14);
		getContentPane().add(lblNrBoczny);
		
		phoneNrLabel = new JLabel();
		phoneNrLabel.setBounds(86, 51, 98, 14);
		getContentPane().add(phoneNrLabel);
		
		licenseNumberLabel = new JLabel();
		licenseNumberLabel.setBounds(86, 76, 98, 14);
		getContentPane().add(licenseNumberLabel);
		
		peselLabel = new JLabel();
		peselLabel.setBounds(86, 101, 98, 14);
		getContentPane().add(peselLabel);
		
		statusLabel = new JLabel();
		statusLabel.setBounds(86, 126, 98, 14);
		getContentPane().add(statusLabel);
		
		registrationNrLabel = new JLabel();
		registrationNrLabel.setBounds(335, 51, 98, 14);
		getContentPane().add(registrationNrLabel);
		
		sideNrLabel = new JLabel();
		sideNrLabel.setBounds(335, 76, 98, 14);
		getContentPane().add(sideNrLabel);
		
		this.setVisible(true);
	}	
	
	public void setAllDataInLabel(){
		setDriverData();
		setCarData();
	}
	
	public void setDriverData(){
		fullNameLabel.setText(driverModel.getName() + " " + driverModel.getSurname());
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
