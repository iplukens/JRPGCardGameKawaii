package com.cgk.game.event.cardevents;

import com.cgk.game.event.EventType;

public class AttackMultiplierEvent extends ValueEvent {

	public AttackMultiplierEvent(int value) {
		super(value);
	}

	public EventType getType() {
		return EventType.MULT_BUFF;
	}

	@Override
	public void drawPlayInfo(int start) {
		// TODO Auto-generated method stub

	}

}
