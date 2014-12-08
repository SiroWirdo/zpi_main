package order.view;

import geocoding.AddressInfo;
import geocoding.ConverterGeoPosition;

import java.awt.Color;
import java.awt.KeyboardFocusManager;

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
import javax.swing.text.DefaultStyledDocument;
import javax.swing.JCheckBox;

import org.parse4j.ParseGeoPoint;

import settings.DocumentSizeFilter;
import settings.Settings;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

/*
 * Buduje widok dla okna dodawania ordera, zarządza walidacją pól
 */
public class AddOrderJPanel extends JPanel{
	
	private OrderModel orderModel;
	private OrderController orderController;
	private JTextField surnameTextField;
	private JTextField phoneNumberTextField;
	private JTextField pickUpAddressTextField;
	private JSpinner passangerCountSpinner;
	private JButton addOrderBtn;
	private JTextArea customerRemarksTextArea;
	private JScrollPane scrollPane;
	
	private String pickUpAddress;
	private AddressInfo addressInfo;
	private ParseGeoPoint pickUpAddressGeoPoint;
	
	private JLabel surnameErrors;
	private JLabel phoneErrors;
	private JLabel addresErrors;
	private JLabel passangerCountErrors;
	private Border defaultBorder;
	private Border redBorder;
	private boolean isValidate = true;
	private JCheckBox defaultCityCheckBox;
	private JCheckBox cleanAfterAddCheckBox;
	
	private final int maxSizeText = 65;
	private final String requiredFieldErrorMsg = "Pole wymagane!";
	private final String onlyTextErrorMsg = "To pole może zawierać tylko litery!";
	private final String onlyNumbersErrorMsg = "To pole może zawierać tylko liczby!";
	private final String rangePassangerCountErrorMsg = "<html><body style='width: 150px'>"
											+ "Liczba pasażerów musi być liczbą z przedziału &lt;1, 4&gt;";
	private final String addressNotExistsErrorMsg = "Podany adres nie istnieje!";
	
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
		lblNazwisko.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNazwisko.setBounds(10, 26, 101, 14);
		add(lblNazwisko);
		
		JLabel lblNumerTelefonu = new JLabel("Nr telefonu:");
		lblNumerTelefonu.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNumerTelefonu.setBounds(10, 73, 101, 14);
		add(lblNumerTelefonu);
		
		JLabel lblAdresOdbioru = new JLabel("Adres odbioru:");
		lblAdresOdbioru.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAdresOdbioru.setBounds(10, 119, 101, 14);
		add(lblAdresOdbioru);
		
		JLabel lblLiczbaPasaerw = new JLabel("Liczba pasa\u017Cer\u00F3w:");
		lblLiczbaPasaerw.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLiczbaPasaerw.setBounds(10, 165, 135, 14);
		add(lblLiczbaPasaerw);
		
		JLabel lblUwagi = new JLabel("Uwagi:");
		lblUwagi.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUwagi.setBounds(10, 211, 101, 14);
		add(lblUwagi);
		
		surnameTextField = new JTextField();
		surnameTextField.setBounds(10, 42, 177, 20);
		surnameTextField.setName("surname");
		surnameTextField.addFocusListener(orderController.getValidateTextFieldListener());
		surnameTextField.addFocusListener(orderController.getTrimTextFieldListener());
		add(this.surnameTextField);
		
		phoneNumberTextField = new JTextField();
		phoneNumberTextField.setBounds(10, 88, 176, 20);
		phoneNumberTextField.setName("phone");
		DefaultStyledDocument phoneDoc = new DefaultStyledDocument();
		phoneDoc.setDocumentFilter(new DocumentSizeFilter(13));
		phoneNumberTextField.setDocument(phoneDoc);
		add(phoneNumberTextField);
		
		pickUpAddressTextField = new JTextField();
		pickUpAddressTextField.setBounds(10, 135, 177, 20);
		pickUpAddressTextField.setName("address");
		
