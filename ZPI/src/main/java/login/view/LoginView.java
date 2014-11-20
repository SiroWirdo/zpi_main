package login.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
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

	private static final long serialVersionUID = 1L;
	UserModel userModel;
	LoginController loginController;

	private JTextField loginTextField;
	private JPasswordField passwordField;
	JButton okButton;
	JButton cancelButton;
	
	public LoginView(LoginController loginController, UserModel userModel) {
		this.loginController = loginController;
		this.userModel = userModel;
		initialize();
	}

	public void initialize(){
		try {
			 UIManager.setLookAndFeel(
			            UIManager.getSystemLookAndFeelClassName());
			 
//				UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 351, 228);
		getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 335, 191);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("login:");
		lblNewLabel.setBounds(23, 48, 41, 20);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblNewLabel_1 = new JLabel("has³o:");
		lblNewLabel_1.setBounds(21, 90, 43, 20);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));

		loginTextField = new JTextField();
		loginTextField.setText("login");
		loginTextField.setBounds(100, 39, 206, 31);
		loginTextField.setHorizontalAlignment(JTextField.CENTER);
		panel.add(loginTextField);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("");
		passwordField.setBounds(100, 81, 206, 31);
		passwordField.setText("has³o");
		passwordField.setEchoChar((char) 0);
		passwordField.setHorizontalAlignment(JTextField.CENTER);
		passwordField.addActionListener(loginController.getPasswordListener());
		/*passwordFiels.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            httpProxyPassword.setEchoChar('*');
		        } else {
		             httpProxyPassword.setEchoChar((char) 0);
		        }
		    }
		});
		*/
		panel.add(passwordField);

		okButton = new JButton("OK");
		okButton.setBounds(112, 137, 80, 23);
		okButton.addActionListener(loginController.getLoginListener());
		panel.add(okButton);
		getRootPane().setDefaultButton(okButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(226, 137, 80, 23);
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
