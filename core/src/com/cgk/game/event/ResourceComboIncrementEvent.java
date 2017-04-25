package com.cgk.game.event;

public class ResourceComboIncrementEvent extends BaseEvent implements GameEvent {

	private int resourceNumber;

	public ResourceComboIncrementEvent(int cardResourceNumber) {
		this.resourceNumber = cardResourceNumber;
	}

	@Override
	public EventType getType() {
		return EventType.RESOURCE_COMBO_EVENT;
	}

	public int getResourceNumber() {
		return resourceNumber;
	}

	public void setResourceNumber(int resourceNumber) {
		this.resourceNumber = resourceNumber;
	}

}
