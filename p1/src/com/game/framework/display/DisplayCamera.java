package com.game.framework.display;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class DisplayCamera extends Actor {
	
	private Actor target;
	private Camera camera;

	public DisplayCamera(Camera camera) {
		this.camera = camera;
		// TODO Auto-generated constructor stub
	}
	
	public void setTarget(Actor target) {
		this.target = target;
	}
	
	public Actor getTarget() {
		return target;
	}
	
	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		if(target!=null) {
			this.camera.position.set(target.getX(), target.getY(), camera.position.z);
		}
		super.act(delta);
	}

}
