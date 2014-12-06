package order.view;

import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;

import order.controller.OrderController;
import order.model.OrderModel;

import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.DocumentFilter;
import javax.swing.text.NumberFormatter;

import com.sun.org.apache.xalan.internal.xsltc.dom.DocumentCache;

import javax.swing.JCheckBox;

import settings.DocumentSizeFilter;
import settings.Settings;

/*
 * Buduje widok dla okna dodawania ordera, zarzπdza walidacjπ pÛl
 */
public class AddOrderJPanel extends JPanel{
	
	private OrderModel orderModel;
	private OrderController orderController;
	private JTextField surnameTextField;
	private JTextField phoneNumberTextField;
	private JTextField pickUpAddressTextField;
	private JTextField passangerCountTextField;
	private JButton addOrderBtn;
	private JTextArea customerRemarksTextArea;
	private JScrollPane scrollPane;
	
	private JLabel surnameErrors;
	private JLabel phoneErrors;
	private JLabel addresErrors;
	private JLabel passangerCountErrors;
	private Border defaultBorder;
	private Border redBorder;
	private boolean isValidate = true;
	private JCheckBox defaultCityCheckBox;
	private JCheckBox cleanAfterAddCheckBox;
	
	private final int maxSizeText = 200;
	private final String requiredFieldErrorMsg = "Pole wymagane!";
	private final String onlyTextErrorMsg = "To pole moøe zawieraÊ tylko litery!";
	private final String onlyNumbersErrorMsg = "To pole moøe zawieraÊ tylko liczby!";
	private final String rangePassangerCountErrorMsg = "<html><body style='width: 150px'>"
											+ "Liczba pasaøerÛw musi byÊ liczbπ z przedzia≥u &lt;1, 4&gt;";
	
	
	/**
	 * Create the panel.
	 */
	public AddOrderJPanel(OrderController orderController, OrderModel orderModel) {
		setLayout(null);
		this.orderModel = orderModel;
		this.orderController = orderController;
		redBorder = BorderFactory.createLineBorder(Color.RED, 2);
		defaultBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
//		initialize();

	}
	
	public void initialize(){
		this.setVisible(true);
		JLabel lblNazwisko = new JLabel("Nazwisko:");
		lblNazwisko.setBounds(42, 45, 135, 14);
		add(lblNazwisko);
		
		JLabel lblNumerTelefonu = new JLabel("Nr telefonu:");
		lblNumerTelefonu.setBounds(42, 87, 135, 14);
		add(lblNumerTelefonu);
		
		JLabel lblAdresOdbioru = new JLabel("Adres odbioru:");
		lblAdresOdbioru.setBounds(42, 129, 135, 14);
		add(lblAdresOdbioru);
		
		JLabel lblLiczbaPasaerw = new JLabel("Liczba pasa\u017Cer\u00F3w:");
		lblLiczbaPasaerw.setBounds(42, 172, 135, 14);
		add(lblLiczbaPasaerw);
		
		JLabel lblUwagi = new JLabel("Uwagi:");
		lblUwagi.setBounds(42, 210, 135, 14);
		add(lblUwagi);
		
		surnameTextField = new JTextField();
		surnameTextField.setBounds(187, 42, 177, 20);
		surnameTextField.setName("surname");
		surnameTextField.addFocusListener(orderController.getValidateTextFieldListener());
		surnameTextField.addFocusListener(orderController.getTrimTextFieldListener());
		add(this.surnameTextField);
		
		phoneNumberTextField = new JTextField();
		phoneNumberTextField.setBounds(188, 84, 176, 20);
		phoneNumberTextField.setName("phone");
		add(phoneNumberTextField);
		
		pickUpAddressTextField = new JTextField();
		pickUpAddressTextField.setBounds(187, 126, 177, 20);
		pickUpAddressTextField.setName("address");
		
		//TODO autocomplete adresu!
	///	Java2sAutoComboBox autoComplete = new
		
	/*	String COMMIT_ACTION = "commit";

		// Without this, cursor always leaves text field
		pickUpAddressTextField.setFocusTraversalKeysEnabled(false);
		
		// Our words to complete
		List<String> keywords = new ArrayList<String>(5);
		        keywords.add("example");
		        keywords.add("autocomplete");
		        keywords.add("stackabuse");
		        keywords.add("java");
		AddressAutocomplete  autoComplete = new AddressAutocomplete(pickUpAddressTextField, keywords);
		pickUpAddressTextField.getDocument().addDocumentListener(autoComplete);

		// Maps the tab key to the commit action, which finishes the autocomplete
		// when given a suggestion
		pickUpAddressTextField.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT_ACTION);
		pickUpAddressTextField.getActionMap().put(COMMIT_ACTION, autoComplete.new CommitAction());
		*/
		add(pickUpAddressTextField);
		
		passangerCountTextField = new JTextField();
		passangerCountTextField.setBounds(187, 169, 177, 20);
		passangerCountTextField.setName("passangerCount");
		passangerCountTextField.addFocusListener(orderController.getTrimTextFieldListener());
		add(passangerCountTextField);
		

		customerRemarksTextArea = new JTextArea(3, 50);
		customerRemarksTextArea.setLineWrap(true);
		customerRemarksTextArea.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		customerRemarksTextArea.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
		customerRemarksTextArea.setNextFocusableComponent(addOrderBtn);
		
		DefaultStyledDocument doc = new DefaultStyledDocument();
		doc.setDocumentFilter(new DocumentSizeFilter(maxSizeText));
		customerRemarksTextArea.setDocument(doc);
		
		scrollPane = new JScrollPane(customerRemarksTextArea);
		scrollPane.setBounds(187, 210, 177, 73);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane);
		
