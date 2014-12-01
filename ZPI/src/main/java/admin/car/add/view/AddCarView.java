package admin.car.add.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import settings.Settings;
import admin.car.add.controller.AddCarController;
import admin.car.add.model.AddCarModel;

public class AddCarView extends JPanel{
	private AddCarController addCarController;
	private AddCarModel addCarModel;
	private JLabel jlRegistrationNumber;
	private JLabel jlSideNumber;
	private JLabel jlCarCapacity;
	private JTextField tfRegistrationNumber;
	private JTextField tfSideNumber;
	private JSpinner jsCarCapacity;

	public AddCarView(AddCarController addCarController, AddCarModel addCarModel){
		this.addCarController = addCarController;
		this.addCarModel = addCarModel;
	}

	public void initialize(){
		setLayout(null);

		int x = 10;
		int y = 10;

		jlRegistrationNumber = new JLabel("Nr rejestracyjny: ");
		jlRegistrationNumber.setBounds(x, y, 150, 20);
		add(jlRegistrationNumber);

		tfRegistrationNumber = new JTextField();
		tfRegistrationNumber.setBounds(x + 160, y, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		add(tfRegistrationNumber);
		
		jlSideNumber = new JLabel("Nr boczny: ");
		jlSideNumber.setBounds(x, y=y+30, 150, 20);
		add(jlSideNumber);
		
		tfSideNumber = new JTextField();
		tfSideNumber.setBounds(x+160, y, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		add(tfSideNumber);
		
		jlCarCapacity = new JLabel("Pojemnoœæ: ");
		jlCarCapacity.setBounds(x, y=y+30, 150, 20);
		add(jlCarCapacity);
		
		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, 6, 1);
		jsCarCapacity = new JSpinner(spinnerModel);
		jsCarCapacity.setBounds(x+160, y, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		add(jsCarCapacity);//COLOR SPINNER ZMIENIC
	}
}
