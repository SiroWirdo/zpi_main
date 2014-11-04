package admin.user.add.view;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import admin.user.add.controller.AddUserController;
import admin.user.add.model.AddUserModel;


public class AddUserView extends JFrame{
	AddUserController addUserController;
	AddUserModel addUserModel;
	JPanel mainPanel;
	JLabel userName;
	JLabel password;
	JLabel confirmPassword;
	JLabel email;
	JTextField userNameTF;
	JPasswordField passwordTF;
	JPasswordField confirmPasswordTF;
	JTextField emailTF;
	JButton addUser;
	JButton cancel;
	JCheckBox admin;

	public AddUserView(AddUserController addUserController, AddUserModel addUserModel){
		this.addUserController = addUserController;
		this.addUserModel = addUserModel;
	}

	public void initialize(){
		this.setBounds(300, 200, 310, 260);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		add(mainPanel);

		int x = 10;
		int y = 10;

		userName = new JLabel("Login: ");
		userName.setBounds(x, y, 120, 20);
		mainPanel.add(userName);

		userNameTF = new JTextField();
		userNameTF.setBounds(x+130, y, 150, 20);
		mainPanel.add(userNameTF);

		password = new JLabel("Has≥o: ");
		password.setBounds(x, y = y+30, 120, 20);
		mainPanel.add(password);

		passwordTF = new JPasswordField();
		passwordTF.setBounds(x+130, y, 150, 20);
		mainPanel.add(passwordTF);

		confirmPassword = new JLabel("Potwierdü has≥o: ");
		confirmPassword.setBounds(x, y=y+30, 120, 20);
		mainPanel.add(confirmPassword);

		confirmPasswordTF = new JPasswordField();
		confirmPasswordTF.setBounds(x+130, y, 150, 20);
		mainPanel.add(confirmPasswordTF);

		email = new JLabel("E-mail: ");
		email.setBounds(x, y=y+30, 120, 20);
		mainPanel.add(email);

		emailTF = new JTextField();
		emailTF.setBounds(x + 130, y, 150, 20);
		mainPanel.add(emailTF);

		admin = new JCheckBox("Admin");
		admin.setBounds(x, y=y+30, 100, 20);
		mainPanel.add(admin);

		addUser = new JButton("Dodaj");
		addUser.setBounds(x + 170, y = y+50, 100, 30);
		addUser.addActionListener(addUserController.getAddButtonListener());
		mainPanel.add(addUser);

		cancel = new JButton("Anuluj");
		cancel.setBounds(x, y, 100, 30);
		cancel.addActionListener(addUserController.getCancelButtonListener());
		mainPanel.add(cancel);



		this.setVisible(true);
	}

	public boolean isAdminSelected(){
		return admin.isSelected();
	}

	public String[] getValues(){
		String[] values = {userNameTF.getText(), new String(passwordTF.getPassword()), new String(confirmPasswordTF.getPassword()), emailTF.getText()};
		return values;
	}

	public void clearTextFields(){
		userNameTF.setText("");
		passwordTF.setText("");
		confirmPasswordTF.setText("");
		emailTF.setText("");
	}
}
