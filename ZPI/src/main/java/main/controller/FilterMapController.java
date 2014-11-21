package main.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import settings.Settings;
import main.model.FilterMapModel;
import main.view.FilterMapPanel;

public class FilterMapController implements ItemListener {
	
	private FilterMapModel filterModel;
	private FilterMapPanel filterView;
	private MapController mapController;
	private ArrayList<Integer> queryStatusDriverArray;
	
	public FilterMapController(FilterMapModel filterModel, MapController mapController){
		this.filterModel = filterModel;
		this.mapController = mapController;
		filterView = new FilterMapPanel(this, filterModel);
		filterView.initialize();
		
		queryStatusDriverArray = new ArrayList<Integer>(Settings.driverStatus.length);
		queryStatusDriverArray.add(0);
		queryStatusDriverArray.add(1);
		queryStatusDriverArray.add(2);
		queryStatusDriverArray.add(3);
		queryStatusDriverArray.add(4);
	}
	
	public FilterMapPanel getView(){
		return filterView;
	}

	@Override
	public void itemStateChanged(ItemEvent ie) {
		JCheckBox checkbox = (JCheckBox) ie.getItem();
	    int state = ie.getStateChange();
	    String name = checkbox.getName();
	    int numberStatus = getNumberOfDriverStatusByCheckBoxName(name);
	    
	    if(numberStatus > -1 && queryStatusDriverArray.size() > 0){
	    	if (state == ItemEvent.SELECTED){
	    		queryStatusDriverArray.add(numberStatus);
	    		System.out.println("Dodano: " + numberStatus);
	    	}
	    	else{
	    		if(queryStatusDriverArray.contains(numberStatus)){
	    			queryStatusDriverArray.remove(new Integer(numberStatus));
	    			System.out.println("Usunieto: " + numberStatus);
	    		}
	    	}
	   // Collections.sort(queryStatusDriverArray);
	    mapController.setQueryDriverStatusArray(queryStatusDriverArray);
	    mapController.refreshMap();
	    }	}
	
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
