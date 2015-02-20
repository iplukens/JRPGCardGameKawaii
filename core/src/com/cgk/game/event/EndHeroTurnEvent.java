package com.cgk.game.event;

public class EndHeroTurnEvent extends BaseEvent implements GameEvent {

	@Override
	public EventType getType() {
		return EventType.END_HERO_TURN;
	}

}
