package ordersdisplay.view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
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
	private JLabel jlStatus;
	private JCheckBox waiting;
	private JCheckBox realizing;
	private JCheckBox realized;
	private JCheckBox cancelled;
	private JCheckBox accepted;
	private JButton filtr;
	private JLabel jlSurname;
	private JTextField tfSurname;


	public OrdersView(OrdersController ordersController, OrdersModel ordersModel){
		this.ordersController = ordersController;
		this.ordersModel = ordersModel;

	}

	public void initialize(){
		setLayout(null);


		String[] columns = {"Id", "Nazwisko", "Status", "Adres odbioru", "Adres docelowy", "Koszt", "Uwagi", "<html><body>Liczba<p>pasażerów", "Telefon kier."};
		tableModel = new DefaultTableModel(0, 0){

			   @Override
			   public boolean isCellEditable(int row, int column) {
			       return false;
			   }
			};
		tableModel.setColumnIdentifiers(columns);		
		
		table = new JTable();
		table.setAutoResizeMode( JTable.AUTO_RESIZE_ALL_COLUMNS );

		sorter = new TableRowSorter<DefaultTableModel>(tableModel);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 100, 1300, 510);
		for (int i = 0; i < (table.getColumnCount()); i++) {
            table.getColumn(i).setPreferredWidth(500);
        }
		table.setModel(tableModel);
		
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setPreferredSize(new Dimension(100,50));

		table.setRowSelectionAllowed(false);
		table.setColumnSelectionAllowed(false);
		table.setCellSelectionEnabled(false);
		table.setFillsViewportHeight(true);
		table.setVisible(true);
		
		table.getColumn("Id").setMinWidth(0);
		table.getColumn("Id").setMaxWidth(0);
		table.getColumn("Adres odbioru").setMinWidth(330);
		table.getColumn("Adres docelowy").setMinWidth(300);
		table.getColumn("Koszt").setMinWidth(50);
		table.getColumn("Uwagi").setMinWidth(200);
		table.getColumn("<html><body>Liczba<p>pasażerów").setMinWidth(80);
		table.getColumn("Status").setMinWidth(100);
		table.getColumn("Nazwisko").setMinWidth(100);
		table.getColumn("Telefon kier.").setMinWidth(200);


		
		
		this.add(scrollPane);

		jlStatus = new JLabel("Status:");
		jlStatus.setBounds(10, 5, 100, 20);
		add(jlStatus);

		waiting = new JCheckBox(Settings.orderStatus[0]);
		waiting.setBounds(10, 30, 100, 30);
		waiting.setSelected(true);
		add(waiting);

		realizing = new JCheckBox(Settings.orderStatus[1]);
		realizing.setBounds(10, 60, 140, 30);
		realizing.setSelected(true);
		add(realizing);

		realized = new JCheckBox(Settings.orderStatus[2]);
		realized.setBounds(170, 30, 100, 30);
		realized.setSelected(true);
		add(realized);

		cancelled = new JCheckBox(Settings.orderStatus[3]);
		cancelled.setBounds(170, 60, 100, 30);
		cancelled.setSelected(true);
		add(cancelled);
		
		accepted = new JCheckBox(Settings.orderStatus[4]);
		accepted.setBounds(300, 30, 130, 30);
		accepted.setSelected(true);
		add(accepted);

		filtr = new JButton("Filtruj");
		filtr.setBounds(550, 65, Settings.BUTTON_WIDTH, Settings.BUTTON_HEIGHT);
		filtr.addActionListener(ordersController.getFiltrListener());
		add(filtr);
		
		jlSurname = new JLabel("Nazwisko:");
		jlSurname.setBounds(300, 60, 60, 30);
		add(jlSurname);

		tfSurname = new JTextField();
		tfSurname.setBounds(370, 65, Settings.TEXT_FIELD_WIDTH, Settings.TEXT_FIELD_HEIGHT);
		add(tfSurname);

		
	/*	
	   	String [] lol = {"lol", "lol2"};
		JComboBox combo = new JComboBox(lol);
		combo.setBounds(630, 65, Settings.BUTTON_WIDTH, Settings.BUTTON_HEIGHT);
		add(combo);
	*/
	}

	public int getRowById(String id) throws OrderNotFoundException{
		boolean found = false;
		Integer row = 0;
		while(!found){
			if(tableModel.getValueAt(row, 0).toString().equals(id)){
				found = true;
			}else{
				row++;
				if(row >= tableModel.getRowCount()){
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
		
		if(status.equals(Settings.orderStatus[4])){
			return accepted.isSelected();
		}

		return true;
	}

	public void clearTable(){
		tableModel.setRowCount(0);
	}
	
	public String getSurname(){
		return tfSurname.getText();
	}

	public void setFilters(RowFilter<DefaultTableModel, Object> filter){
		sorter.setRowFilter(filter);
		table.setRowSorter(sorter);
	}
}
