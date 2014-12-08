package login.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import login.controller.LoginController;
import login.model.UserModel;

public class LoginView extends JFrame {

	private static final String LOGO_ICON_PATH = "src/main/resources/taxi_waypoint_logo.png";
	private static final long serialVersionUID = 1L;
	private UserModel userModel;
	private LoginController loginController;

	private JTextField loginTextField;
	private JPasswordField passwordField;
	private JButton okButton;
	private JButton cancelButton;
	private JPanel logo_icon;
	
	public LoginView(LoginController loginController, UserModel userModel) {
		this.loginController = loginController;
		this.userModel = userModel;
//		initialize();
	}

	public void initialize(){
		this.setTitle("Logowanie");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 354, 252);
		getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 335, 346);
		getContentPane().add(panel);
		panel.setLayout(null);

		loginTextField = new JTextField();
		loginTextField.setName("login");
		loginTextField.setFont(new Font("Verdana", Font.PLAIN, 14));
		loginTextField.setText("login");
		loginTextField.setBounds(56, 44, 218, 31);
		loginTextField.setHorizontalAlignment(JTextField.CENTER);
		loginTextField.setForeground(Color.GRAY);
		loginTextField.addFocusListener(loginController.getTextFieldFocusListener());
		panel.add(loginTextField);
		
		passwordField = new JPasswordField();
		passwordField.setName("haslo");
		passwordField.setFont(new Font("Verdana", Font.PLAIN, 14));
		passwordField.setBounds(56, 86, 216, 31);
		passwordField.setText("hasło");
		passwordField.setEchoChar((char) 0);
		passwordField.setHorizontalAlignment(JTextField.CENTER);
		passwordField.setForeground(Color.GRAY);
		passwordField.addFocusListener(loginController.getTextFieldFocusListener());
		panel.add(passwordField);

		okButton = new JButton("Zaloguj");
		okButton.setBounds(180, 146, 94, 31);
		okButton.addActionListener(loginController.getLoginListener());
		panel.add(okButton);
		
		cancelButton = new JButton("Anuluj");
		cancelButton.setBounds(56, 146, 94, 31);
		cancelButton.addActionListener(loginController.getCancelListener());
		panel.add(cancelButton);

		this.setVisible(true);

		System.out.println(okButton.getFont().getFontName());
	}

	public void clearPassword(){
		passwordField.setText("");
	}

	public char[] getPassword(){
		return passwordField.getPassword();
	}

	public String getLogin(){
		return loginTextField.getText();
	}
}
