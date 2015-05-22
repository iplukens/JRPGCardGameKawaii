package com.cgk.game.gameobject.units.eventresponses;

import com.cgk.game.event.cardevents.ValueEvent;
import com.cgk.game.gameobject.card.ValueType;
import com.cgk.game.gameobject.eventresponses.EventResponse;
import com.cgk.game.gameobject.units.UnitObject;

/**
 * @author ianlukens May 21, 2015
 *
 */
public class UnitHealResponse extends EventResponse<UnitObject, ValueEvent> {

	@Override
	protected void handleEvent(UnitObject gameObject, ValueEvent event) {
		gameObject.heal(event.getValue(ValueType.STRENGTH));
	}

}
