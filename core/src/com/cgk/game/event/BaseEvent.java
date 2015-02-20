package com.cgk.game.event;

public abstract class BaseEvent implements GameEvent {

	public GameEvent cloneEvent() {
		try {
			return (GameEvent) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean hasPriority() {
		return false;
	}
}
