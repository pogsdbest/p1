package com.game.p1.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.game.p1.components.ObjectState;
import com.game.p1.components.StateMachine;
import com.game.p1.utils.Config;

public class MoveAction extends Action {
	
	private float speed;

	public MoveAction(float speed) {
		this.speed = speed;
	}

	@Override
	public boolean act(float delta) {
		float velocity = speed * Config.DELTA_TIME;
		ObjectState currentState = ((StateMachine)actor).getState();
		switch (currentState) {
		case STOP:
			setPosition(getX(), getY());
			break;
		case MOVE_LEFT:
			setPosition(getX()-velocity, getY());
			break;
		case MOVE_RIGHT:
			setPosition(getX()+velocity, getY());
			break;
		case MOVE_DOWN:
			setPosition(getX(), getY()-velocity);
			break;
		case  MOVE_UP:
			setPosition(getX(), getY()+velocity);
			break;
		default:
			break;
		}
		return false;
	}
	
	private void setPosition(float x, float y) {
		getActor().setPosition(x, y);
	}

	private float getX() {
		return getActor().getX();
	}
	
	private float getY() {
		return getActor().getY();
	}

}
