package com.cgk.game.event;

public class EndHeroTurnEvent implements GameEvent {

	@Override
	public EventType getType() {
		return EventType.END_HERO_TURN;
	}

}
