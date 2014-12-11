package main.controller;

import main.model.MapModel;
import main.view.MainMenuView;

public class MainMenuController {
	MapModel menuModel;
	MainMenuView menuView;
	
	public MainMenuController(MapModel menuModel) {
		this.menuModel = menuModel;
		menuView = new MainMenuView(this, menuModel);
//		menuView.initialize();
	}

}
