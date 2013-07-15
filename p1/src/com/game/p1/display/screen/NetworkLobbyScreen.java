package com.game.p1.display.screen;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.game.framework.display.DisplayScreen;
import com.game.p1.utils.Config;

public class NetworkLobbyScreen extends DisplayScreen {
	
	private Group menuGroup;
	
	public NetworkLobbyScreen() {
		super(Config.SCREEN_WIDTH,Config.SCREEN_HEIGHT);
		
		
		createMenu();
		
	}

	private void createMenu() {
		menuGroup = new Group();
		
		
	}
}
