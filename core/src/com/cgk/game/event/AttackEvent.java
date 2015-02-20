package com.cgk.game.event;

import com.cgk.game.gameobject.units.UnitAttack;

public abstract class AttackEvent extends BaseEvent {

	private UnitAttack attack;

	public AttackEvent(UnitAttack attack) {
		this.setAttack(attack);
	}

	public UnitAttack getAttack() {
		return attack;
	}

	public void setAttack(UnitAttack attack) {
		this.attack = attack;
	}

}
