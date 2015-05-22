package com.cgk.game.gameobject.eventresponses;

import com.cgk.game.event.GameEvent;
import com.cgk.game.gameobject.IGameObject;

public abstract class EventResponse<T extends IGameObject, E extends GameEvent> {

	@SuppressWarnings("unchecked")
	public void respond(IGameObject gameObject, GameEvent event) {
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
