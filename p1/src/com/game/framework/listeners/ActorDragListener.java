package com.game.framework.listeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.game.framework.display.DisplayScreen;
import com.game.framework.utils.L;

public class ActorDragListener extends InputListener {

	private final Actor actor;
	private float startX;
	private float startY;
	private final DisplayScreen screen;

	public ActorDragListener(Actor actor, DisplayScreen screen) {
		this.actor = actor;
		this.screen = screen;
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
		/*
		 * Gdx.app.log("Actor X", ""+actor.getX()); Gdx.app.log("Actor Y",
		 * ""+actor.getY()); Gdx.app.log("X", ""+x); Gdx.app.log("Y", ""+y);
		 */
		actor.setPosition((actor.getX() + x) - actor.getWidth() / 2,
				(actor.getY() + y) - actor.getHeight() / 2);
		// L.wtf("x "+x);
		// L.wtf("y "+y);
		// L.wtf("actor x "+actor.getX());
		//L.wtf("actor y "+actor.getY());
		// TODO Auto-generated method stub
		// return super.touchDown(event, x, y, pointer, button);
		return true;
	}

	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		// TODO Auto-generated method stub
		// L.wtf("x "+x);
		// L.wtf("y "+y);
		actor.setPosition((actor.getX() + x) - actor.getWidth() / 2,
				(actor.getY() + y) - actor.getHeight() / 2);
		super.touchDragged(event, x, y, pointer);
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer,
			int button) {
		// TODO Auto-generated method stub
		super.touchUp(event, x, y, pointer, button);
		L.wtf("Actor is at (" + (int) actor.getX() + "," + (int) actor.getY()
				+ ")");
	}
}
