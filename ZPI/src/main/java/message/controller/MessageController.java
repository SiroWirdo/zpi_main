package message.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import message.model.MessageModel;
import message.view.MessageView;

public class MessageController {
	private MessageModel messageModel;
	private MessageView messageView;
	
	public MessageController(MessageModel messageModel){
		this.messageModel = messageModel;
		this.messageView = new MessageView(this, messageModel);
		
		messageView.initialize();
		messageModel.initialize();
	}
	
	public MessageView getMessageView(){
		return this.messageView;
	}
	
	public SendButtonListener getSendButtonListener(){
		return new SendButtonListener();
	}
	
	private class SendButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String message = messageView.getMessage();
			
			messageModel.sendMessage(message);
		}
		
	}
}
