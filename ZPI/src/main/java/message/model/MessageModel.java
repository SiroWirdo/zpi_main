package message.model;

import java.util.ArrayList;
import java.util.List;

import model.Driver;

import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParsePush;
import org.parse4j.ParseQuery;

public class MessageModel {

	public void initialize(){

	}

	public void sendMessage(String message){
		//Nie wiem czy trzeba tu doda inicjalizacj bazy
		ParsePush push = new ParsePush();
		//ArrayList<String> channels = new ArrayList<String>();
		//sprawdzić jakie potrzebne są do listy rzeczy czy: ["",id] czy [id] czy inaczej
		ParseQuery<Driver> query = ParseQuery.getQuery("Driver");
		List<Driver> drivers;
		/*try {
			drivers = query.find();
			ArrayList<String> channels = new ArrayList<String>();
			for(Driver driver : drivers){
				ParseObject pointer = (ParseObject) driver.getParseObject("userId");
				String userId = (String)pointer.getObjectId();
				String channel = "user_" + userId;
				push.setOrderMessage(message);
		        push.setChannel(channel);
				channels.add(channel);
				push.sendInBackground(message, channels);
			}

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		ArrayList<String> channels = new ArrayList<String>();
		//String channel = "";
		push.setMessageToEveryone(message);
        //push.setChannel(channel);
		//channels.add(channel);
		push.sendInBackground(message, channels);

	}
}
