package com.game.p1;

import com.badlogic.gdx.Game;
import com.game.framework.manager.ScreenManager;
import com.game.framework.utils.L;
import com.game.p1.display.screen.BattleField;
import com.game.p1.display.screen.TitleScreen;
import com.game.p1.utils.Assets;

public class P1Main extends Game {
	
	
	public P1Main() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		Assets.getInstance().initialize();
		//GameLevel.getInstance().loadSaveFile();
		
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().addScreen( TitleScreen.class);
		ScreenManager.getInstance().addScreen(BattleField.class);
		
		
	}
	
	@Override
	public void render() {
		// TODO Auto-generated method stub
		Assets assets = Assets.getInstance();
		if(!assets.isDone()) {
			assets.update();
			L.wtf("loading..."+(assets.getProgress()*100)+"%");
			if(assets.isDone()) {
				ScreenManager.getInstance().setScreen(TitleScreen.class);
			}
		}
		
		super.render();
	}
	
	public void dispose() {
		// TODO Auto-generated method stub
		Assets.getInstance().dispose();
		super.dispose();
	}
	
}
