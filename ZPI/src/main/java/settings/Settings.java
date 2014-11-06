package settings;

import java.io.UnsupportedEncodingException;

import model.Dispatcher;

import org.parse4j.ParseUser;

public class Settings {
	public static Dispatcher USER_OBJECT;
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
