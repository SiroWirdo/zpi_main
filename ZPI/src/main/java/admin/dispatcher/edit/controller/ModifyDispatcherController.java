package admin.dispatcher.edit.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Dispatcher;
import admin.dispatcher.edit.model.ModifyDispatcherModel;
import admin.dispatcher.edit.view.ModifyDispatcherView;

public class ModifyDispatcherController {
	ModifyDispatcherModel modifyDispatcherModel;
	ModifyDispatcherView modifyDispatcherView;

	public ModifyDispatcherController(ModifyDispatcherModel modifyDispatcherModel, Dispatcher dispatcher){
		this.modifyDispatcherModel = modifyDispatcherModel;

		this.modifyDispatcherView = new ModifyDispatcherView(this, this.modifyDispatcherModel);

		//this.modifyDriverModel.initialize();
		this.modifyDispatcherView.initialize();

		this.modifyDispatcherView.setValues(dispatcher);
	}

	public CancelButtonListener getCancelButtonListener(){
		return new CancelButtonListener();
	}

	public EditButtonListener getEditButtonListener(){
		return new EditButtonListener();
	}


	private class CancelButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			modifyDispatcherView.dispose();
		}

	}

	private class EditButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Dodac walidacje usera i samochodu(sprawdzac czy nie jest juz do kogos przypisany
			Dispatcher dispatcher = modifyDispatcherView.getDispatcher();
			String[] values = modifyDispatcherView.getValues();

			modifyDispatcherModel.editDispatcher(dispatcher, values);
			modifyDispatcherView.dispose();
		}

	}

}
