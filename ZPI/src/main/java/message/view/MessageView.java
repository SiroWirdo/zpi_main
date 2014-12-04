package message.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.DefaultStyledDocument;

import message.controller.MessageController;
import message.model.MessageModel;
import settings.DocumentSizeFilter;
import settings.Settings;

public class MessageView extends JPanel {

	private MessageModel messageModel;
	private MessageController messageController;
	private JLabel message;
	private JTextArea messageArea;
	private JButton send;

	public MessageView(MessageController messageController, MessageModel messageModel){
		this.messageController = messageController;
		this.messageModel = messageModel;
	}

	public void initialize(){
		setLayout(null);

		message = new JLabel("Wiadomoœæ: ");
		message.setBounds(10, 10, 100, 20);
		add(message);

		DefaultStyledDocument doc = new DefaultStyledDocument();
		doc.setDocumentFilter(new DocumentSizeFilter(200));
		messageArea = new JTextArea();
		messageArea.setBounds(10, 40, 200, 185);
		messageArea.setLineWrap(true);
		messageArea.setWrapStyleWord(true);
		messageArea.setDocument(doc);
		add(messageArea);

		send = new JButton("Wyœlij");
		send.setBounds(10, 250, Settings.BUTTON_WIDTH, Settings.BUTTON_HEIGHT);
		send.addActionListener(messageController.getSendButtonListener());
		add(send);
	}

	public String getMessage(){
		return messageArea.getText();
	}

	public void clearTextArea(){
		this.messageArea.setText("");
	}

}
