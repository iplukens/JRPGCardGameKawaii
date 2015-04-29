package com.cgk.game.event.cardevents;

import com.cgk.game.event.EventType;

public class DiscardEvent extends CardEffectEvent {

	@Override
	public EventType getType() {
		return EventType.DISCARD;
	}

	@Override
	public void drawPlayInfo(int start) {
		// TODO Auto-generated method stub

	}

}
