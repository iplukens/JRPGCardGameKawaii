package com.cgk.game.event;

public class EndHeroTurnEvent extends CloneableEvent implements GameEvent {

	@Override
	public EventType getType() {
		return EventType.END_HERO_TURN;
	}

}
