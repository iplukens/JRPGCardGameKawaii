package com.cgk.game.gameobject.units.eventresponses;

import com.cgk.game.event.cardevents.AddResistanceEvent;
import com.cgk.game.gameobject.eventresponses.EventResponse;
import com.cgk.game.gameobject.units.UnitObject;

/**
 * @author ianlukens May 25, 2015
 *
 */
public class UnitAddResistanceResponse extends
		EventResponse<UnitObject, AddResistanceEvent> {

	@Override
	protected void handleEvent(UnitObject gameObject, AddResistanceEvent event) {
		gameObject.addResistanceModifier(event.getModifier());
	}

}
