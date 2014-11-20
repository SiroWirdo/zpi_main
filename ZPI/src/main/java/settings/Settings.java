package settings;

import java.io.UnsupportedEncodingException;

import model.Dispatcher;

import org.parse4j.ParseUser;

public class Settings {
	
	public static Dispatcher USER_OBJECT;
	
	public static final int FREE_CAR_STATUS = 0;
	public static final int DRIVER_CAR_STATUS = 1;
	public static final int PAUSE_CAR_STATUS = 2;
	public static final int BLOCK_CAR_STATUS = 3;
	public static final int UNAVALAIBLE_CAR_STATUS = 4;
	
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
}