		addOrderBtn = new JButton("Dodaj");
		addOrderBtn.addActionListener(orderController.getButtonListener());
		addOrderBtn.setBounds(244, 314, 120, 33);
		add(addOrderBtn);
		
		JButton cleanBtn = new JButton("Wyczy\u015B\u0107");
		cleanBtn.setBounds(78, 314, 120, 33);
		cleanBtn.addActionListener(orderController.getButtonListener());
		add(cleanBtn);
		
		
		/*
		 * ERRORS LABEL
		 */
		surnameErrors = new JLabel("surname error");
		surnameErrors.setName("surnameError");
		surnameErrors.setBounds(374, 45, 211, 14);
		surnameErrors.setForeground(Color.RED);
		surnameErrors.setVisible(false);
		add(surnameErrors);
		
		phoneErrors = new JLabel("phone error");
		phoneErrors.setName("phoneError");
		phoneErrors.setBounds(374, 87, 211, 14);
		phoneErrors.setForeground(Color.RED);
		phoneErrors.setVisible(false);
		add(phoneErrors);
		
		addresErrors = new JLabel("address error");
		addresErrors.setName("addressError");
		addresErrors.setBounds(484, 129, 211, 14);
		addresErrors.setForeground(Color.RED);
		addresErrors.setVisible(false);
		add(addresErrors);
		
		passangerCountErrors = new JLabel("passanger count error");
		passangerCountErrors.setName("passangerCountError");
		passangerCountErrors.setBounds(374, 159, 211, 41);
		passangerCountErrors.setForeground(Color.RED);
		passangerCountErrors.setVisible(false);
		add(passangerCountErrors);
		
		defaultCityCheckBox = new JCheckBox(Settings.DEFAULT_CITY);
		defaultCityCheckBox.setSelected(true);
		defaultCityCheckBox.setBounds(374, 125, 90, 23);
		add(defaultCityCheckBox);
		
		cleanAfterAddCheckBox = new JCheckBox("<html><body>wyczy\u015B\u0107<p>po dodaniu");
		cleanAfterAddCheckBox.setSelected(true);
		cleanAfterAddCheckBox.setBounds(375, 310, 135, 41);
		add(cleanAfterAddCheckBox);
		
