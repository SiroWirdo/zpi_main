package drivers.view;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import drivers.controller.DriverNotFoundException;
import drivers.controller.DriversController;
import drivers.model.DriversModel;

public class DriversView extends JPanel {
	private DriversController driversController;
	private DriversModel driversModel;
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton editDriver;
	private JButton addDriver;
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

	public DriversView(DriversController driversController, DriversModel driversModel){
		this.driversController = driversController;
		this.driversModel = driversModel;

	}

	public void initialize(){
		setLayout(null);
		

		String[] columns = {"Imi�", "Nazwisko", "Telefon", "Licencja", "PESEL", "Status"};
		tableModel = new DefaultTableModel(0, 0);
		tableModel.setColumnIdentifiers(columns);
		table = new JTable();
		//table.setBounds(0, 0, 1100, 700);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 100, 1100, 600);
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
		
		addDriver = new JButton("Dodaj");
		addDriver.setBounds(10, 20, 100, 20);
		add(addDriver);
		
		editDriver = new JButton("Edytuj");
		editDriver.setBounds(10, 60, 100, 20);
		add(editDriver);
		
		jlStatus = new JLabel("Status:");
		jlStatus.setBounds(200, 5, 100, 20);
		add(jlStatus);
		
		free = new JCheckBox("wolny");
		free.setBounds(200, 30, 100, 30);
		free.setSelected(true);
		add(free);
		
		course = new JCheckBox("kurs");
		course.setBounds(200, 60, 100, 30);
		course.setSelected(true);
		add(course);
		
		pause = new JCheckBox("przerwa");
		pause.setBounds(320, 30, 100, 30);
		pause.setSelected(true);
		add(pause);
		
		blocked = new JCheckBox("zablokowany");
		blocked.setBounds(320, 60, 100, 30);
		blocked.setSelected(true);
		add(blocked);
		
		unavailable = new JCheckBox("niedost�pny");
		unavailable.setBounds(440, 30, 100, 30);
		unavailable.setSelected(true);
		add(unavailable);
		
		jlName = new JLabel("Imi�:");
		jlName.setBounds(600, 30, 60, 30);
		add(jlName);
		
		tfName = new JTextField();
		tfName.setBounds(670, 35, 100, 20);
		add(tfName);
		
		jlSurname = new JLabel("Nazwisko:");
		jlSurname.setBounds(600, 60, 60, 30);
		add(jlSurname);
		
		tfSurname = new JTextField();
		tfSurname.setBounds(670, 65, 100, 20);
		add(tfSurname);
		
		filtr = new JButton("Filtruj");
		filtr.setBounds(790, 65, 100, 20);
		filtr.addActionListener(driversController.getFiltrListener());
		add(filtr);
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
				if(row > tableModel.getRowCount()){
					throw new DriverNotFoundException("Driver's not found in table");
				}
			}
		}
		return row;
	}
	
	public boolean isChecked(String status){
		if(status.equals("wolny")){
			return free.isSelected();
		}
		
		if(status.equals("kurs")){
			return course.isSelected();
		}
		
		if(status.equals("przerwa")){
			return pause.isSelected();
		}
		
		if(status.equals("zablokowany")){
			return blocked.isSelected();
		}
		
		if(status.equals("niedost�pny")){
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
}
