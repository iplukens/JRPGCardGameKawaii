package com.cgk.game.event;

import com.cgk.game.gameobject.GameObject;
import com.cgk.game.gameobject.units.UnitAttack;
import com.cgk.game.gameobject.units.UnitObject;

public abstract class AttackEvent extends BaseEvent {

	protected UnitObject unit;

	public AttackEvent(UnitObject unit) {
		this.setUnit(unit);
	}

	public UnitAttack getAttack() {
		return unit.getAttack();
	}

	public void setUnit(UnitObject unit) {
		this.unit = unit;
	}

	@Override
	public GameObject getOriginObject() {
		return unit;
	}

}
