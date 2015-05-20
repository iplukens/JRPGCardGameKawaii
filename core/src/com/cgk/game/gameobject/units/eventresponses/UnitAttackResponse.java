package com.cgk.game.gameobject.units.eventresponses;

import com.cgk.game.event.GameEvent;
import com.cgk.game.gameobject.eventresponses.EventResponse;
import com.cgk.game.gameobject.units.UnitObject;

/**
 * @author ianlukens
 * May 4, 2015
 *
 */
public class UnitAttackResponse extends EventResponse<UnitObject, GameEvent> {

	@Override
	protected void handleEvent(UnitObject gameObject, GameEvent event) {
		gameObject.sendAttackEvent();
	}

}
