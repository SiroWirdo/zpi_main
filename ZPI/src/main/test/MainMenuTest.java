import main.controller.MainMenuController;
import main.model.MapModel;


public class MainMenuTest {
	public static void main(String[] args) {
		MapModel menuModel = new MapModel();
		MainMenuController menuController = new MainMenuController(menuModel);
	}
}
