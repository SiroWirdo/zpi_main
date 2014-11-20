package main.view;

import javax.swing.JPanel;
import javax.swing.JLabel;

import main.controller.StatisticController;
import main.model.StatisticModel;

import java.awt.Font;

public class StatisticJPanel extends JPanel{
	
	private StatisticModel statisticModel;
	private StatisticController statisticController;
	
	private JLabel allCars;
	private JLabel freeCars;
	private JLabel driverCars;
	private JLabel stopCars;
	private JLabel blockCars;
	private JLabel unavailableCars;
	
	public StatisticJPanel(StatisticController statisticController,
			StatisticModel statisticModel) {
		this.statisticModel = statisticModel;
		this.statisticController = statisticController;
		
		initialize();
		}
	
	public void initialize(){
		setLayout(null);
		
		JLabel lblTaxi = new JLabel("TAXI");
		lblTaxi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTaxi.setBounds(21, 24, 79, 24);
		add(lblTaxi);
		
		JLabel lblOgem = new JLabel("Og\u00F3\u0142em:");
		lblOgem.setBounds(31, 59, 95, 14);
		add(lblOgem);
		
		JLabel lblWolnych = new JLabel("Dostepny:");
		lblWolnych.setBounds(31, 84, 95, 14);
		add(lblWolnych);
		
		JLabel lblKurs = new JLabel("Kurs:");
		lblKurs.setBounds(31, 107, 95, 14);
		add(lblKurs);
		
		JLabel lblPrzerwa = new JLabel("Przerwa:");
		lblPrzerwa.setBounds(31, 132, 95, 14);
		add(lblPrzerwa);
		
		JLabel lblZablokowany = new JLabel("Zablokowany:");
		lblZablokowany.setBounds(31, 157, 95, 14);
		add(lblZablokowany);
		
		JLabel lblNiedostpny = new JLabel("Niedost\u0119pny:");
		lblNiedostpny.setBounds(31, 185, 95, 14);
		add(lblNiedostpny);
		
		allCars = new JLabel();
		allCars.setBounds(150, 59, 46, 14);
		add(allCars);
		
		freeCars = new JLabel();
		freeCars.setBounds(150, 84, 46, 14);
		add(freeCars);
		
		driverCars = new JLabel();
		driverCars.setBounds(150, 107, 46, 14);
		add(driverCars);
		
		stopCars = new JLabel();
		stopCars.setBounds(150, 132, 46, 14);
		add(stopCars);
		
		blockCars = new JLabel();
		blockCars.setBounds(150, 157, 46, 14);
		add(blockCars);
		
		unavailableCars = new JLabel();
		unavailableCars.setBounds(150, 185, 46, 14);
		add(unavailableCars);
	}
	
	public void setStatistic(int[] statistic){
		allCars.setText(Integer.toString(statistic[0]));
		freeCars.setText(Integer.toString(statistic[1]));
		driverCars.setText(Integer.toString(statistic[2]));
		stopCars.setText(Integer.toString(statistic[3]));
		blockCars.setText(Integer.toString(statistic[4]));
		unavailableCars.setText(Integer.toString(statistic[5]));
	}
}
