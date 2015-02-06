package com.cgk.game.event;

public abstract class CloneableEvent implements GameEvent {

	public GameEvent cloneEvent() {
		try {
			return (GameEvent) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
