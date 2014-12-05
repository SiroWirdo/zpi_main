
import settings.Settings;
import main.controller.MainMenuController;
import main.model.MapModel;

public class MainMenuTest {
	public static void main(String[] args) {
		//TODO na koñcu wy³¹czyæ
		Settings.setLookAndFeel();
		MapModel menuModel = new MapModel();
		MainMenuController menuController = new MainMenuController(menuModel);
	}
}
