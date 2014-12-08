package algorithm;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class NoAvailableDrivers extends JDialog{
	public NoAvailableDrivers() {
		getContentPane().setLayout(null);
		
		JLabel lblNieMaAktualnie = new JLabel("Nie ma aktualnie wolnych kierowców!");
		lblNieMaAktualnie.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNieMaAktualnie.setBounds(34, 24, 242, 36);
		getContentPane().add(lblNieMaAktualnie);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				closeDialog();		
			}
		});
		
		btnOk.setBounds(110, 74, 89, 23);
		getContentPane().add(btnOk);
		setBounds(1000, 400, 323, 160);
		setAlwaysOnTop(true);
	}
	
	public void closeDialog(){
		this.dispose();
		this.setVisible(false);
	}

}
