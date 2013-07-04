package com.game.p1.components;

public enum ObjectState {
	STOP(0),
	MOVE_LEFT(1), MOVE_RIGHT(2), MOVE_DOWN(3), MOVE_UP(4);
	
	private final int value;
	ObjectState(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
