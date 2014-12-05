package main.model;

import java.util.List;

import model.Driver;

import org.parse4j.ParseException;
import org.parse4j.ParseQuery;

import settings.Settings;

public class StatisticModel {

	private boolean stop;
	private int[] statistic;

	private final int numberOfStatistic = 6;
	
	public StatisticModel(){	
		stop = false;
	}
	
	public int[] getStatistic(){
		return statistic;
	}
	
	public void setStop(){
		stop = true;
	}
	
	/*
	 * Aktualizuje dane statusów kierowcy co okreœlony czas
	 */

//	public void run() {
//		while(!stop){
//			System.out.println("Model: Uaktualniam statystyki");
//			synchronized(statistic){
//				statistic = updateStatistic();
//			}
//			this.notifyAll();
//			try {
//				Thread.sleep(Settings.STATISTIC_PANEL_REFRESH_TIME);
//			} catch (InterruptedException e1) {
//				e1.printStackTrace();
//			}
//		}
//	}
	
	public void updateStatistic(){
		statistic = new int[]{countAllTaxi(),
					countFreeTaxi(),
					countDriverTaxi(),
					countPauseTaxi(),
					countBlockTaxi(),
					countUnavailableTaxi()
					};
	}
	
	public int countAllTaxi(){
		int allCars = 0;
		ParseQuery<Driver> query = ParseQuery.getQuery(Driver.class);
		 try {
			allCars = query.find().size();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return allCars;
	}

	private int countTaxiByStatus(int driverStatus){
		int cars = 0;
		ParseQuery<Driver> query = ParseQuery.getQuery(Driver.class);
		query.whereEqualTo("status", driverStatus);
		 try {
			List<Driver> result = query.find();
			if(result != null){
				cars = result.size();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return cars;
	}
	
	public int countFreeTaxi(){
		return countTaxiByStatus(Settings.FREE_CAR_STATUS);
	}
	
	public int countDriverTaxi(){
		return countTaxiByStatus(Settings.DRIVER_CAR_STATUS);
	}
	
	public int countPauseTaxi(){
		return countTaxiByStatus(Settings.PAUSE_CAR_STATUS);
	}
	
	public int countBlockTaxi(){
		return countTaxiByStatus(Settings.BLOCK_CAR_STATUS);
	}
	
	public int countUnavailableTaxi(){
		return countTaxiByStatus(Settings.UNAVALAIBLE_CAR_STATUS);
	}

}
