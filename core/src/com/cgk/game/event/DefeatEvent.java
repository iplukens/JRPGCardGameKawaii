package com.cgk.game.event;

public class DefeatEvent extends BaseEvent {

	@Override
	public EventType getType() {
		return EventType.DEFEAT;
	}

	@Override
	public int getPriority() {
		return 5;
	}

}
