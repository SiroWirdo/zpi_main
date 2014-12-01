package settings;

import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import xml.reader.XMLReader;
import model.Dispatcher;

public class Settings {

	public static Dispatcher USER_OBJECT;
	public static final int FREE_CAR_STATUS = 0;
	public static final int DRIVER_CAR_STATUS = 1;
	public static final int PAUSE_CAR_STATUS = 2;
	public static final int BLOCK_CAR_STATUS = 3;
	public static final int UNAVALAIBLE_CAR_STATUS = 4;
	public static final int TEXT_FIELD_WIDTH = 125;
	public static final int TEXT_FIELD_HEIGHT = 30;
	public static final int BUTTON_WIDTH = 125;
	public static final int BUTTON_HEIGHT = 25;


	public static String[] driverStatus = {"wolny", "kurs", "przerwa", "zablokowany", "niedostêpny"};
	public static String[] orderStatus = {"oczekuj¹ce", "w trakcie realizacji", "zrealizowane", "anulowane", "zaakceptowane"};

	private Settings(){

	}

	public static String changeEncoding(String text){
		if(text != null){
			byte[] pick = text.getBytes();
			try {
				text = new String(pick, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			text = "";
		}
		return text;
	}

	public static Color getColor(String name, String kind){
		XMLReader reader = new XMLReader();
		String color = reader.readString(name, kind);
		Map<String, Color> map = new HashMap<String, Color>();
		map.put("blue", Color.blue);
		map.put("red", Color.red);
		map.put("green", Color.green);
		map.put("yellow", Color.yellow);
		map.put("black", Color.black);
		map.put("white", Color.white);

		return map.get(color);

	}


}
