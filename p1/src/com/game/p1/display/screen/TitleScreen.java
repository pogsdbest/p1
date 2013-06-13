package com.game.p1.display.screen;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.equations.Bounce;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.game.framework.display.DisplayObject;
import com.game.framework.display.DisplayScreen;
import com.game.framework.display.DisplayText;
import com.game.framework.listeners.ActorDragListener;
import com.game.framework.manager.ScreenManager;
import com.game.framework.utils.ActorTweenAccessor;
import com.game.p1.utils.Assets;
import com.game.p1.utils.Config;

public class TitleScreen extends DisplayScreen {

	public TitleScreen() {
		super( Config.SCREEN_WIDTH,Config.SCREEN_HEIGHT );
		// TODO Auto-generated constructor stub
		Assets assets = Assets.getInstance();
		
		DisplayObject bg = new DisplayObject(assets.getTextureRegion("bg"));
		addActor(bg);
		
		final DisplayText text = new DisplayText("" , assets.getFont("uni_05_63"));
		text.addListener(new ActorDragListener(text, this));
		text.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				text.setX(text.getX()+1);
				return super.touchDown(event, x, y, pointer, button);
			}
			
			
		});
		text.setPosition(100, 100);
		addActor(text);
		
	    
	    //more sample at http://www.aurelienribon.com/blog/projects/universal-tween-engine/
		Tween.to(text, ActorTweenAccessor.POSITION_XY, 0.5f)
		    .target(0, 0)
		    .ease(Bounce.OUT)
		    .delay(1.0f)
		    .repeatYoyo(2, 0.5f)
		    .start(manager);
		
		bg.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				ScreenManager.getInstance().setScreen(BattleField.class);
				//super.clicked(event, x, y);
			}
		});
	}

}
