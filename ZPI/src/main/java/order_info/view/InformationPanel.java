package order_info.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import order_info.controller.OrderInfoController;
import order_info.model.OrderInfoModel;

public class InformationPanel extends JPanel {
	private OrderInfoModel infoModel;
	private OrderInfoController infoController;
	
	private List<SingleInfoPanel> infoPanels;
	
	public InformationPanel(OrderInfoController infoController, OrderInfoModel infoModel) {
		this.infoModel = infoModel;
		this.infoController = infoController;
		
		infoPanels = new ArrayList<SingleInfoPanel>();
	}
	
	public void setListInfoPanels(List<SingleInfoPanel> infoPanels){
		this.infoPanels = infoPanels;
	}
	
	public void paintAllSingleInfo(){
		for(SingleInfoPanel sip: infoPanels){
			add(sip);
		}
		repaint();
	}
}
