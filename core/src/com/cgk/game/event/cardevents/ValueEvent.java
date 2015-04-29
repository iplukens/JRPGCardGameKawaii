package com.cgk.game.event.cardevents;


public abstract class ValueEvent extends CardEffectEvent {

	int value;

	public ValueEvent(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
