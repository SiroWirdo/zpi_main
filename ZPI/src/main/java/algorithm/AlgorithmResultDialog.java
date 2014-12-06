package algorithm;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;

import model.Driver;

public class AlgorithmResultDialog extends JDialog {

	private JLabel timeLabel;
	private JLabel driverLabel;
	private final Algorithm alg;
	private boolean isDispatcherResponse;
	
	public AlgorithmResultDialog(final Algorithm alg) {
 
		this.alg = alg;
		isDispatcherResponse = false;
		getContentPane().setLayout(null);
		setTitle("Poprawnie dodano zamówienie!");
		JLabel expectedTimeLabel = new JLabel("Szacowany czas dojazdu:");
		expectedTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		expectedTimeLabel.setBounds(23, 11, 154, 34);
		getContentPane().add(expectedTimeLabel);

		timeLabel = new JLabel("Nieznany");
		timeLabel.setBounds(187, 10, 100, 30);
		timeLabel.setFont(new Font(timeLabel.getFont().getFontName(),
				Font.BOLD, 20));
		getContentPane().add(timeLabel);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				alg.setIsGiveUp(false);
				isDispatcherResponse = true;
				closeDialog();
			}
		});
		btnOk.setBounds(197, 92, 131, 30);
		getContentPane().add(btnOk);
		
		JButton giveUpOrderBtn = new JButton("Rezygnacja");
		giveUpOrderBtn.setBounds(23, 92, 131, 30);
		giveUpOrderBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				alg.setIsGiveUp(true);
				isDispatcherResponse = true;
				closeDialog();		
			}
		});
		getContentPane().add(giveUpOrderBtn);
		
		JLabel sendOrderLabel = new JLabel("Wys\u0142a\u0107 zlecenie do:");
		sendOrderLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sendOrderLabel.setBounds(23, 51, 131, 14);
		getContentPane().add(sendOrderLabel);
		
		driverLabel = new JLabel("kierowca?");
		driverLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		driverLabel.setBounds(187, 42, 114, 30);
		getContentPane().add(driverLabel);
		setBounds(400, 400, 374, 172);
	}
	
	
	public void setExpectingTime(long expectingTimeInMillisec){
		timeLabel.setText(convertMillisToMinutes(expectingTimeInMillisec));
	}
	
	public void setDriverLabel(Driver d){
		StringBuffer sb = new StringBuffer();
		sb.append(d.getSurname());
		sb.append(" ");
		sb.append(d.getName());
		
		driverLabel.setText(sb.toString());
	}
	
	public boolean isDispatcherResponse(){
		return isDispatcherResponse;
	}
	
//	public boolean showAndGetResponse(){
//		boolean abort = false;
//		this.setVisible(false);
//		alg.setWaitingForResponse();
//		return abort;
//	}
	
	private String convertMillisToMinutes(long millisec){
		StringBuilder sb = new StringBuilder();
		
		int minutes = (int)millisec/1000/60;
		sb.append(minutes);
		sb.append(" min.");
		
		return sb.toString();
	}
	
	private void closeDialog(){
		this.dispose();
		this.setVisible(false);
	}
}
