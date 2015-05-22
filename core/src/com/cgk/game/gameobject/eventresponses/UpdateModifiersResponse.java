package com.cgk.game.gameobject.eventresponses;

import com.cgk.game.event.GameEvent;
import com.cgk.game.gameobject.strategy.IModifiable;

/**
 * @author ianlukens
 * May 22, 2015
 *
 */
public class UpdateModifiersResponse extends EventResponse<IModifiable, GameEvent> {

	@Override
	protected void handleEvent(IModifiable gameObject, GameEvent event) {
		gameObject.updateModifiers();
	}

}
