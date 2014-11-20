package main.view;

import javax.swing.JPanel;

import main.controller.FilterMapController;
import main.model.FilterMapModel;

import javax.swing.JLabel;
import javax.swing.JCheckBox;

import settings.Settings;

import java.awt.Font;

public class FilterMapPanel extends JPanel{

	private FilterMapController filterController;
	private FilterMapModel filterModel;
	
	private JCheckBox freeCheckBox;
	private JCheckBox courseCheckBox;
	private JCheckBox pausedCheckBox;
	private JCheckBox blockedCheckBox;
	private JCheckBox unavalaibleCheckBox;
	
	public FilterMapPanel(FilterMapController filterController, FilterMapModel filterModel){
		this.filterController = filterController;
		this.filterModel = filterModel;
		setLayout(null);

		initialize();
	}
	
	public void initialize(){
		JLabel lblFiltruj = new JLabel("Filtruj");
		lblFiltruj.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFiltruj.setBounds(19, 25, 97, 23);
		add(lblFiltruj);
		
		freeCheckBox = new JCheckBox(Settings.driverStatus[0]);
		freeCheckBox.setName("freeCheckBox");
		freeCheckBox.setSelected(true);
		freeCheckBox.setBounds(19, 54, 97, 23);
		freeCheckBox.addItemListener(filterController);
		add(freeCheckBox);
		
		courseCheckBox = new JCheckBox(Settings.driverStatus[1]);
		courseCheckBox.setName("courseCheckBox");
		courseCheckBox.setSelected(true);
		courseCheckBox.setBounds(118, 54, 117, 23);
		courseCheckBox.addItemListener(filterController);
		add(courseCheckBox);
		
		pausedCheckBox = new JCheckBox(Settings.driverStatus[2]);
		pausedCheckBox.setName("pausedCheckBox");
		pausedCheckBox.setSelected(true);
		pausedCheckBox.setBounds(19, 80, 97, 23);
		pausedCheckBox.addItemListener(filterController);
		add(pausedCheckBox);
		
		blockedCheckBox = new JCheckBox(Settings.driverStatus[3]);
		blockedCheckBox.setName("blockedCheckBox");
		blockedCheckBox.setSelected(true);
		blockedCheckBox.setBounds(118, 80, 117, 23);
		blockedCheckBox.addItemListener(filterController);
		add(blockedCheckBox);
		
		unavalaibleCheckBox = new JCheckBox(Settings.driverStatus[4]);
		unavalaibleCheckBox.setName("unavalaibleCheckBox");
		unavalaibleCheckBox.setSelected(true);
		unavalaibleCheckBox.setBounds(19, 106, 97, 23);
		unavalaibleCheckBox.addItemListener(filterController);
		add(unavalaibleCheckBox);
		
	}

}
