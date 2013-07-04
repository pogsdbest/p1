package com.game.p1.display.objects.player;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.framework.display.AnimatedDisplayObject;
import com.game.p1.components.Controllable;
import com.game.p1.components.ObjectState;
import com.game.p1.components.StateMachine;

public class PlayerDisplay extends AnimatedDisplayObject implements StateMachine,Controllable {

	private ObjectState currentState;

	private PlayerModel model;

	public PlayerDisplay(PlayerModel model) {
		// TODO Auto-generated constructor stub
		super(model.getDefaultTextures());
		this.model = model;
		
		idle();
	}

	/******************Controllable interface*******************/
	@Override
	public void moveLeft() {
		setMoveTextures(ObjectState.MOVE_LEFT, model.getWalkLeftTextures());
	}

	@Override
	public void moveRight() {
		setMoveTextures(ObjectState.MOVE_RIGHT, model.getWalkRightTextures());
	}

	@Override
	public void moveDown() {
		setMoveTextures(ObjectState.MOVE_DOWN, model.getWalkDownTextures());
	}

	@Override
	public void moveUp() {
		setMoveTextures(ObjectState.MOVE_UP, model.getWalkUpTextures());
	}
	
	@Override
	public void idle() {
		currentState = ObjectState.STOP;
		animation.setPlayMode(Animation.NORMAL);
		looping = false;
	}
	/****************StateMachine interface***********************/
	
	@Override
	public ObjectState getState() {
		return currentState;
	}
	
	/***************Getters and Setters *****************************/
	public void setMoveTextures(ObjectState moveState,TextureRegion[] textures) {
		// TODO Auto-generated method stub
		looping = true;
		setTextures(duration,textures);
		animation.setPlayMode(Animation.LOOP);
		currentState = moveState;
	}

}
