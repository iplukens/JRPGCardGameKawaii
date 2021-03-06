package com.cgk.game.event;

import com.cgk.game.gameobject.units.UnitAttack;

public class AttackPlayerEvent extends AttackEvent {

	private UnitAttack attack;

	public AttackPlayerEvent(UnitAttack attack) {
		super(attack);
	}

	@Override
	public EventType getType() {
		return EventType.ATTACK_PLAYER;
	}

	public UnitAttack getAttack() {
		return attack;
	}

	public void setAttack(UnitAttack attack) {
		this.attack = attack;
	}

}
