package admin.dispatcher.edit.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Dispatcher;
import admin.dispatcher.edit.controller.ModifyDispatcherController;
import admin.dispatcher.edit.model.ModifyDispatcherModel;

public class ModifyDispatcherView extends JFrame{
	private ModifyDispatcherController modifyDispatcherController;
	private ModifyDispatcherModel modifyDispatcherModel;
	private JLabel jlName;
	private JLabel jlSurname;
	private JLabel jlPesel;
	private JLabel jlUserId;
	private JTextField tfName;
	private JTextField tfSurname;
	private JTextField tfPesel;
	private JTextField tfUserId;
	private JButton edit;
	private JButton cancel;
	private JPanel mainPanel;
	private Dispatcher dispatcher;


	public ModifyDispatcherView(ModifyDispatcherController modifyDispatcherController, ModifyDispatcherModel modifyDispatcherModel){
		this.modifyDispatcherController = modifyDispatcherController;
		this.modifyDispatcherModel = modifyDispatcherModel;

	}

	public void initialize(){
		this.setBounds(500, 300, 300, 350);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		int x = 10;
		int y = 10;

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		this.add(mainPanel);

		jlName = new JLabel("Imiê: ");
		jlName.setBounds(x, y, 60, 20);
		mainPanel.add(jlName);

		tfName = new JTextField();
		tfName.setBounds(x + 130, y, 100, 20);
		mainPanel.add(tfName);

		jlSurname = new JLabel("Nazwisko: ");
		jlSurname.setBounds(x, y=y+30, 100, 20);
		mainPanel.add(jlSurname);

		tfSurname = new JTextField();
		tfSurname.setBounds(x + 130, y, 100, 20);
		mainPanel.add(tfSurname);

		jlPesel = new JLabel("PESEL: ");
		jlPesel.setBounds(x, y=y+30, 60, 20);
		mainPanel.add(jlPesel);

		tfPesel = new JTextField();
		tfPesel.setBounds(x+130, y, 100, 20);
		mainPanel.add(tfPesel);

		jlUserId = new JLabel("Id u¿ytkownika: ");
		jlUserId.setBounds(x, y=y+30, 120, 20);
		mainPanel.add(jlUserId);

		tfUserId = new JTextField();
		tfUserId.setBounds(x+130, y, 100, 20);
		mainPanel.add(tfUserId);

		cancel = new JButton("Anuluj");
		cancel.setBounds(x, y=y+40, 100, 20);
		cancel.addActionListener(modifyDispatcherController.getCancelButtonListener());
		mainPanel.add(cancel);

		edit = new JButton("Edytuj");
		edit.setBounds(x+130, y, 100, 20);
		edit.addActionListener(modifyDispatcherController.getEditButtonListener());
		mainPanel.add(edit);
		//TODO Dodac edycje hasla

		this.setVisible(true);
	}

	public void setValues(Dispatcher dispatcher){
		this.dispatcher = dispatcher;
		tfName.setText(dispatcher.getName());
		tfSurname.setText(dispatcher.getSurname());
		tfPesel.setText(new Long(dispatcher.getPESEL()).toString());
		
		String userId = "";
		if(dispatcher.getUser() != null){
			userId = dispatcher.getUser().getObjectId();
		}
		tfUserId.setText(userId);
		this.repaint();
	}

	public Dispatcher getDispatcher(){
		return this.dispatcher;
	}

	public String[] getValues(){
		String[] values = {tfName.getText(), tfSurname.getText(), tfPesel.getText(), tfUserId.getText()};
		return values;
	}
}
