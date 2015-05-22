package com.cgk.game.event;

import java.util.ArrayList;
import java.util.List;

import com.cgk.game.gameobject.GameObject;
import com.cgk.game.gameobject.units.UnitAttack;
import com.cgk.game.gameobject.units.UnitObject;

public abstract class AttackEvent extends BaseEvent {

	protected UnitObject unit;
	protected List<UnitObject> targets;

	public AttackEvent(UnitObject unit, List<UnitObject> targets) {
		this.setUnit(unit);
		this.targets = targets;
	}

	public AttackEvent(UnitObject unit, UnitObject target) {
		this.setUnit(unit);
		this.targets = new ArrayList<>();
		targets.add(target);
	}

	public UnitAttack getAttack() {
		return unit.getAttack();
	}

	public void setUnit(UnitObject unit) {
		this.unit = unit;
	}

	public List<UnitObject> getTargets() {
		return targets;
	}

	@Override
	public GameObject getOriginObject() {
		return unit;
	}

}
