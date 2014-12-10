package algorithm;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CarCapacityJDialogError extends JDialog {
	
	public CarCapacityJDialogError() {
		getContentPane().setLayout(null);
		setTitle("Brak miejsc");
		JLabel lblNieMaKierowcy = new JLabel("Nie ma kierowcy z autem o takiej liczbie miejsc.");
		lblNieMaKierowcy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNieMaKierowcy.setBounds(23, 11, 284, 53);
		getContentPane().add(lblNieMaKierowcy);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				closeDialog();		
			}
		});
		
		btnOk.setBounds(111, 60, 89, 23);
		getContentPane().add(btnOk);
		setBounds(600, 500, 337, 145);

	}
	
	public void closeDialog(){
		this.dispose();
		this.setVisible(false);
	}

}
