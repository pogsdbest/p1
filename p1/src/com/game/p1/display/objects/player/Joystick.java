package com.game.p1.display.objects.player;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.game.framework.display.DisplayObject;
import com.game.p1.display.screen.TestScreen;
import com.game.p1.listeners.JoystickListener;
import com.game.p1.utils.Assets;
import com.game.p1.utils.Config;

public class Joystick {
	private Group josystickGroup;
	Assets asset;
	TextureAtlas atlas;
	private DisplayObject downBtn, leftBtn, upBtn, rightBtn;
	private TextureRegion down, left, up, right;
	public final static int MOVE_RIGHT = 0;
	public final static int MOVE_LEFT = 1;
	public final static int MOVE_UP = 2;
	public final static int MOVE_DOWN = 3;
	
	public Joystick(TestScreen ts){
		josystickGroup = new Group();
		
		asset =  Assets.getInstance();
		atlas = asset.get("data/assets.pack");
		right = new TextureRegion(atlas.findRegion("rightarrow"));
		left = new TextureRegion(atlas.findRegion("leftarrow"));
		up = new TextureRegion(atlas.findRegion("uparrow"));
		down = new TextureRegion(atlas.findRegion("downarrow"));
		
		rightBtn = new DisplayObject(right);
		rightBtn.setPosition(Config.SCREEN_WIDTH - 10 - rightBtn.getWidth(),
				10 + (rightBtn.getHeight() * 2));
		
		leftBtn = new DisplayObject(left);
		leftBtn.setPosition(Config.SCREEN_WIDTH - 10 - (leftBtn.getWidth() * 3),
				10 + (leftBtn.getHeight() * 2));
		
		upBtn = new DisplayObject(up);
		upBtn.setPosition(Config.SCREEN_WIDTH - 10 - (upBtn.getWidth() * 2),
				10 + (upBtn.getHeight() * 3));
		
		downBtn = new DisplayObject(down);
		downBtn.setPosition(Config.SCREEN_WIDTH - 10 - (downBtn.getWidth() * 2),
				10 + (downBtn.getHeight() * 1));
		
		rightBtn.addListener(new JoystickListener(ts, MOVE_RIGHT));
		leftBtn.addListener(new JoystickListener(ts, MOVE_LEFT));
		downBtn.addListener(new JoystickListener(ts, MOVE_DOWN));
		upBtn.addListener(new JoystickListener(ts, MOVE_UP));
		ts.addActor(rightBtn);
		josystickGroup.addActor(rightBtn);
		josystickGroup.addActor(leftBtn);
		josystickGroup.addActor(downBtn);
		josystickGroup.addActor(upBtn);
		
		ts.getDisplayCamera().addActor(josystickGroup);
	}
}
