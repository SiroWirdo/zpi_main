package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Timer;

import main.model.StatisticModel;
import main.view.MapComponent;
import main.view.StatisticJPanel;

public class StatisticController {
	
	private StatisticModel statisticModel;
	private StatisticJPanel statisticView;
	
	ActionListener taskPerformer;
	
	public StatisticController(StatisticModel statisticModel) {
		this.statisticModel = statisticModel;
		statisticView = new StatisticJPanel(this, statisticModel);
		statisticView.initialize();
		setStatistic();
		refreshStat();
	}

	public StatisticJPanel getView(){
		return statisticView;
	}
	
	public void setStatistic(){
		int[] stat = {
				statisticModel.countAllTaxi(),
				statisticModel.countFreeTaxi(),
				statisticModel.countDriverTaxi(),
				statisticModel.countPauseTaxi(),
				statisticModel.countBlockTaxi(),
				statisticModel.countUnavailableTaxi()
				};
		
		statisticView.setStatistic(stat);
	}
	
	public void refreshStat(){
		int delay = 10000; //milliseconds
		  taskPerformer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setStatistic();
			}
		  };
		  new Timer(delay, taskPerformer).start();
	}
}
