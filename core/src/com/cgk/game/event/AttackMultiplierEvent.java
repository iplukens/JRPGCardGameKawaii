package com.cgk.game.event;

public class AttackMultiplierEvent extends ValueEvent {

	public AttackMultiplierEvent(int value) {
		super(value);
	}

	public EventId getEventId() {
		return EventId.MULT_BUFF;
	}

}
