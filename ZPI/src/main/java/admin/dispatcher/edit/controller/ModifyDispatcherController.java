package admin.dispatcher.edit.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Dispatcher;

import org.parse4j.ParseException;
import org.parse4j.ParseUser;

import validation.DriverDispatcherValidation;
import admin.dispatcher.edit.model.ModifyDispatcherModel;
import admin.dispatcher.edit.view.ModifyDispatcherView;

public class ModifyDispatcherController {
	ModifyDispatcherModel modifyDispatcherModel;
	ModifyDispatcherView modifyDispatcherView;
	Dispatcher dispatcherOld;

	public ModifyDispatcherController(ModifyDispatcherModel modifyDispatcherModel, Dispatcher dispatcher){
		this.modifyDispatcherModel = modifyDispatcherModel;

		this.modifyDispatcherView = new ModifyDispatcherView(this, this.modifyDispatcherModel);

		//this.modifyDriverModel.initialize();
		this.modifyDispatcherView.initialize();

		this.modifyDispatcherView.setValues(dispatcher);
		this.dispatcherOld = dispatcher;
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
	
	public RestartPasswordButtonListener getRestartPasswordButtonListener(){
		return new RestartPasswordButtonListener();
	}
	
	private class RestartPasswordButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String[] values = modifyDispatcherView.getValues();
			try {
				ParseUser.requestPasswordReset(values[3]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	private class EditButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Dodac walidacje usera
			Dispatcher dispatcher = modifyDispatcherView.getDispatcher();
			String[] values = modifyDispatcherView.getValues();
			boolean valid = true;
			for(String value : values){
				if(value.equals("")){
					valid = false;
				}
			}

			boolean validPesel = true;
			if(values[2].length() != 11){
				validPesel = false;
			}

			boolean uniquePesel = DriverDispatcherValidation.isPeselUnique(values[2]);

			if(values[2].equals(new Long(dispatcherOld.getPESEL()).toString())){
				uniquePesel = true;
			}

			if(valid && validPesel && uniquePesel){
				modifyDispatcherModel.editDispatcher(dispatcher, values);
				modifyDispatcherView.dispose();
			}else{
				if(!uniquePesel){
					printError("Taki PESEL jest ju� przypisany do innego kierowcy");
				}
				if(!validPesel){
					printError("PESEL powinien mie� 11 znak�w");
				}

				if(!valid){
					printError("Wprowad� wszystkie dane");

				}
			}
		}

	}

	public void printError(String text){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JOptionPane.showMessageDialog(frame, text);
	}

}
