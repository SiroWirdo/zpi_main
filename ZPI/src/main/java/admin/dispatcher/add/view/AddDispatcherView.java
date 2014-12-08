package admin.dispatcher.add.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import settings.Settings;
import admin.dispatcher.add.controller.AddDispatcherController;
import admin.dispatcher.add.model.AddDispatcherModel;
import admin.user.add.controller.AddUserController;
import admin.user.add.model.AddUserModel;
import admin.user.add.view.AddUserView;



public class AddDispatcherView extends JFrame{
	private AddDispatcherController addDispatcherController;
	private AddDispatcherModel addDispatcherModel;
	private JPanel mainPanel;
	private AddUserView addUserView;
	private AddUserModel addUserModel;
	private AddUserController addUserController;
	private JPanel optionPanel;
	private JLabel name;
	private JLabel surname;
	private JLabel pesel;
//	private JLabel userId;
	private JTextField nameTF;
	private JTextField surnameTF;
	private JTextField peselTF;
//	private JTextField userIdTF;
	private JButton addDriver;
	private JButton cancel;
	private JLabel driverLB;

	public AddDispatcherView(AddDispatcherController addDispatcherController, AddDispatcherModel addDispatcherModel){
		this.addDispatcherController = addDispatcherController;
		this.addDispatcherModel = addDispatcherModel;
	}

	public void initialize(){
		this.setBounds(300, 200, 520, 600);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLayout(new GridLayout(3,1));
		this.setTitle("Dodawanie dyspozytora");
		this.setResizable(false);

		int x = 10;
		int y = 10;

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		add(mainPanel);

		addUserView = addDispatcherController.getUserView();
		add(addUserView);

		optionPanel = new JPanel();
		optionPanel.setLayout(null);
		add(optionPanel);

		driverLB = new JLabel("Dane kierowcy: ");
		driverLB.setBounds(x, y, 200, 20);
		mainPanel.add(driverLB);

		name = new JLabel("Imię: ");
		name.setBounds(x, y = y + 30, 60, 20);
		mainPanel.add(name);

		nameTF = new JTextField("kowal");
		nameTF.setBounds(x+110, y, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		mainPanel.add(nameTF);

		surname = new JLabel("Nazwisko: ");
		surname.setBounds(x, y = y + 30, 70, 20);
		mainPanel.add(surname);

		surnameTF = new JTextField("iui");
		surnameTF.setBounds(x + 110, y, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		mainPanel.add(surnameTF);

		pesel = new JLabel("PESEL: ");
		pesel.setBounds(x, y=y+30, 70, 20);
		mainPanel.add(pesel);

		peselTF = new JTextField();
		peselTF.setBounds(x+110, y, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		mainPanel.add(peselTF);

/*
		userId = new JLabel("Użytkownik: ");
		userId.setBounds(x, y=y+30, 80, 20);
		mainPanel.add(userId);

		userIdTF = new JTextField();
		userIdTF.setBounds(x+80, y, 100, 20);
		mainPanel.add(userIdTF);
*/
		addDriver = new JButton("Dodaj");
		addDriver.setBounds(370, 10, Settings.BUTTON_WIDTH, Settings.BUTTON_HEIGHT);
		addDriver.addActionListener(addDispatcherController.getAddButtonListener());
		optionPanel.add(addDriver);

		cancel = new JButton("Anuluj");
		cancel.setBounds(10, 10, Settings.BUTTON_WIDTH, Settings.BUTTON_HEIGHT);
		cancel.addActionListener(addDispatcherController.getCancelButtonListener());
		optionPanel.add(cancel);


		this.setVisible(true);

	}

	public String[] getValues(){
		String[] values = {nameTF.getText(), surnameTF.getText(), peselTF.getText()};
		return values;
	}

	public void clearTextFields(){
		nameTF.setText("");
		surnameTF.setText("");
		peselTF.setText("");
		//userIdTF.setText("");
	}
}
