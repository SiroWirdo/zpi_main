package ordersdiplay.view;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import ordersdisplay.controller.OrderNotFoundException;
import ordersdisplay.controller.OrdersController;
import ordersdisplay.model.OrdersModel;
import settings.Settings;


public class OrdersView extends JPanel {
	private OrdersController ordersController;
	private OrdersModel ordersModel;
	private DefaultTableModel tableModel;
	private JTable table;
	private TableRowSorter<DefaultTableModel> sorter;
	private JButton addOrder;
	private JButton editOrder;
	private JLabel jlStatus;
	private JCheckBox waiting;
	private JCheckBox realizing;
	private JCheckBox realized;
	private JCheckBox cancelled;
	private JButton filtr;
	
	public OrdersView(OrdersController ordersController, OrdersModel ordersModel){
		this.ordersController = ordersController;
		this.ordersModel = ordersModel;

	}

	public void initialize(){
		setLayout(null);
		

		String[] columns = {"Id", "Adres odbioru", "Adres docelowy", "Koszt", "Uwagi", "Liczba pasa¿erów", "Status"};
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

		table.setRowSelectionAllowed(false);
		table.setColumnSelectionAllowed(false);
		table.setCellSelectionEnabled(false);
		table.setFillsViewportHeight(true);
		table.setVisible(true);

		this.add(scrollPane);
		
		addOrder = new JButton("Dodaj");
		addOrder.setBounds(10, 20, 100, 20);
		add(addOrder);
		
		editOrder = new JButton("Edytuj");
		editOrder.setBounds(10, 60, 100, 20);
		add(editOrder);
		
		jlStatus = new JLabel("Status:");
		jlStatus.setBounds(200, 5, 100, 20);
		add(jlStatus);
		
		waiting = new JCheckBox(Settings.orderStatus[0]);
		waiting.setBounds(200, 30, 100, 30);
		waiting.setSelected(true);
		add(waiting);
		
		realizing = new JCheckBox(Settings.orderStatus[1]);
		realizing.setBounds(200, 60, 100, 30);
		realizing.setSelected(true);
		add(realizing);
		
		realized = new JCheckBox(Settings.orderStatus[2]);
		realized.setBounds(320, 30, 100, 30);
		realized.setSelected(true);
		add(realized);
		
		cancelled = new JCheckBox(Settings.orderStatus[3]);
		cancelled.setBounds(320, 60, 100, 30);
		cancelled.setSelected(true);
		add(cancelled);
		
	/*	jlName = new JLabel("Imiê:");
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
		add(tfSurname);*/
		
		filtr = new JButton("Filtruj");
		filtr.setBounds(790, 65, 100, 20);
		filtr.addActionListener(ordersController.getFiltrListener());
		add(filtr);
		//	tableModel.addRow(new Object[]{"test", "test", "test", "test", "test", "test"});
	}
	
	public int getRowById(String id) throws OrderNotFoundException{
		boolean found = false;
		int row = 0;

		while(!found){
			if(tableModel.getValueAt(row, 4).toString().equals(id)){
				found = true;
			}else{
				row++;
				if(row > tableModel.getRowCount()){
					throw new OrderNotFoundException("Driver's not found in table");
				}
			}
		}
		return row;
	}
	
	public void addRow(Object[] row){
		tableModel.addRow(row);

	}

	public void updateRow(int row, Object[] values){
		for(int i = 0 ; i < table.getColumnCount(); i++){
			tableModel.setValueAt(values[i], row, i);
		}
	}
	
	public boolean isChecked(String status){
		if(status.equals(Settings.orderStatus[0])){
			return waiting.isSelected();
		}
		
		if(status.equals(Settings.orderStatus[1])){
			return realizing.isSelected();
		}
		
		if(status.equals(Settings.orderStatus[2])){
			return realized.isSelected();
		}
		
		if(status.equals(Settings.orderStatus[3])){
			return cancelled.isSelected();
		}
		
		return true;
	}
	
	public void clearTable(){
		tableModel.setRowCount(0);
	}
	
	public void setFilters(RowFilter<DefaultTableModel, Object> filter){
		sorter.setRowFilter(filter);
		table.setRowSorter(sorter);
	}
}
