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
			modifyDispatcherView.dispose();
		}

	}
	
	public RestartPasswordButtonListener getRestartPasswordButtonListener(){
		return new RestartPasswordButtonListener();
	}
	
	private class RestartPasswordButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String[] values = modifyDispatcherView.getValues();
			try {
				ParseUser.requestPasswordReset(values[3]);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	private class EditButtonListener implements ActionListener{
		private String error = "";
		@Override
		public void actionPerformed(ActionEvent e) {
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
			
			boolean uniquePesel = true;

			if(validPesel){
				uniquePesel = DriverDispatcherValidation.isPeselUnique(values[2]);
			}
			
			if(values[2].equals(new Long(dispatcherOld.getPESEL()).toString())){
				uniquePesel = true;
			}

			if(valid && validPesel && uniquePesel){
				modifyDispatcherModel.editDispatcher(dispatcher, values);
				modifyDispatcherView.dispose();
			}else{
				if(!uniquePesel){
					error += "- Taki PESEL jest ju¿ przypisany do innego kierowcy \n";
				}
				if(!validPesel){
					error += "- PESEL powinien mieæ 11 znaków \n";
				}

				if(!valid){
					error += "- WprowadŸ wszystkie dane \n";

				}
				printError(error);
				error = "";
			}
		}

	}

	public void printError(String text){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JOptionPane.showMessageDialog(frame, text);
	}

}
