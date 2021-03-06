package drivers.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import settings.Settings;
import xml.reader.XMLReader;
import drivers.controller.DriverNotFoundException;
import drivers.controller.DriversController;
import drivers.model.DriversModel;

public class DriversView extends JPanel {
	private DriversController driversController;
	private DriversModel driversModel;
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton filtr;
	private JCheckBox free;
	private JCheckBox course;
	private JCheckBox pause;
	private JCheckBox blocked;
	private JCheckBox unavailable;
	private JLabel jlName;
	private JLabel jlSurname;
	private JLabel jlStatus;
	private JTextField tfName;
	private JTextField tfSurname;
	private TableRowSorter sorter;
	private JButton block;
	private JButton unblock;
	
	public DriversView(DriversController driversController, DriversModel driversModel){
		this.driversController = driversController;
		this.driversModel = driversModel;

	}

	public void initialize(){
		setLayout(null);

		System.out.println("wielkosc: " + this.getSize().getWidth());
		String[] columns = {"Imię", "Nazwisko", "Telefon", "Licencja", "PESEL", "Status"};
		tableModel = new DefaultTableModel(0, 0){

			   @Override
			   public boolean isCellEditable(int row, int column) {
			       return false;
			   }
			};
		tableModel.setColumnIdentifiers(columns);
		table = new JTable();

		sorter = new TableRowSorter<DefaultTableModel>(tableModel);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 100, 1300, 510);
		for (int i = 0; i < (table.getColumnCount()); i++) {
            table.getColumn(i).setPreferredWidth(500);
        }
		table.setModel(tableModel);
		
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		table.setCellSelectionEnabled(false);
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(false);
		
		table.setVisible(true);

		this.add(scrollPane);

		jlStatus = new JLabel("Status:");
		jlStatus.setBounds(10, 5, 100, 20);
		add(jlStatus);

		free = new JCheckBox(Settings.driverStatus[0]);
		free.setBounds(10, 30, 100, 30);
		free.setSelected(true);
		//free.setBackground(backgroundColor);
		add(free);

		course = new JCheckBox(Settings.driverStatus[1]);
		course.setBounds(10, 60, 100, 30);
		course.setSelected(true);
		//course.setBackground(backgroundColor);
		add(course);

		pause = new JCheckBox(Settings.driverStatus[2]);
		pause.setBounds(130, 30, 100, 30);
		pause.setSelected(true);
		//pause.setBackground(backgroundColor);
		add(pause);

		blocked = new JCheckBox(Settings.driverStatus[3]);
		blocked.setBounds(130, 60, 100, 30);
		blocked.setSelected(true);
		//blocked.setBackground(backgroundColor);
		add(blocked);

		unavailable = new JCheckBox(Settings.driverStatus[4]);
		unavailable.setBounds(250, 30, 100, 30);
		unavailable.setSelected(true);
		//unavailable.setBackground(backgroundColor);
		add(unavailable);

		jlName = new JLabel("Imię:");
		jlName.setBounds(410, 30, 60, 30);
		add(jlName);

		tfName = new JTextField();
		tfName.setBounds(480, 35, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		add(tfName);

		jlSurname = new JLabel("Nazwisko:");
		jlSurname.setBounds(410, 60, 60, 30);
		add(jlSurname);

		tfSurname = new JTextField();
		tfSurname.setBounds(480, 65, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		add(tfSurname);

		filtr = new JButton("Filtruj");
		filtr.setBounds(625, 65, Settings.BUTTON_WIDTH, Settings.BUTTON_HEIGHT);
		filtr.addActionListener(driversController.getFiltrListener());
		add(filtr);
		
		unblock = new JButton("Odblokuj");
		unblock.setBounds(1250-Settings.BUTTON_WIDTH, 65, Settings.BUTTON_WIDTH, Settings.BUTTON_HEIGHT);
		unblock.addActionListener(driversController.getUnblockListener());
		add(unblock);
		
		block = new JButton("Zablokuj");
		block.setBounds(1250-Settings.BUTTON_WIDTH, 30, Settings.BUTTON_WIDTH, Settings.BUTTON_HEIGHT);
		block.addActionListener(driversController.getBlockListener());
		add(block);
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

	public int getRowByPESEL(long pesel) throws DriverNotFoundException{
		boolean found = false;
		int row = 0;

		while(!found){
			if(new Long(tableModel.getValueAt(row, 4).toString()) == pesel){
				found = true;
			}else{
				row++;
				if(row >= tableModel.getRowCount()){
					throw new DriverNotFoundException("Driver's not found in table");
				}
			}
		}
		return row;
	}

	public boolean isChecked(String status){
		if(status.equals(Settings.driverStatus[0])){
			return free.isSelected();
		}

		if(status.equals(Settings.driverStatus[1])){
			return course.isSelected();
		}

		if(status.equals(Settings.driverStatus[2])){
			return pause.isSelected();
		}

		if(status.equals(Settings.driverStatus[3])){
			return blocked.isSelected();
		}

		if(status.equals(Settings.driverStatus[4])){
			return unavailable.isSelected();
		}

		return true;
	}

	public String getDriverName(){
		System.out.println(tfName.getText());
		return tfName.getText();
	}

	public String getSurname(){
		System.out.println(tfSurname.getText());
		return tfSurname.getText();
	}

	public void clearTable(){
		tableModel.setRowCount(0);
	}

	public void setFilters(RowFilter<DefaultTableModel, Object> filter){
		sorter.setRowFilter(filter);
		table.setRowSorter(sorter);
	}
	
	public long getPeselFromSelectedRow(){
		int row = table.getSelectedRow();
		long pesel = -1;
		if(row >= 0){
			pesel = (Long)table.getValueAt(row, 4);
		}
		return pesel;
	}
	
	public String getStatusFromSelectedRow(){
		int row = table.getSelectedRow();
		String status = "";
		if(row >= 0){
			status = (String)table.getValueAt(row, 5);
		}
		return status;		
	}
}
