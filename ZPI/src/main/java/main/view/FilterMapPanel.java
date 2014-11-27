package main.view;

import javax.swing.JPanel;

import main.controller.FilterMapController;
import main.model.FilterMapModel;

import javax.swing.JLabel;
import javax.swing.JCheckBox;

import settings.Settings;

import java.awt.Font;
import javax.swing.JButton;

public class FilterMapPanel extends JPanel{

	private FilterMapController filterController;
	private FilterMapModel filterModel;
	
	private JCheckBox freeCheckBox;
	private JCheckBox courseCheckBox;
	private JCheckBox pausedCheckBox;
	private JCheckBox blockedCheckBox;
	private JCheckBox unavalaibleCheckBox;
	private JLabel lblKlienciWgStatusu;
	
	public FilterMapPanel(FilterMapController filterController, FilterMapModel filterModel){
		this.filterController = filterController;
		this.filterModel = filterModel;
		setLayout(null);

//		initialize();
	}
	
	public void initialize(){
		
		JLabel lblFiltruj = new JLabel("Filtruj");
		lblFiltruj.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFiltruj.setBounds(19, 25, 97, 23);
		add(lblFiltruj);
		
		JLabel lblKierowcyWgStatusu = new JLabel("Kierowcy wg. statusu:");
		lblKierowcyWgStatusu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKierowcyWgStatusu.setBounds(19, 47, 137, 29);
		add(lblKierowcyWgStatusu);
		
		freeCheckBox = new JCheckBox(Settings.driverStatus[0]);
		freeCheckBox.setName("freeCheckBox");
		freeCheckBox.setSelected(true);
		freeCheckBox.setBounds(19, 83, 97, 23);
		freeCheckBox.addItemListener(filterController.getCheckboxDriverItemListener());
		add(freeCheckBox);
		
		courseCheckBox = new JCheckBox(Settings.driverStatus[1]);
		courseCheckBox.setName("courseCheckBox");
		courseCheckBox.setSelected(true);
		courseCheckBox.setBounds(118, 83, 117, 23);
		courseCheckBox.addItemListener(filterController.getCheckboxDriverItemListener());
		add(courseCheckBox);
		
		pausedCheckBox = new JCheckBox(Settings.driverStatus[2]);
		pausedCheckBox.setName("pausedCheckBox");
		pausedCheckBox.setSelected(true);
		pausedCheckBox.setBounds(19, 109, 97, 23);
		pausedCheckBox.addItemListener(filterController.getCheckboxDriverItemListener());
		add(pausedCheckBox);
		
		blockedCheckBox = new JCheckBox(Settings.driverStatus[3]);
		blockedCheckBox.setName("blockedCheckBox");
		blockedCheckBox.setSelected(true);
		blockedCheckBox.setBounds(118, 109, 117, 23);
		blockedCheckBox.addItemListener(filterController.getCheckboxDriverItemListener());
		add(blockedCheckBox);
		
		unavalaibleCheckBox = new JCheckBox(Settings.driverStatus[4]);
		unavalaibleCheckBox.setName("unavalaibleCheckBox");
		unavalaibleCheckBox.setSelected(true);
		unavalaibleCheckBox.setBounds(19, 135, 97, 23);
		unavalaibleCheckBox.addItemListener(filterController.getCheckboxDriverItemListener());
		add(unavalaibleCheckBox);
	
		JButton filterDrivers = new JButton("Filtruj");
		filterDrivers.setName("filterDrivers");
		filterDrivers.setBounds(118, 168, 87, 29);
		filterDrivers.addActionListener(filterController.getFilterActionListener());
		add(filterDrivers);
		
		lblKlienciWgStatusu = new JLabel("Klienci wg. statusu:");
		lblKlienciWgStatusu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKlienciWgStatusu.setBounds(19, 219, 137, 29);
		add(lblKlienciWgStatusu);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("oczekuj¹ce");
		chckbxNewCheckBox.setName("waitingCheckBox");
		chckbxNewCheckBox.addItemListener(filterController.getCheckboxOrdersItemListener());
		chckbxNewCheckBox.setSelected(true);
		chckbxNewCheckBox.setBounds(19, 251, 97, 23);
		add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("zaakceptowane");
		chckbxNewCheckBox_1.setName("acceptedCheckBox");
		chckbxNewCheckBox_1.addItemListener(filterController.getCheckboxOrdersItemListener());
		chckbxNewCheckBox_1.setSelected(true);
		chckbxNewCheckBox_1.setBounds(118, 251, 117, 23);
		add(chckbxNewCheckBox_1);
		
		JButton filterCustomers = new JButton("Filtruj");
		filterCustomers.setName("filterCustomers");
		filterCustomers.setBounds(116, 281, 89, 28);
		filterCustomers.addActionListener(filterController.getFilterActionListener());
		add(filterCustomers);
		
	}
}
