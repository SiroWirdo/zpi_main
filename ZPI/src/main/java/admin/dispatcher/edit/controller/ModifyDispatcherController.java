package admin.dispatcher.edit.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import validation.DriverDispatcherValidation;
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
			// TODO Dodac walidacje usera
			// TODO poprawiæ sprawdzanie danych(jeœli nie zosta³y zmienione)
			Dispatcher dispatcher = modifyDispatcherView.getDispatcher();
			String[] values = modifyDispatcherView.getValues();
			boolean valid = true;
			for(String value : values){
				if(value == ""){
					valid = false;
				}
			}

			boolean validPesel = true;
			if(values[2].length() != 11){
				validPesel = false;
			}

			boolean uniquePesel = DriverDispatcherValidation.isPeselUnique(values[2]);

			if(valid && validPesel && uniquePesel){
				modifyDispatcherModel.editDispatcher(dispatcher, values);
				modifyDispatcherView.dispose();
			}else{
				if(!uniquePesel){
					printError("Taki PESEL jest ju¿ przypisany do innego kierowcy");
				}
				if(!validPesel){
					printError("PESEL powinien mieæ 11 znaków");
				}

				if(!valid){
					printError("WprowadŸ wszystkie dane");

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
