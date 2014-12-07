package main.controller;

import javax.swing.SwingUtilities;

import settings.Settings;
import main.model.StatisticModel;
import main.view.StatisticJPanel;

/*
 * Odpowiada za zarządzanie panelem statystyk kierowców
 */
public class StatisticController {

	private StatisticModel statisticModel;
	private StatisticJPanel statisticView;

	public StatisticController(StatisticModel statisticModel) {
		this.statisticModel = statisticModel;
		statisticView = new StatisticJPanel(this, statisticModel);
		statisticView.initialize();
		updateStatistic();
		refreshStatistic(Settings.STATISTIC_PANEL_REFRESH_TIME);
	}

	public StatisticJPanel getView() {
		return statisticView;
	}

	public void refreshStatistic(final long millis) {
		Thread refreshThread = new Thread() {
			public void run() {
				while (true) {
					System.out.println("Map Kontroller: Uaktualniam statystyki");
					updateStatistic();
					try {
						Thread.sleep(millis);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		refreshThread.start();
	}
	
	public void updateStatistic() {
		statisticModel.updateStatistic();
		updateView();
	}

	public void updateView() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				statisticView.setStatistic(statisticModel.getStatistic());
			}
		});
	}

}
