package com.cgk.game.event;

import com.cgk.game.gameobject.GameObject;

/**
 * @author ianlukens May 13, 2015
 *
 */
public class UnregisterEvent extends BaseEvent {

	private GameObject gameObject;

	public UnregisterEvent(GameObject object) {
		this.setGameObject(object);
	}

	@Override
	public EventType getType() {
		return EventType.UNREGISTER;
	}

	public GameObject getGameObject() {
		return gameObject;
	}

	public void setGameObject(GameObject gameObject) {
		this.gameObject = gameObject;
	}

	@Override
	public int getPriority() {
		return 5;
	}

	@Override
	public GameObject getOriginObject() {
		return null;
	}

}
