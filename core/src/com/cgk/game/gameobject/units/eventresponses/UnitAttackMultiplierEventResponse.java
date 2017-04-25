package com.cgk.game.gameobject.units.eventresponses;

import com.cgk.game.event.cardevents.AttackMultiplierEvent;
import com.cgk.game.gameobject.card.ValueType;
import com.cgk.game.gameobject.eventresponses.EventResponse;
import com.cgk.game.gameobject.units.UnitObject;

public class UnitAttackMultiplierEventResponse extends
		EventResponse<UnitObject, AttackMultiplierEvent> {

	@Override
	protected void handleEvent(UnitObject gameObject,
			AttackMultiplierEvent event) {
		gameObject.setTempAttackMultiplicative(gameObject
				.getTempAttackMultiplicative()
				* event.getValue(ValueType.STRENGTH));
	}

}
