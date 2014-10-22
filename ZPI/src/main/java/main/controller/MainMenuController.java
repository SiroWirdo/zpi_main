package main.controller;

import main.model.MainMenuModel;
import main.view.MainMenuView;

public class MainMenuController {
	MainMenuModel menuModel;
	MainMenuView menuView;
	
	public MainMenuController(MainMenuModel menuModel) {
		this.menuModel = menuModel;
		menuView = new MainMenuView(this, menuModel);
		menuView.initialize();
		menuView.addTabbedPane();
		//menuView.addTab(,null, ,null);
		//menuView.addOrderPanel();
		//menuView.addDriverPanel();
	}

}
