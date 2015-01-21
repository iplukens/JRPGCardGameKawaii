package com.cgk.game.event;

public abstract class ValueEvent implements GameEvent {

	int value;

	public ValueEvent(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
