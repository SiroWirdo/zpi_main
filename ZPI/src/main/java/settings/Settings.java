package settings;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import xml.reader.XMLReader;
import model.Dispatcher;

public class Settings {

	public final static String GOOGLE_API_KEY = "AIzaSyCwdqY0myu5bBafDf3r7WyhU_THfd_Q3lI";
	public final static String DEFAULT_CITY = "Wroc³aw";

	public static Dispatcher USER_OBJECT;
	
	/*
	 * Ustawienie wartoœci pó³ dotycz¹cych odœwie¿ania poszczególnych paneli
	 */
	public static final long STATISTIC_PANEL_REFRESH_TIME = 10000;
	public static final long MAP_REFRESH_TIME = 10000;
	
	/*
	 * Ustawienie wartoœci pól dla danych statusów kierowcy
	 */
	public static final int FREE_CAR_STATUS = 0;
	public static final int DRIVER_CAR_STATUS = 1;
	public static final int PAUSE_CAR_STATUS = 2;
	public static final int BLOCK_CAR_STATUS = 3;
	public static final int UNAVALAIBLE_CAR_STATUS = 4;

	/*
	 * Ustawienie wartoœci pól dla danych statusów zamówienia
	 */
	public static final int WAITING_ORDER_STATUS = 0;
	public static final int IN_PROGRESS_ORDER_STATUS = 1;
	public static final int DONE_ORDER_STATUS = 2;
	public static final int CANCEL_ORDER_STATUS = 3;
	public static final int ACCEPTED_ORDER_STATUS = 4;

	/*
	 * Domyœlne wielkoœci pól
	 */
	public static final int TEXT_FIELD_WIDTH = 125;
	public static final int TEXT_FIELD_HEIGHT = 30;
	public static final int BUTTON_WIDTH = 125;
	public static final int BUTTON_HEIGHT = 25;

	/*
	 * Inicjalizacja tablic przydatnych do wypisywania statusów
	 */
	public static String[] driverStatus = { "wolny", "kurs", "przerwa",
			"zablokowany", "niedostêpny" };
	public static String[] orderStatus = { "oczekuj¹ce",
			"w trakcie realizacji", "zrealizowane", "anulowane",
			"zaakceptowane" };

	private Settings() {

	}

	public static String changeEncoding(String text) {
		if (text != null) {
			byte[] pick = text.getBytes();
			try {
				text = new String(pick, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			text = "";
		}
		return text;
	}

	public static Color getColor(String name, String kind) {
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

	/*
	 * Metoda wy³¹czaj¹ca wypisywanie logów w konsoli
	 */
	public static void turnOffLogBackLogger() {
		Logger orgHibernateLogger = (Logger) LoggerFactory.getLogger("ROOT");
		ch.qos.logback.classic.Level oldLogLevel = orgHibernateLogger
				.getLevel();
		orgHibernateLogger.setLevel(Level.OFF);
	}

	/*
	 * Metoda wy³¹czaj¹ca wypisywanie sysoutów w konsoli
	 */
	public static void turnOffSysoutLog() {
		System.setOut(new PrintStream(new OutputStream() {
			@Override
			public void write(int arg0) throws IOException {
			}
		}));
	}

}
