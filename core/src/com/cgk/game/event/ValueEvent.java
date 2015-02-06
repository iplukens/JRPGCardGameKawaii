package com.cgk.game.event;

public abstract class ValueEvent extends CloneableEvent implements GameEvent {

	int value;

	public ValueEvent(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
