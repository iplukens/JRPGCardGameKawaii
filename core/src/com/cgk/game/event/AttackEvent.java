package com.cgk.game.event;

import com.cgk.game.gameobject.GameObject;
import com.cgk.game.gameobject.units.UnitAttack;
import com.cgk.game.gameobject.units.UnitObject;

public abstract class AttackEvent extends BaseEvent {

	protected UnitObject unit;
	protected UnitObject target;

	public AttackEvent(UnitObject unit, UnitObject target) {
		this.setUnit(unit);
		this.target = target;
	}

	public UnitAttack getAttack() {
		return unit.getAttack();
	}

	public void setUnit(UnitObject unit) {
		this.unit = unit;
	}

	public UnitObject getTarget() {
		return target;
	}

	@Override
	public GameObject getOriginObject() {
		return unit;
	}

}
