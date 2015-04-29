package com.cgk.game.event;

public class SuperComboEvent extends BaseEvent implements GameEvent {

	@Override
	public EventType getType() {
		return EventType.SUPER_COMBO;
	}

}
