package com.cgk.game.event;

import com.cgk.game.gameobject.GameObject;

public abstract class BaseEvent implements GameEvent {

	public GameEvent cloneEvent() {
		try {
			return (GameEvent) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getPriority() {
		return 1;
	}

	@Override
	public GameObject getOriginObject() {
		return null;
	}
}
