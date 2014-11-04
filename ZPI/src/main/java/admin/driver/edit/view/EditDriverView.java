package admin.driver.edit.view;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import drivers.controller.DriverNotFoundException;
import settings.Settings;
import admin.driver.edit.controller.EditDriverController;
import admin.driver.edit.model.EditDriverModel;

public class EditDriverView extends JFrame{
	private EditDriverController editDriverController;
	private EditDriverModel editDriverModel;
	private JPanel mainPanel;
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
	private JButton edit;

	public EditDriverView(EditDriverController editDriverController, EditDriverModel editDriverModel){
		this.editDriverController = editDriverController;
		this.editDriverModel = editDriverModel;

	}

	public void initialize(){
		this.setBounds(100, 100, 1280, 800);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		getContentPane().add(mainPanel);


		String[] columns = {"Imi�", "Nazwisko", "Telefon", "Licencja", "PESEL", "Status"};
		tableModel = new DefaultTableModel(0, 0);
		tableModel.setColumnIdentifiers(columns);
		table = new JTable();
		//table.setBounds(0, 0, 1100, 700);

		sorter = new TableRowSorter<DefaultTableModel>(tableModel);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 100, 1100, 600);
		for (int i = 0; i < (table.getColumnCount()); i++) {
            table.getColumn(i).setPreferredWidth(500);
        }
		//table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setModel(tableModel);
		table.setFillsViewportHeight(true);
		table.setVisible(true);

		mainPanel.add(scrollPane);

		jlStatus = new JLabel("Status:");
		jlStatus.setBounds(10, 5, 100, 20);
		mainPanel.add(jlStatus);

		free = new JCheckBox(Settings.driverStatus[0]);
		free.setBounds(10, 30, 100, 30);
		free.setSelected(true);
		mainPanel.add(free);

		course = new JCheckBox(Settings.driverStatus[1]);
		course.setBounds(10, 60, 100, 30);
		course.setSelected(true);
		mainPanel.add(course);

		pause = new JCheckBox(Settings.driverStatus[2]);
		pause.setBounds(130, 30, 100, 30);
		pause.setSelected(true);
		mainPanel.add(pause);

		blocked = new JCheckBox(Settings.driverStatus[3]);
		blocked.setBounds(130, 60, 100, 30);
		blocked.setSelected(true);
		mainPanel.add(blocked);

		unavailable = new JCheckBox(Settings.driverStatus[4]);
		unavailable.setBounds(250, 30, 100, 30);
		unavailable.setSelected(true);
		mainPanel.add(unavailable);

		jlName = new JLabel("Imi�:");
		jlName.setBounds(410, 30, 60, 30);
		mainPanel.add(jlName);

		tfName = new JTextField();
		tfName.setBounds(480, 35, 100, 20);
		mainPanel.add(tfName);

		jlSurname = new JLabel("Nazwisko:");
		jlSurname.setBounds(410, 60, 60, 30);
		mainPanel.add(jlSurname);

		tfSurname = new JTextField();
		tfSurname.setBounds(480, 65, 100, 20);
		mainPanel.add(tfSurname);

		filtr = new JButton("Filtruj");
		filtr.setBounds(600, 65, 100, 20);
		filtr.addActionListener(editDriverController.getFiltrListener());
		mainPanel.add(filtr);

		edit = new JButton("Edytuj");
		edit.setBounds(600, 40, 100, 20);
		edit.addActionListener(editDriverController.getEditButtonListener());
		mainPanel.add(edit);


		this.setVisible(true);
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
				if(row > tableModel.getRowCount()){
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

	public String getName(){
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

	public long getPeselFromSelectedRow(){
		int row = table.getSelectedRow();
		long pesel = new Long((String)table.getValueAt(row, 4));
		return pesel;
	}

	public void setFilters(RowFilter<DefaultTableModel, Object> filter){
		sorter.setRowFilter(filter);
		table.setRowSorter(sorter);
	}
}
