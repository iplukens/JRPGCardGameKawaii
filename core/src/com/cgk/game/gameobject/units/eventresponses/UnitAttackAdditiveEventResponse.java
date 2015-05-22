package com.cgk.game.gameobject.units.eventresponses;

import com.cgk.game.event.cardevents.AttackAdditiveEvent;
import com.cgk.game.gameobject.card.ValueType;
import com.cgk.game.gameobject.eventresponses.EventResponse;
import com.cgk.game.gameobject.units.UnitObject;

public class UnitAttackAdditiveEventResponse extends
		EventResponse<UnitObject, AttackAdditiveEvent> {

	@Override
	protected void handleEvent(UnitObject gameObject, AttackAdditiveEvent event) {
		gameObject.setTempAttackAdditive(gameObject.getTempAttackAdditive()
				+ event.getValue(ValueType.STRENGTH));
	}

}