		//TODO autocomplete adresu!
/*		
		String COMMIT_ACTION = "commit";

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
		

		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, 4, 1);
		passangerCountSpinner = new JSpinner(spinnerModel);
		passangerCountSpinner.setBounds(10, 180, 177, 23);
		passangerCountSpinner.setName("passangerCount");
		JFormattedTextField ampmspin=((JSpinner.DefaultEditor)passangerCountSpinner.getEditor()).getTextField();
		ampmspin.setEditable(false); 
//		passangerCountTextField.addFocusListener(orderController.getTrimTextFieldListener());
		add(passangerCountSpinner);
		
		DefaultStyledDocument doc = new DefaultStyledDocument();
		doc.setDocumentFilter(new DocumentSizeFilter(maxSizeText));
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 226, 177, 49);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane);
		
		customerRemarksTextArea = new JTextArea(3, 50);
		scrollPane.setViewportView(customerRemarksTextArea);
		customerRemarksTextArea.setLineWrap(true);
		customerRemarksTextArea.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		customerRemarksTextArea.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
		customerRemarksTextArea.setDocument(doc);
		
		addOrderBtn = new JButton("Dodaj");
		addOrderBtn.addActionListener(orderController.getButtonListener());
		addOrderBtn.setBounds(104, 296, 83, 33);
		add(addOrderBtn);
		
		JButton cleanBtn = new JButton("Wyczy\u015B\u0107");
		cleanBtn.setBounds(10, 296, 83, 33);
		cleanBtn.addActionListener(orderController.getButtonListener());
		add(cleanBtn);
		
		/*
		 * ERRORS LABEL
		 */
		surnameErrors = new JLabel("surname error");
		surnameErrors.setName("surnameError");
		surnameErrors.setBounds(197, 45, 274, 14);
		surnameErrors.setForeground(Color.RED);
		surnameErrors.setVisible(false);
		add(surnameErrors);
		
		phoneErrors = new JLabel("phone error");
		phoneErrors.setName("phoneError");
		phoneErrors.setBounds(196, 91, 293, 14);
		phoneErrors.setForeground(Color.RED);
		phoneErrors.setVisible(false);
		add(phoneErrors);
		
		addresErrors = new JLabel("address error");
		addresErrors.setName("addressError");
		addresErrors.setBounds(271, 138, 211, 14);
		addresErrors.setForeground(Color.RED);
		addresErrors.setVisible(false);
		add(addresErrors);
		
		passangerCountErrors = new JLabel("passanger count error");
		passangerCountErrors.setName("passangerCountError");
		passangerCountErrors.setBounds(197, 170, 274, 41);
		passangerCountErrors.setForeground(Color.RED);
		passangerCountErrors.setVisible(false);
		add(passangerCountErrors);
		
		defaultCityCheckBox = new JCheckBox(Settings.DEFAULT_CITY);
		defaultCityCheckBox.setSelected(true);
		defaultCityCheckBox.setBounds(193, 134, 72, 23);
		add(defaultCityCheckBox);
		
		cleanAfterAddCheckBox = new JCheckBox("<html><body>wyczy\u015B\u0107<p>po dodaniu");
		cleanAfterAddCheckBox.setSelected(true);
		cleanAfterAddCheckBox.setBounds(193, 292, 135, 41);
		add(cleanAfterAddCheckBox);
		
		setDefaultBorderField();
		this.setVisible(true);
	}
	
	public String getPickUpAddress(){
		return pickUpAddress;
	}
	
	public ParseGeoPoint getPickUpAddressGeoPoint(){
		return pickUpAddressGeoPoint;
	}
	
	public boolean isAddressExists(){
		boolean isExists = false;
		
		String address = pickUpAddressTextField.getText();
		if(defaultCityCheckBox.isSelected()){
			address += " " + Settings.DEFAULT_CITY;
		}
		
		addressInfo = ConverterGeoPosition.addressToAdressInfo(address);
		
		int i = 1;
		if(addressInfo != null){
			if(!isDefaultCity()){
				pickUpAddressGeoPoint = ConverterGeoPosition.addressInfoToParseGeoPoint(addressInfo);
				pickUpAddress = addressInfo.getFullAddress();
				isExists = true;
			}
		}
		return isExists;
	}
	
	public boolean isDefaultCity(){
		return addressInfo.getLatitude() == Settings.DEFAULT_LATITUDE && addressInfo.getLongtitude() == Settings.DEFAULT_LONGITUDE;
	}
	/*
	 * Sprawdza i ustawia walidacje pól
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
		
		else if(!isAddressExists()){
			setValidationError(pickUpAddressTextField, addresErrors, addressNotExistsErrorMsg); 
		}

		return isValidate;
	}
	
	public boolean isEmptyField(JTextField validateField){
		return validateField.getText() == null || validateField.getText().equals("");
	}
	
	public boolean isOnlyTextField(JTextField validateField){
		return validateField.getText().matches("[A-ZŻŹĆĄŚĘŁÓŃ][a-zżźćńółęąś]*[\\w-]*");
	}
	
	public boolean isOnlyNumberField(JTextField validateField){
		return validateField.getText().matches("^[0-9]+$");
	}
	
	/*
	 * Ustawia labelke errora, podświetla pole, które nie przeszło walidacji
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
		phoneNumberTextField.setText("");
		pickUpAddressTextField.setText(null);
		passangerCountSpinner.setValue(1);
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
		passangerCountSpinner.setBorder(defaultBorder);
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

	public JSpinner getPassangerCountTextField() {
		return passangerCountSpinner;
	}

	public void setPassangerCountTextField(
			JSpinner passangerCountSpinner) {
		this.passangerCountSpinner = passangerCountSpinner;
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
