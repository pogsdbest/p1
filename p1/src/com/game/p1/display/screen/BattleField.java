package com.game.p1.display.screen;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.game.framework.display.DisplayObject;
import com.game.framework.display.DisplayScreen;
import com.game.p1.listeners.JoystickListener;
import com.game.p1.utils.Assets;
import com.game.p1.utils.Config;

public class BattleField extends DisplayScreen {

	public BattleField() {
		super(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		// DisplayObject bg = new DisplayObject(Assets.getInstance().bg1);
		// addActor(bg);

		addJoystickGroup();
		stopMove();
	}

	private Group josystickGroup;

	private boolean moveRight, moveLeft, moveTop, moveBottom;
	public final int MOVE_RIGHT = 0;
	public final int MOVE_LEFT = 1;
	public final int MOVE_UP = 2;
	public final int MOVE_DOWN = 3;

	DisplayObject right;
	DisplayObject up;
	DisplayObject left;
	private DisplayObject down;

	float bManX = 0;
	float bManY = 0;
	DisplayObject bManTop, bManBot, bManRight, bManLeft;

	public void addJoystickGroup() {

		josystickGroup = new Group();
		Assets asset = Assets.getInstance();
		TextureAtlas atlas = asset.get("data/assets.pack");
		
		bManTop = new DisplayObject(atlas.findRegion("uphuman"));
		bManBot = new DisplayObject(atlas.findRegion("downhuman"));
		bManRight = new DisplayObject(atlas.findRegion("righthuman"));
		bManLeft = new DisplayObject(atlas.findRegion("lefthuman"));

		bManX = width / 2;
		bManY = height / 2;

		bManTop.setPosition(bManX, bManY);
		bManBot.setPosition(bManX, bManY);
		bManRight.setPosition(bManX, bManY);
		bManLeft.setPosition(bManX, bManY);

		addActor(bManTop);
		addActor(bManBot);
		addActor(bManRight);
		addActor(bManLeft);

		bManTop.setVisible(false);
		bManBot.setVisible(true);
		bManRight.setVisible(false);
		bManLeft.setVisible(false);

		right = new DisplayObject(atlas.findRegion("rightarrow"));
		left = new DisplayObject(atlas.findRegion("leftarrow"));
		up = new DisplayObject(atlas.findRegion("uparrow"));
		down = new DisplayObject(atlas.findRegion("downarrow"));

		right.setPosition(Config.SCREEN_WIDTH - 10 - right.getWidth(),
				10 + (right.getHeight() * 2));
		left.setPosition(Config.SCREEN_WIDTH - 10 - (left.getWidth() * 3),
				10 + (right.getHeight() * 2));
		up.setPosition(Config.SCREEN_WIDTH - 10 - (left.getWidth() * 2),
				10 + (right.getHeight() * 3));
		down.setPosition(Config.SCREEN_WIDTH - 10 - (left.getWidth() * 2),
				10 + (right.getHeight() * 1));

		// right.addListener(new ActorDragListener(up, this));
//		right.addListener(new JoystickListener(this, MOVE_RIGHT));
//		left.addListener(new JoystickListener(this, MOVE_LEFT));
//		up.addListener(new JoystickListener(this, MOVE_UP));
//		down.addListener(new JoystickListener(this, MOVE_DOWN));

		josystickGroup.addActor(right);
		josystickGroup.addActor(up);
		josystickGroup.addActor(down);
		josystickGroup.addActor(left);

		addActor(josystickGroup);
	}

	private void makeItFalse() {
		bManTop.setVisible(false);
		bManBot.setVisible(false);
		bManRight.setVisible(false);
		bManLeft.setVisible(false);
	}

	public void move() {
		if (moveRight) {
			makeItFalse();
			bManRight.setPosition(bManX + 1, bManY);
			bManRight.setVisible(true);
			bManX += 1;
		} else if (moveLeft) {
			makeItFalse();
			bManLeft.setPosition(bManX - 1, bManY);
			bManLeft.setVisible(true);
			bManX -= 1;
		} else if (moveTop) {
			makeItFalse();
			bManTop.setPosition(bManX, bManY + 1);
			bManTop.setVisible(true);
			bManY += 1;
		} else if (moveBottom) {
			makeItFalse();
			bManBot.setPosition(bManX, bManY - 1);
			bManBot.setVisible(true);
			bManY -= 1;
		}
	}

	float holder = 0;

	@Override
	protected void update(float delta) {
		holder += delta;
		System.out.println(holder);

		if (moveRight || moveLeft || moveTop || moveBottom)
			move();
	}

	public void stopMove() {
		moveRight = false;
		moveLeft = false;
		moveTop = false;
		moveBottom = false;
	}

	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}

	public void setMoveLeft(boolean moveLeft) {
		this.moveLeft = moveLeft;
	}

	public void setMoveTop(boolean moveTop) {
		this.moveTop = moveTop;
	}

	public void setMoveBottom(boolean moveBottom) {
		this.moveBottom = moveBottom;
	}

}
