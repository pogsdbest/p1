package com.game.p1;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.game.framework.manager.ScreenManager;
import com.game.p1.display.screen.BattleField;
import com.game.p1.display.screen.LoadingScreen;
import com.game.p1.display.screen.MainMenuScreen;
import com.game.p1.display.screen.TestScreen;
import com.game.p1.display.screen.TitleScreen;
import com.game.p1.utils.Assets;

public class P1Main extends Game {
	
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "p1";
		cfg.useGL20 = false;
		cfg.width = 800;
		cfg.height = 480;
		
		new LwjglApplication(new P1Main(), cfg);
	}
	
	public P1Main() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		Assets.getInstance().initialize();
		//GameLevel.getInstance().loadSaveFile();
		
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().addScreen( LoadingScreen.class);
		ScreenManager.getInstance().addScreen( TitleScreen.class);
		ScreenManager.getInstance().addScreen( BattleField.class);
		ScreenManager.getInstance().addScreen( MainMenuScreen.class);
		
		ScreenManager.getInstance().setScreen( LoadingScreen.class);
		
	}
	
	public void dispose() {
		// TODO Auto-generated method stub
		Assets.getInstance().dispose();
		super.dispose();
	}
	
}
