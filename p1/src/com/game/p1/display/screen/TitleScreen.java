package com.game.p1.display.screen;

import com.game.framework.display.DisplayObject;
import com.game.framework.display.DisplayScreen;
import com.game.framework.display.DisplayText;
import com.game.framework.listeners.ActorDragListener;
import com.game.p1.utils.Assets;
import com.game.p1.utils.Config;

public class TitleScreen extends DisplayScreen {

	public TitleScreen() {
		super( Config.SCREEN_WIDTH,Config.SCREEN_HEIGHT );
		// TODO Auto-generated constructor stub
		Assets assets = Assets.getInstance();
		
		DisplayObject bg = new DisplayObject(assets.getTextureRegion("bg"));
		addActor(bg);
		
		DisplayText text = new DisplayText("POGS D BEST" , assets.getFont("uni_05_63"));
		text.addListener(new ActorDragListener(text, this));
		text.getFont().setScale(2);
		addActor(text);
	}

}
