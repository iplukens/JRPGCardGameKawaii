package com.cgk.game.event.cardevents;

import com.cgk.game.event.EventType;

public class AttackAdditiveEvent extends ValueEvent {

	public AttackAdditiveEvent(int value) {
		super(value);
	}

	public EventType getType() {
		return EventType.ADD_BUFF;
	}

	@Override
	public void drawPlayInfo(int start) {
		// TODO Auto-generated method stub

	}

}
