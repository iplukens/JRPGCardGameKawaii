package com.cgk.game.event;

public class DefeatEvent extends BaseEvent {

	@Override
	public EventType getType() {
		return EventType.DEFEAT;
	}

	@Override
	public boolean hasPriority() {
		return true;
	}

}
