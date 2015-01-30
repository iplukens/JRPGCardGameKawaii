package com.cgk.game.event;


public class AttackAdditiveEvent extends ValueEvent {
	
	public AttackAdditiveEvent(int value) {
		super(value);
	}

	public EventType getType() {
		return EventType.ADD_BUFF;
	}
	
}
