package com.cgk.game.gameobject.eventresponses;

import com.cgk.game.event.GameEvent;
import com.cgk.game.gameobject.GameObject;

public abstract class EventResponse<T extends GameObject, E extends GameEvent> {

	@SuppressWarnings("unchecked")
	public void respond(GameObject gameObject, GameEvent event) {
		handleEvent((T) gameObject, (E) event);
	}

	/**
	 * the actual response code
	 * 
	 * @param gameObject
	 * @param event
	 */
	protected abstract void handleEvent(T gameObject, E event);

}
