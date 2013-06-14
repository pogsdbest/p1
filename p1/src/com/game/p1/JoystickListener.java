package com.game.p1;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.game.framework.display.DisplayScreen;
import com.game.p1.display.screen.BattleField;

public class JoystickListener extends ActorGestureListener{
	BattleField bf;
	int direction;
	public JoystickListener(BattleField bf, int direction){
		this.bf = bf;
		this.direction = direction;
	}
		@Override
		public void touchDown(InputEvent event, float x, float y, int pointer,
				int button) {
			if (direction == bf.MOVE_RIGHT)
				bf.setMoveRight(true);
			else if (direction == bf.MOVE_LEFT)
				bf.setMoveLeft(true);
			else if (direction == bf.MOVE_UP)
				bf.setMoveTop(true);
			else if (direction == bf.MOVE_DOWN)
				bf.setMoveBottom(true);
		}
		@Override
		public void touchUp(InputEvent event, float x, float y, int pointer,
				int button) {
			bf.stopMove();
		}
	
}
