package admin.dispatcher.edit.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;

import model.Dispatcher;
import settings.Settings;
import admin.dispatcher.edit.model.EditDispatcherModel;
import admin.dispatcher.edit.model.ModifyDispatcherModel;
import admin.dispatcher.edit.view.EditDispatcherView;
import drivers.controller.DriverNotFoundException;

public class EditDispatcherController  implements Observer{
	EditDispatcherModel editDispatcherModel;
	EditDispatcherView editDispatcherView;

	public EditDispatcherController(EditDispatcherModel editDispatcherModel){
		this.editDispatcherModel = editDispatcherModel;
		this.editDispatcherView = new EditDispatcherView(this, editDispatcherModel);

		editDispatcherModel.initialize();
		editDispatcherView.initialize();
	}

	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(arg0 == editDispatcherModel){
			if(editDispatcherModel.getChanges().getFlag() == 0){
				List<Dispatcher> dispatchers = editDispatcherModel.getChanges().getDispatchers();
				for(Dispatcher dispatcher : dispatchers){

					addRow(dispatcher);
				}
			}else{
				List<Dispatcher> dispatchers = editDispatcherModel.getChanges().getDispatchers();
				for(Dispatcher driver : dispatchers){

					updateRow(driver);
				}
			}
		}
	}

	public void addRow(Dispatcher dispatcher){
		editDispatcherView.addRow(new Object[]{dispatcher.getName(), dispatcher.getSurname(), dispatcher.getPESEL()});
		editDispatcherView.repaint();
	}

	public void updateRow(Dispatcher dispatcher){
		Object[] values = new Object[]{dispatcher.getName(), dispatcher.getSurname(), dispatcher.getPESEL()};
		try{
			int row = editDispatcherView.getRowByPESEL(dispatcher.getPESEL());
			editDispatcherView.updateRow(row, values);
			editDispatcherView.repaint();
		}catch(DriverNotFoundException e){
			System.out.println(e.getMessage());
		}

	}


	public EditButtonListener getEditButtonListener(){
		return new EditButtonListener();
	}

	public FiltrListener getFiltrListener(){
		return new FiltrListener();
	}

	public RefreshListener getRefreshListener(){
		return new RefreshListener();
	}

	private class EditButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			long pesel = editDispatcherView.getPeselFromSelectedRow();
			Dispatcher dispatcher = editDispatcherModel.getDispatcherByPesel(pesel);
			if(dispatcher != null){
				ModifyDispatcherModel modifyDispatcherModel = new ModifyDispatcherModel();
				ModifyDispatcherController modifyDispatcherController = new ModifyDispatcherController(modifyDispatcherModel, dispatcher);

			}else{

			}
		}

	}

	private class FiltrListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

			ArrayList<RowFilter<DefaultTableModel, Object>> nameFilters = new ArrayList<RowFilter<DefaultTableModel, Object>>();
			RowFilter<DefaultTableModel, Object> nameFilter;

			if(editDispatcherView.getName() != ""){
				RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter(editDispatcherView.getName());
				nameFilters.add(filter);
			}

			if(editDispatcherView.getSurname() != ""){
				RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter(editDispatcherView.getSurname());
				nameFilters.add(filter);
			}

			nameFilter = RowFilter.andFilter(nameFilters);

			editDispatcherView.setFilters(nameFilter);
			editDispatcherView.repaint();

		}

	}

	private class RefreshListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			editDispatcherModel.refresh();
		}

	}

}
