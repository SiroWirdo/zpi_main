package algorithm;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import java.awt.Font;

import javax.swing.JButton;

import settings.Settings;
import model.Customer;
import model.Order;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NotAssignedOrderJDialog extends JDialog{
	private String notAssignedOrder = "<html><body>Żaden z kierowcównie podjął się zlecenia!";
	private Algorithm alg;
	
	private JLabel customerName;
	private JLabel phoneCustomer;
	private JLabel waitingTime;
	
	public NotAssignedOrderJDialog(Algorithm alg) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.alg = alg;
		initialize();
	}
	
	public void initialize(){
		getContentPane().setLayout(null);
		setTitle("Nieprzydzielone zlecenie");
		JLabel lblNewLabel = new JLabel(notAssignedOrder);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(20, 11, 350, 60);
		getContentPane().add(lblNewLabel);
		
		JLabel lblSzczegyZlecenia = new JLabel("Szczegóły zlecenia:");
		lblSzczegyZlecenia.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSzczegyZlecenia.setBounds(33, 72, 132, 14);
		getContentPane().add(lblSzczegyZlecenia);
		
		JLabel lblKlient = new JLabel("Klient:");
		lblKlient.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblKlient.setBounds(33, 97, 61, 14);
		getContentPane().add(lblKlient);
		
		JLabel lblTelefon = new JLabel("telefon:");
		lblTelefon.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTelefon.setBounds(33, 121, 61, 14);
		getContentPane().add(lblTelefon);
		
		JLabel lblCzasOczekiwania = new JLabel("<html><body>czas<p>oczekiwania:");
		lblCzasOczekiwania.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCzasOczekiwania.setBounds(33, 135, 72, 41);
		getContentPane().add(lblCzasOczekiwania);
		
		customerName = new JLabel();
		customerName.setBounds(125, 97, 216, 14);
		getContentPane().add(customerName);
		
		phoneCustomer = new JLabel();
		phoneCustomer.setBounds(125, 121, 206, 14);
		getContentPane().add(phoneCustomer);
		
		waitingTime = new JLabel();
		waitingTime.setBounds(125, 146, 103, 14);
		getContentPane().add(waitingTime);
		
		JButton retryBTn = new JButton("Ponów przydział");
		retryBTn.setBounds(33, 212, 132, 29);
		retryBTn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				closeDialog();
				Thread runNewAlg = new Thread() {
			        public void run() {
						Algorithm retryAlgorytm = new Algorithm(alg.getOrder(), alg.getDuration());
						retryAlgorytm.run(); 
			        }
			      };
			      runNewAlg.start();
			  
			}
		});
		getContentPane().add(retryBTn);
		
		JButton endBtn = new JButton("Zakończ");
		endBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Order o = alg.getOrder();
				o.setStatus(Settings.CANCEL_ORDER_STATUS);
				o.saveInBackground();
				closeDialog();
			}
		});
		endBtn.setBounds(226, 212, 126, 29);
		getContentPane().add(endBtn);
		
		JLabel lblPonwPrzydziaLub = new JLabel("Ponów przydział lub oddzwoń do klienta.");
		lblPonwPrzydziaLub.setBounds(32, 187, 309, 14);
		getContentPane().add(lblPonwPrzydziaLub);
		
		setFields(alg.getOrder(), alg.getDuration());
		setBounds(500, 300, 396, 291);
		setAlwaysOnTop(true);
		setVisible(true);
	}
	
	public void closeDialog(){
		this.dispose();
		this.setVisible(false);
	}
	
	public void setFields(Order o, double millisec){
		Customer customer = o.getCustomer();
		customerName.setText(customer.getSurname());
		phoneCustomer.setText(""+customer.getPhoneNumber());
		waitingTime.setText(Settings.convertMillisToMinutes((long)millisec));
	}
}
