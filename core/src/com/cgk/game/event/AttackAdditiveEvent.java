package com.cgk.game.event;


public class AttackAdditiveEvent extends ValueEvent {
	
	public AttackAdditiveEvent(int value) {
		super(value);
	}

	public EventId getEventId() {
		return EventId.ADD_BUFF;
	}
	
}
