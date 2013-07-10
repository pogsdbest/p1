package com.game.p1.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.game.p1.display.screen.TestScreen;

public class JoystickListener extends ActorGestureListener{
	TestScreen ts;
	int direction;
	public JoystickListener(TestScreen ts, int direction){
		this.ts = ts;
		this.direction = direction;
	}
		@Override
		public void touchDown(InputEvent event, float x, float y, int pointer,
				int button) {
			/*
			if (direction == Joystick.MOVE_RIGHT)
				ts.setGoRight(true);
			else if (direction == Joystick.MOVE_LEFT)
				ts.setGoLeft(true);
			else if (direction == Joystick.MOVE_UP)
				ts.setGoUp(true);
			else if (direction == Joystick.MOVE_DOWN)
				ts.setGoDown(true);
				*/
		}
		@Override
		public void touchUp(InputEvent event, float x, float y, int pointer,
				int button) {
			ts.stopMove();
		}
	
}
