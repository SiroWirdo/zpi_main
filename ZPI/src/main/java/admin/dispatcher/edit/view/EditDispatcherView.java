package admin.dispatcher.edit.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import admin.dispatcher.edit.controller.EditDispatcherController;
import admin.dispatcher.edit.model.EditDispatcherModel;
import drivers.controller.DriverNotFoundException;

public class EditDispatcherView extends JFrame{
	private EditDispatcherController editDispatcherController;
	private EditDispatcherModel editDispatcherModel;
	private JPanel mainPanel;
	private JTable table;
	private DefaultTableModel tableModel;
	private JLabel jlName;
	private JLabel jlSurname;
	private JTextField tfName;
	private JTextField tfSurname;
	private TableRowSorter sorter;
	private JButton edit;

	public EditDispatcherView(EditDispatcherController editDispatcherController, EditDispatcherModel editDispatcherModel){
		this.editDispatcherController = editDispatcherController;
		this.editDispatcherModel = editDispatcherModel;

	}

	public void initialize(){
		this.setBounds(100, 100, 1280, 800);
		//przy EXIT_ON_CLOSE zabija wszystkie procesy, przy DISPOSE nie....
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		getContentPane().add(mainPanel);


		String[] columns = {"Imiê", "Nazwisko", "PESEL"};
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

		jlName = new JLabel("Imiê:");
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

		edit = new JButton("Edytuj");
		edit.setBounds(600, 40, 100, 20);
		edit.addActionListener(editDispatcherController.getEditButtonListener());
		mainPanel.add(edit);

		this.addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		        editDispatcherModel.stop();
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
					throw new DriverNotFoundException("Dispatchers not found in table");
				}
			}
		}
		return row;
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
		long pesel = (Long)table.getValueAt(row, 4);
		return pesel;
	}

}