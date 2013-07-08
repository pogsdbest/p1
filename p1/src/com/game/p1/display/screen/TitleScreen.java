package com.game.p1.display.screen;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.equations.Bounce;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.game.framework.display.DisplayObject;
import com.game.framework.display.DisplayScreen;
import com.game.framework.display.DisplayText;
import com.game.framework.utils.ActorTweenAccessor;
import com.game.framework.utils.L;
import com.game.p1.utils.Assets;
import com.game.p1.utils.Config;

public class TitleScreen extends DisplayScreen {

	public TitleScreen() {
		super( Config.SCREEN_WIDTH,Config.SCREEN_HEIGHT );
		// TODO Auto-generated constructor stub
		Assets assets = Assets.getInstance();
		TextureAtlas atlas = assets.get("gfx/assets.pack");
		
		TextureRegion bgTexture = atlas.findRegion("bg");
		DisplayObject bg = new DisplayObject(bgTexture);
		addActor(bg);
		
		BitmapFont font = assets.get("data/fonts/uni05_64.fnt");
		DisplayText title = new DisplayText("PROJECT P1" , font);
		//title.addListener(new ActorDragListener(title, this));
		title.setPosition((width - title.getWidth())/2, height);
		addActor(title);
		title.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				L.wtf("AAAAAAAAAAAA");
				super.clicked(event, x, y);
			}
		});
		
	    
	    //more sample at http://www.aurelienribon.com/blog/projects/universal-tween-engine/
		Tween.to(title, ActorTweenAccessor.POSITION_XY, 0.5f)
		    .target(309,253)
		    .ease(Bounce.OUT)
		    .delay(.5f)
		    .start(manager);
		
		bg.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				//ScreenManager.getInstance().setScreen(TestScreen.class);
				//super.clicked(event, x, y);
			}
		});
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector2 point = stageToScreenCoordinates(new Vector2(screenX,screenY));
		L.wtf(point.x);
		L.wtf(point.y);
		return super.touchDown(screenX, screenY, pointer, button);
	}
}
