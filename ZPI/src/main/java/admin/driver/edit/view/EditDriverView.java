package admin.driver.edit.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

import settings.Settings;
import admin.driver.edit.controller.EditDriverController;
import admin.driver.edit.model.EditDriverModel;
import drivers.controller.DriverNotFoundException;

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
		//przy EXIT_ON_CLOSE zabija wszystkie procesy, przy DISPOSE nie....
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Edycja kierowcy");

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		getContentPane().add(mainPanel);


		String[] columns = {"Imię", "Nazwisko", "Telefon", "Licencja", "PESEL", "Status"};
		tableModel = new DefaultTableModel(0, 0){

			   @Override
			   public boolean isCellEditable(int row, int column) {
			       return false;
			   }
			};
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

		jlName = new JLabel("Imię:");
		jlName.setBounds(410, 30, 60, 30);
		mainPanel.add(jlName);

		tfName = new JTextField();
		tfName.setBounds(480, 35, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		mainPanel.add(tfName);

		jlSurname = new JLabel("Nazwisko:");
		jlSurname.setBounds(410, 60, 60, 30);
		mainPanel.add(jlSurname);

		tfSurname = new JTextField();
		tfSurname.setBounds(480, 65, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		mainPanel.add(tfSurname);

		filtr = new JButton("Filtruj");
		filtr.setBounds(610, 65, Settings.BUTTON_WIDTH, Settings.BUTTON_HEIGHT);
		filtr.addActionListener(editDriverController.getFiltrListener());
		mainPanel.add(filtr);

		edit = new JButton("Edytuj");
		edit.setBounds(610, 40, Settings.BUTTON_WIDTH, Settings.BUTTON_HEIGHT);
		edit.addActionListener(editDriverController.getEditButtonListener());
		mainPanel.add(edit);

		this.addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		        editDriverModel.stop();
		    }
		});


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
		long pesel = -1;
		if(row >= 0){
			pesel = (Long)table.getValueAt(row, 4);
		}
		return pesel;
	}

	public void setFilters(RowFilter<DefaultTableModel, Object> filter){
		sorter.setRowFilter(filter);
		table.setRowSorter(sorter);
	}


}
