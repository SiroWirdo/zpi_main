import main.controller.MainMenuController;
import main.model.MainMenuModel;


public class MainMenuTest {
	public static void main(String[] args) {
		MainMenuModel menuModel = new MainMenuModel();
		MainMenuController menuController = new MainMenuController(menuModel);
	}
}
