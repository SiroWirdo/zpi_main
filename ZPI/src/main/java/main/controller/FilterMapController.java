package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import settings.Settings;
import main.model.FilterMapModel;
import main.view.FilterMapPanel;

public class FilterMapController {
	
	private FilterMapModel filterModel;
	private FilterMapPanel filterView;
	private MapController mapController;
	private ArrayList<Integer> queryStatusDriverArray;
	private ArrayList<Integer> queryStatusOrderArray;
	
	public FilterMapController(FilterMapModel filterModel, MapController mapController){
		this.filterModel = filterModel;
		this.mapController = mapController;
		filterView = new FilterMapPanel(this, filterModel);
		
		queryStatusDriverArray = new ArrayList<Integer>(Settings.driverStatus.length);
		queryStatusDriverArray.add(0);
		queryStatusDriverArray.add(1);
		queryStatusDriverArray.add(2);
		queryStatusDriverArray.add(3);
		queryStatusDriverArray.add(4);
		
		queryStatusOrderArray = new ArrayList<Integer>(2);
		
		filterView.initialize();
	}
	
	public FilterMapPanel getView(){
		return filterView;
	}
	
	public CheckboxOrdersItemListener getCheckboxOrdersItemListener(){
		return new CheckboxOrdersItemListener();
	}
	
	public CheckboxDriverItemListener getCheckboxDriverItemListener(){
		return new CheckboxDriverItemListener();
	}
	
	public FilterActionListener getFilterActionListener(){
		return new FilterActionListener();
	}

	private class CheckboxDriverItemListener implements ItemListener{
		
		@Override
		public void itemStateChanged(ItemEvent ie) {
		JCheckBox checkbox = (JCheckBox) ie.getItem();
	    	int state = ie.getStateChange();
	    	String name = checkbox.getName();
	    	int numberStatus = getNumberOfDriverStatusByCheckBoxName(name);
	    
	    	if(numberStatus > -1){
	    		if (state == ItemEvent.SELECTED && queryStatusDriverArray!= null){
	    			queryStatusDriverArray.add(numberStatus);
	    			System.out.println("Dodano: " + numberStatus);
	    		}
	    		else{
	    			if(queryStatusDriverArray.contains(numberStatus)  && queryStatusDriverArray.size() > 0){
	    				queryStatusDriverArray.remove(new Integer(numberStatus));
	    				System.out.println("Usunieto: " + numberStatus);
	    			}
	    		}
	    	}		}
		
		public int getNumberOfDriverStatusByCheckBoxName(String name){
			int statusNumber = -1;
			switch (name) {
			case "freeCheckBox":
				statusNumber = 0;
				break;
			case "courseCheckBox":
				statusNumber = 1;
				break;
			case "pausedCheckBox":
				statusNumber = 2;
				break;
			case "blockedCheckBox":
				statusNumber = 3;
				break;
			case "unavalaibleCheckBox":
				statusNumber = 4;
				break;
			default:
				break;
			}
			return statusNumber;
		}
	}
	
	private class CheckboxOrdersItemListener implements ItemListener{
		
		@Override
		public void itemStateChanged(ItemEvent ie) {
		JCheckBox checkbox = (JCheckBox) ie.getItem();
	    	int state = ie.getStateChange();
	    	String name = checkbox.getName();
	    	int numberStatus = getNumberOfOrderStatusByCheckBoxName(name);
	    
	    	if(numberStatus > -1){
	    		if (state == ItemEvent.SELECTED && queryStatusOrderArray != null){
	    			queryStatusOrderArray.add(numberStatus);
	    			System.out.println("Dodano order : " + numberStatus);
	    		}
	    		else if(state == ItemEvent.DESELECTED){
	    			if(queryStatusOrderArray.contains(numberStatus)  && queryStatusOrderArray.size() > 0){
	    				queryStatusOrderArray.remove(new Integer(numberStatus));
	    				System.out.println("Usunieto order: " + numberStatus);
	    			}
	    		}
	    	}
		}
		
		public int getNumberOfOrderStatusByCheckBoxName(String name){
			int statusNumber = -1;
			switch (name) {
			case "waitingCheckBox":
				statusNumber = 0;
				break;
			case "acceptedCheckBox":
				statusNumber = 4;
				break;
			default:
				break;
			}
			return statusNumber;
		}
	}
	private class FilterActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(((JButton)e.getSource()).getName().equals("filterDrivers")){
				Thread filerThread = new Thread() {
			        public void run() {
						mapController.setQueryDriverStatusArray(queryStatusDriverArray);
						mapController.refreshMap();
			        }
			      };
			      filerThread.start();

			}
			else if(((JButton)e.getSource()).getName().equals("filterCustomers")){
				Thread filterThread = new Thread() {
			        public void run() {
						mapController.setQueryOrderStatusArray(queryStatusOrderArray);
						mapController.refreshMap();
			        }
			      };
			      filterThread.start();
			}
		}
	}
}
