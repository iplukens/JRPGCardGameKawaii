package com.cgk.game.event;

public class DroppedComobEvent extends BaseEvent implements GameEvent {

	@Override
	public EventType getType() {
		return EventType.DROP_COMBO;
	}

}
