package drivers.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import drivers.controller.DriversController;
import drivers.model.DriversModel;

public class DriversView extends JPanel {
	private DriversController driversController;
	private DriversModel driversModel;
	private JTable table;
	private DefaultTableModel tableModel;
	
	public DriversView(DriversController driversController, DriversModel driversModel){
		this.driversController = driversController;
		this.driversModel = driversModel;		
		
	}
	
	public void initialize(){		
		String[] columns = {"Imiê", "Nazwisko", "Telefon", "Licencja", "PESEL", "Status"};
		tableModel = new DefaultTableModel(0, 0);
		tableModel.setColumnIdentifiers(columns);
		table = new JTable();
		table.setModel(tableModel);
		table.setRowSelectionAllowed(false);
	    table.setColumnSelectionAllowed(false);
	    table.setCellSelectionEnabled(false);
	    table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		this.add(scrollPane);
		
		tableModel.addRow(new Object[]{"test", "test", "test", "test", "test", "test"});
	}
	
	public void addRow(Object[] row){
		tableModel.addRow(row);

	}

}
