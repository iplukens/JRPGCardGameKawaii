package com.cgk.game.event;

public class AttackMultiplierEvent extends ValueEvent {

	public AttackMultiplierEvent(int value) {
		super(value);
	}

	public EventType getType() {
		return EventType.MULT_BUFF;
	}

}
