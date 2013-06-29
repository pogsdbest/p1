package com.game.p1.display.objects.player;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.framework.display.AnimatedDisplayObject;
import com.game.p1.components.ObjectState;
import com.game.p1.components.StateMachine;

public class PlayerDisplay extends AnimatedDisplayObject implements StateMachine {

	private ObjectState currentState;

	private PlayerModel model;

	public PlayerDisplay(PlayerModel model) {
		// TODO Auto-generated constructor stub
		super(model.getDefaultTextures());
		this.model = model;
		
		idle();
	}

	public void moveLeft() {
		setMoveTextures(ObjectState.MOVE_LEFT, model.getWalkLeftTextures());
	}

	public void moveRight() {
		setMoveTextures(ObjectState.MOVE_RIGHT, model.getWalkRightTextures());
	}

	public void moveDown() {
		setMoveTextures(ObjectState.MOVE_DOWN, model.getWalkDownTextures());
	}

	public void moveUp() {
		setMoveTextures(ObjectState.MOVE_UP, model.getWalkUpTextures());
	}
	
	public void idle() {
		currentState = ObjectState.STOP;
		animation.setPlayMode(Animation.NORMAL);
		looping = false;
	}

	public void setMoveTextures(ObjectState moveState,TextureRegion[] textures) {
		// TODO Auto-generated method stub
		looping = true;
		setTextures(duration,textures);
		animation.setPlayMode(Animation.LOOP);
		currentState = moveState;
	}

	@Override
	public ObjectState getState() {
		return currentState;
	}
	
}
