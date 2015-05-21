package com.cgk.game.gameobject.units.eventresponses;

import com.cgk.game.event.AttackEvent;
import com.cgk.game.gameobject.eventresponses.EventResponse;
import com.cgk.game.gameobject.units.UnitObject;

public class ProcessAttackResponse extends
		EventResponse<UnitObject, AttackEvent> {

	@Override
	protected void handleEvent(UnitObject gameObject, AttackEvent event) {
		if (event.getTarget().equals(gameObject)) {
			gameObject.processAttack(event.getAttack());
		}
	}
}
