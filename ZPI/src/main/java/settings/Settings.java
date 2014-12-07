package settings;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Painter;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;

import model.Dispatcher;

import org.slf4j.LoggerFactory;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import xml.reader.XMLReader;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

public class Settings {

	/*
	 * Kod polskich znaków na klwiaturze
	 */
	private static char[] polishCodeKey = {
			379, 380, 377, 378, 262, 263, 260, 261, 346, 347, 280, 281, 321, 322, 211, 243, 323, 324,
	};
	
	public static List POLISH_KEY_CODE = new ArrayList(Arrays.asList(polishCodeKey));
	public final static String GOOGLE_API_KEY = "AIzaSyCwdqY0myu5bBafDf3r7WyhU_THfd_Q3lI";
	public final static String DEFAULT_CITY = "Wrocław";

	public static Dispatcher USER_OBJECT;
	/*
	 * Ustawienie wartości dotyczących mapy
	 */
	public static final int MAX_ZOOM_MAP = 7;
	public static final int MIN_ZOOM_MAP = 1;
	public static final int DEFAULT_ZOOM = 3;
	//Default localization: Wrocław
	public static final double DEFAULT_LATITUDE = 51.107885200000000000;
	public static final double DEFAULT_LONGITUDE = 17.038537600000040000;
	public static final String CONTRIBUTORS_TEXT = "\u00a9 Open StreetMap contributors";

	
	/*
	 * Ustawienie wartości pól dotyczących odświeżania poszczególnych paneli
	 */
	public static final long STATISTIC_PANEL_REFRESH_TIME = 10000;
	public static final long MAP_REFRESH_TIME = 10000;
	
	/*
	 * Ustawienie wartosci pol dla danych status�w kierowcy
	 */
	public static final int FREE_CAR_STATUS = 0;
	public static final int DRIVER_CAR_STATUS = 1;
	public static final int PAUSE_CAR_STATUS = 2;
	public static final int BLOCK_CAR_STATUS = 3;
	public static final int UNAVALAIBLE_CAR_STATUS = 4;

	/*
	 * Ustawienie wartosci pol dla danych statusow zamowienia
	 */
	public static final int WAITING_ORDER_STATUS = 0;
	public static final int IN_PROGRESS_ORDER_STATUS = 1;
	public static final int DONE_ORDER_STATUS = 2;
	public static final int CANCEL_ORDER_STATUS = 3;
	public static final int ACCEPTED_ORDER_STATUS = 4;

	/*
	 * Domyslne wielkosci pol
	 */
	public static final int TEXT_FIELD_WIDTH = 125;
	public static final int TEXT_FIELD_HEIGHT = 30;
	public static final int BUTTON_WIDTH = 125;
	public static final int BUTTON_HEIGHT = 25;

	/*
	 * Inicjalizacja tablic przydatnych do wypisywania status�w
	 */
	public static String[] driverStatus = { "wolny", "kurs", "przerwa",
			"zablokowany", "niedost�pny" };
	public static String[] orderStatus = { "oczekujące",
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
	 * Metoda wylaczajaca wypisywanie logow w konsoli
	 */
	public static void turnOffLogBackLogger() {
		Logger orgHibernateLogger = (Logger) LoggerFactory.getLogger("ROOT");
		ch.qos.logback.classic.Level oldLogLevel = orgHibernateLogger
				.getLevel();
		orgHibernateLogger.setLevel(Level.OFF);
	}

	/*
	 * Metoda wylaczajaca wypisywanie sysoutow w konsoli
	 */
	public static void turnOffSysoutLog() {
		System.setOut(new PrintStream(new OutputStream() {
			@Override
			public void write(int arg0) throws IOException {
			}
		}));
	}
	
	/*
	 * Ustawienie domslnych stylow
	 */
	public static void setLookAndFeel(){
		
		UIManager.put("nimbusBase", new Color(0,68,102));
		UIManager.put("nimbusBlueGrey", new Color(60,145,144));
		UIManager.put("control", new Color(43,82,102));
		UIManager.put("text", new Color(255,255,255));
		UIManager.put("Table.alternateRowColor", new Color(0,68,102));
		UIManager.put("TextField.font", new Font("Font", Font.BOLD, 12));
		UIManager.put("TextField.textForeground", new Color(0,0,0));
		UIManager.put("PasswordField.foreground", new Color(0,0,0));
		UIManager.put("TextArea.foreground", new Color(0,0,0));
		UIManager.put("FormattedTextField.foreground", new Color(0,0,0));
		
		//TODO nie chca dzialac tooltipy na mapie
		Border border = BorderFactory.createLineBorder(new Color(0,0,0));    //#4c4f53
		UIManager.put("ToolTip.border", border);

		//UIManager.put("ToolTip.foregroundInactive", new Color(0,0,0));
		//UIManager.put("ToolTip.backgroundInactive", new Color(0,0,0));
		//UIManager.put("ToolTip.background", new Color(0,0,0)); //#fff7c8
		//UIManager.put("ToolTip.foreground", new Color(0,0,0));
		 Painter<Component> p = new Painter<Component>() {
		     public void paint(Graphics2D g, Component c, int width, int height) {
		         g.setColor(new Color(20,36,122));
		         //and so forth
		     }
		 };
		 
		UIManager.put("ToolTip[Enabled].backgroundPainter", p);
		
//		UIManager.getLookAndFeelDefaults().put("ToolTip.foregroundInactive", new Color(255, 255, 255));
//		UIManager.getLookAndFeelDefaults().put("backgroundInactive", new Color(255, 255, 255));
//		UIManager.getLookAndFeelDefaults().put("ToolTip.background", new Color(255, 255, 255)); //#fff7c8
//		UIManager.getLookAndFeelDefaults().put("ToolTip.foreground", new Color(255, 255, 255));
//		
//		UIManager.getLookAndFeelDefaults().put("ToolTip.foregroundInactive",new ColorUIResource(new Color(255, 255, 255)));
//		UIManager.getLookAndFeelDefaults().put("backgroundInactive", new ColorUIResource((new Color(255, 255, 255))));
//		UIManager.getLookAndFeelDefaults().put("ToolTip.background", new ColorUIResource(new Color(255, 255, 255))); //#fff7c8
//		UIManager.getLookAndFeelDefaults().put("ToolTip.foreground", new ColorUIResource(new Color(255, 255, 255)));
		
	     
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}

		UIManager.getLookAndFeelDefaults().put("Table:\"Table.cellRenderer\".background", new ColorUIResource(new Color(74,144,178)));
		UIManager.getLookAndFeelDefaults().put("Table.background", new ColorUIResource(new Color(74,144,178)));
		

	}
	
	public static String convertMillisToMinutes(long millisec){
		StringBuilder sb = new StringBuilder();
		
		int minutes = (int)millisec/1000/60;
		sb.append(minutes);
		sb.append(" min.");
		
		return sb.toString();
	}
	

}
