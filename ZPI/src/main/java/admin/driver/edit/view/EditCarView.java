package admin.driver.edit.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import model.Car;
import settings.Settings;
import admin.driver.edit.controller.EditCarController;
import admin.driver.edit.model.EditCarModel;

public class EditCarView extends JFrame {
	private EditCarController editCarController;
	private EditCarModel editCarModel;
	private JPanel mainPanel;
	private JLabel jlRegistrationNumber;
	private JLabel jlSideNumber;
	private JLabel jlCarCapacity;
	private JTextField tfRegistrationNumber;
	private JTextField tfSideNumber;
	private JSpinner jsCarCapacity;
	private JButton edit;
	private JButton cancel;
	private Car car;
	
	public EditCarView(EditCarController editCarController, EditCarModel editCarModel){
		this.editCarController = editCarController;
		this.editCarModel = editCarModel;
	}
	
	public void initialize(){
		this.setBounds(500, 300, 350, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Edycja samochodu");
		int x = 10;
		int y = 10;
		
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		this.add(mainPanel);

		jlRegistrationNumber = new JLabel("Nr rejestracyjny: ");
		jlRegistrationNumber.setBounds(x, y, 150, 20);
		mainPanel.add(jlRegistrationNumber);

		tfRegistrationNumber = new JTextField();
		tfRegistrationNumber.setBounds(x + 160, y, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		mainPanel.add(tfRegistrationNumber);

		jlSideNumber = new JLabel("Nr boczny: ");
		jlSideNumber.setBounds(x, y=y+30, 150, 20);
		mainPanel.add(jlSideNumber);

		tfSideNumber = new JTextField();
		tfSideNumber.setBounds(x+160, y, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		mainPanel.add(tfSideNumber);

		jlCarCapacity = new JLabel("Pojemność: ");
		jlCarCapacity.setBounds(x, y=y+30, 150, 20);
		mainPanel.add(jlCarCapacity);

		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, 6, 1);
		jsCarCapacity = new JSpinner(spinnerModel);
		jsCarCapacity.setBounds(x+160, y, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		mainPanel.add(jsCarCapacity);
		
		edit = new JButton("Edytuj");
		edit.setBounds(x+160, y=y+40, Settings.BUTTON_WIDTH, Settings.BUTTON_HEIGHT);
		edit.addActionListener(editCarController.getEditButtonListener());
		mainPanel.add(edit);
		
		cancel = new JButton("Anuluj");
		cancel.setBounds(x, y, Settings.BUTTON_WIDTH, Settings.BUTTON_HEIGHT);
		cancel.addActionListener(editCarController.getCancelButtonListener());
		mainPanel.add(cancel);
		
		this.setVisible(true);
	}
	
	public void setValues(Car car){
		this.car = car;
		tfRegistrationNumber.setText(car.getRegistrationNumber());
		tfSideNumber.setText((new Integer(car.getSideNumber()).toString()));
		jsCarCapacity.setValue(car.getCarCapacity());
	}
	
	public String[] getValues(){
		return new String[]{tfRegistrationNumber.getText(), tfSideNumber.getText(), jsCarCapacity.getValue().toString()};
	}
	
	public Car getCar(){
		return car;
	}
}
