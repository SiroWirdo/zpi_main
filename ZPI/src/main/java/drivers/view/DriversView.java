package drivers.view;

import java.awt.Dimension;

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
		setLayout(null);

		String[] columns = {"Imiê", "Nazwisko", "Telefon", "Licencja", "PESEL", "Status"};
		tableModel = new DefaultTableModel(0, 0);
		tableModel.setColumnIdentifiers(columns);
		table = new JTable();
		//table.setBounds(0, 0, 1100, 700);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 1100, 700);
		for (int i = 0; i < (table.getColumnCount()); i++) {
            table.getColumn(i).setPreferredWidth(500);
        }
		//table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setModel(tableModel);

		table.setRowSelectionAllowed(false);
		table.setColumnSelectionAllowed(false);
		table.setCellSelectionEnabled(false);
		table.setFillsViewportHeight(true);
		table.setVisible(true);

		this.add(scrollPane);


		//	tableModel.addRow(new Object[]{"test", "test", "test", "test", "test", "test"});
	}

	public void addRow(Object[] row){
		tableModel.addRow(row);

	}

	public void updateRow(int row, Object[] values){
		for(int i = 0 ; i < table.getColumnCount(); i++){
			tableModel.setValueAt(values[i], row, i);
		}
	}

	public int getRowByPESEL(long pesel){
		boolean found = false;
		int row = 0;

		while(!found){
			if(new Long(tableModel.getValueAt(row, 4).toString()) == pesel){
				found = true;
			}else{
				row++;
			}
		}
		return row;
	}

}