		setDefaultBorderField();
//		TODO spiner
//		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, 6, 1);
//		jsCarCapacity = new JSpinner(spinnerModel);
//		jsCarCapacity.setBounds(x+160, y, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
//		add(jsCarCapacity);
//		
		this.setVisible(true);
//		surnameTextField.requestFocusInWindow();
	}
	
	/*
	 * Sprawdza i ustawia walidacje pÛl
	 */
	public boolean validateFields(){
		isValidate = true; 
		if(isEmptyField(surnameTextField)){
			setValidationError(surnameTextField, surnameErrors, requiredFieldErrorMsg);
		}
		else{
			if(!isOnlyTextField(surnameTextField)){
				setValidationError(surnameTextField, surnameErrors, onlyTextErrorMsg);
			}
		}
		
		if(isEmptyField(phoneNumberTextField)){
			setValidationError(phoneNumberTextField, phoneErrors, requiredFieldErrorMsg);
		}
		else{
			if(!isOnlyNumberField(phoneNumberTextField)){
				setValidationError(phoneNumberTextField, phoneErrors, onlyNumbersErrorMsg);
			}
		}
		
		if(isEmptyField(pickUpAddressTextField)){
			setValidationError(pickUpAddressTextField, addresErrors, requiredFieldErrorMsg); 
		}

		if(isEmptyField(passangerCountTextField)){
			setValidationError(passangerCountTextField, passangerCountErrors, requiredFieldErrorMsg);
		}
		else{
			if(isOnlyNumberField(passangerCountTextField)){
				int number = Integer.parseInt(passangerCountTextField.getText());
				if(number < 1 || number > 5){
					setValidationError(passangerCountTextField, passangerCountErrors, rangePassangerCountErrorMsg);
				}
			}
			else{
				setValidationError(passangerCountTextField, passangerCountErrors, onlyNumbersErrorMsg);
			}
		}
		return isValidate;
	}
	
	public boolean isEmptyField(JTextField validateField){
		return validateField.getText() == null || validateField.getText().equals("");
	}
	
	public boolean isOnlyTextField(JTextField validateField){
		return validateField.getText().matches("[A-ZØè∆•å £”—][a-zøüÊÒÛ≥Íπú]*[\\w-]*");
	}
	
	public boolean isOnlyNumberField(JTextField validateField){
		return validateField.getText().matches("^[0-9]+$");
	}
	
	/*
	 * Ustawia labelke errora, podúwietla pole, ktÛre nie przesz≥o walidacji
	 */
	public void setValidationError(JTextField validateField, JLabel errorLabel, String errorMessage){
		errorLabel.setText(errorMessage);
		validateField.setBorder(redBorder);
		errorLabel.setVisible(true);
		isValidate = false; 
		//this.repaint();
	}
	
	public void cleanAll(){
		cleanFields();
		cleanAllErrors();
	}
	
	public void cleanFields(){
		surnameTextField.setText(null);
		phoneNumberTextField.setText(null);
		pickUpAddressTextField.setText(null);
		passangerCountTextField.setText(null);
		customerRemarksTextArea.setText(null);
	}
	
	public void cleanAllErrors(){
		setDefaultBorderField();
		hideAllErrorsLabel();
	}
	
	public void setDefaultBorderField(){
		surnameTextField.setBorder(defaultBorder);
		phoneNumberTextField.setBorder(defaultBorder);
		pickUpAddressTextField.setBorder(defaultBorder);
		passangerCountTextField.setBorder(defaultBorder);
		scrollPane.setBorder(defaultBorder);
	}
	
	public void hideAllErrorsLabel(){
		surnameErrors.setVisible(false);
		phoneErrors.setVisible(false);
		addresErrors.setVisible(false);
		passangerCountErrors.setVisible(false);
	}
	
	public JTextField getSurnameTextField() {
		return surnameTextField;
	}

	public void setSurnameTextField(JTextField surnameTextField) {
		this.surnameTextField = surnameTextField;
	}

	public JTextField getPhoneNumberTextField() {
		return phoneNumberTextField;
	}

	public void setPhoneNumberTextField(JTextField phoneNumberTextField) {
		this.phoneNumberTextField = phoneNumberTextField;
	}

	public JTextField getPickUpAddressTextField() {
		return pickUpAddressTextField;
	}

	public void setPickUpAddressTextField(JTextField pickUpAddressTextField) {
		this.pickUpAddressTextField = pickUpAddressTextField;
	}

	public JTextField getPassangerCountTextField() {
		return passangerCountTextField;
	}

	public void setPassangerCountTextField(
			JFormattedTextField passangerCountTextField) {
		this.passangerCountTextField = passangerCountTextField;
	}

	public JTextArea getCustomerRemarksTextArea() {
		return customerRemarksTextArea;
	}

	public void setCustomerRemarksTextArea(JTextArea customerRemarksTextArea) {
		this.customerRemarksTextArea = customerRemarksTextArea;
	}
	
	public boolean isFormValidate() {
		return isValidate;
	}
	
	public boolean isDefaultCityChecked(){
		return defaultCityCheckBox.isSelected();
	}
	
	public boolean isCleanAfterAddChecked(){
		return cleanAfterAddCheckBox.isSelected();
	}
}
